package br.ufba.hupes.hospitaladmissionforram;

import java.util.List;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import br.ufba.hupes.hospitaladmissionforram.connection.RestConnection;
import br.ufba.hupes.hospitaladmissionforram.helper.DatabaseHelper;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;
import br.ufba.hupes.hospitaladmissionforram.model.User;
import br.ufba.hupes.hospitaladmissionforram.model.UserHolder_;

import com.google.gson.GsonBuilder;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;

@EService
public class MainService extends IntentService {

    public static final String LOGIN = "LOGIN";
    public static final String UPDATE_HOSPITALS = "UPDATE_HOSPITALS";
    public static final String SYNC_RESOURCES = "SYNC_RESOURCES";

	@RestService
    RestConnection connection;

	@Pref
	UserHolder_ userHolder;
	
    private DatabaseHelper databaseHelper;

	public MainService() {
		super("MainService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String action = intent.getAction();
		if (action.equals(LOGIN)) {
			login();
		} else if (action.equals(UPDATE_HOSPITALS)) {
			getHospitalsOnline();
		} else if (action.equals(SYNC_RESOURCES)) {
			sendResearches();
		}
	}

	@Background
    protected void login() {
		try {
			User user = connection.login();
			if (user != null) {
				userHolder.admin().put(user.isAdmin());
				userHolder.id().put(user.getId());
				Hospital[] hospitals = user.getHospitals();
				Dao<Hospital, ?> hospitalDao = this.getHelper().getDao(Hospital.class);
				if (hospitals.length > 0) {
					for(Hospital hospital: hospitals) {
						hospitalDao.createOrUpdate(hospital);
					}
				}
				LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LOGIN));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Background
    public void getHospitalsOnline() {
		try {
			Hospital[] hospitals = connection.getHospitals();
			Dao<Hospital, ?> hospitalDao = this.getHelper().getDao(Hospital.class);
			if (hospitals.length > 0) {
				for(Hospital hospital: hospitals) {
					hospitalDao.createOrUpdate(hospital);
				}
				LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(UPDATE_HOSPITALS));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Background
	protected void sendResearches() {
		try {
			Dao<Research, ?> dao = getHelper().getDao(Research.class);
			PreparedQuery<Research> preparedQuery = dao.queryBuilder().where().isNull("syncedAt").prepare();
			List<Research> researchesToSend = dao.query(preparedQuery);
			for (Research research : researchesToSend) {
				Log.d("DEBUG", new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(research));
				connection.newResearch(research);
				research.setSent(true);
				dao.update(research);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    private DatabaseHelper getHelper() {
        if (this.databaseHelper == null) {
            this.databaseHelper = (DatabaseHelper) OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return this.databaseHelper;
    }
}
