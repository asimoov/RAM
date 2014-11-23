package br.ufba.hupes.hospitaladmissionforram;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.springframework.web.client.ResourceAccessException;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import br.ufba.hupes.hospitaladmissionforram.connection.RestConnection;
import br.ufba.hupes.hospitaladmissionforram.helper.DatabaseHelper;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.RAM;
import br.ufba.hupes.hospitaladmissionforram.model.Research;
import br.ufba.hupes.hospitaladmissionforram.model.User;
import br.ufba.hupes.hospitaladmissionforram.model.UserHolder_;

@EService
public class MainService extends IntentService {

    public static final String ERROR = "ERROR";
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
			syncResources();
		}
	}

	private void sendError(String action, String message) {
		Intent intent = new Intent(action);
		intent.putExtra(ERROR, message);
		LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
	}

	@Background
    protected void login() {
		try {
			User user = connection.login();
			if (user != null) {
				userHolder.admin().put(user.isAdmin());
				userHolder.id().put(user.getId());
				Hospital[] h = user.getHospitals();
				List<Hospital> serverHospitals = Arrays.asList(h);
				Dao<Hospital, ?> hospitalDao = this.getHelper().getDao(Hospital.class);
				if (serverHospitals.size() > 0) {
					
					List<Hospital> list = hospitalDao.queryForAll();
					for(Hospital hospital: list) {
						if (!serverHospitals.contains(hospital)) {
							hospitalDao.delete(hospital);
						}
					}
					
					for(Hospital hospital: serverHospitals) {
						hospitalDao.createOrUpdate(hospital);
					}
				}
				LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LOGIN));
			}
		} catch (ResourceAccessException e) {
			sendError(LOGIN, "Não foi possível se conectar ao servidor. Verifique seu acesso à internet e tente novamente.");
		} catch (Exception e) {
			sendError(LOGIN, "");
			e.printStackTrace();
		}
	}

	@Background
    public void getHospitalsOnline() {
		try {
			Hospital[] h = connection.getHospitals();
			List<Hospital> serverHospitals = Arrays.asList(h);
			Dao<Hospital, ?> hospitalDao = this.getHelper().getDao(Hospital.class);
			if (serverHospitals.size() > 0) {
				
				List<Hospital> list = hospitalDao.queryForAll();
				for(Hospital hospital: list) {
					if (!serverHospitals.contains(hospital)) {
						hospitalDao.delete(hospital);
					}
				}
				
				for(Hospital hospital: serverHospitals) {
					hospitalDao.createOrUpdate(hospital);
				}
				try {
					getResearches();
					sendResearches();
				} catch (Exception e) {
				}
			} else {
				hospitalDao.delete(hospitalDao.queryForAll());
			}
			LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(UPDATE_HOSPITALS));
		} catch (ResourceAccessException e) {
			sendError(UPDATE_HOSPITALS, "Não foi possível se conectar ao servidor. Verifique seu acesso à internet e tente novamente.");
		} catch (Exception e) {
			sendError(UPDATE_HOSPITALS, "Erro ao sincronizar, tente novamente mais tarde");
			e.printStackTrace();
		}
    }
	
	@Background
	protected void syncResources() {
		try {
			getResearches();
			sendResearches();
		} catch (ResourceAccessException e) {
			sendError(SYNC_RESOURCES, "Não foi possível se conectar ao servidor. Verifique seu acesso à internet e tente novamente.");
		} catch (Exception e) {
			sendError(SYNC_RESOURCES, "Erro ao sincronizar, tente novamente mais tarde");
		}
		LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(SYNC_RESOURCES));
	}

	protected void getResearches() {
		Research[] researches = connection.getResearches();
		for (int i = 0; i < researches.length; i++) {
			try {
				Research serverResearch = researches[i];
				serverResearch.setHospital(new Hospital(serverResearch.getHospitalId()));
				Dao<Research, String> dao;
					dao = getHelper().getDao(Research.class);
	            Research dbResearch = dao.queryForId(serverResearch.getId());
	            if (dbResearch != null) {
	            	String updatedAt = dbResearch.getUpdatedAt();
	            	
	            	if (MainApp.compareDatesFormatted(serverResearch.getUpdatedAt(),updatedAt,"yyyy-MM-dd HH:mm:ss ZZZ") <= 0) {
	            		Log.d("DEBUG", "Pulou update: " + updatedAt);
	            		Log.d("DEBUG", "Pulou update: " + new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(serverResearch));
	            		continue;
	            	}
	            }
	            dao.createOrUpdate(serverResearch);
	    		RAM ram = serverResearch.getRam();
	    		Dao<RAM, String> dao2 = getHelper().getDao(RAM.class);
	    		dao2.createOrUpdate(ram);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void sendResearches() {
		try {
			Dao<Research, ?> dao = getHelper().getDao(Research.class);
			PreparedQuery<Research> preparedQuery = dao.queryBuilder().where().isNull("createdAt").or().eq("createdAt", "").and().eq("sent", false).prepare();
			
			List<Research> researchesToSend = dao.query(preparedQuery);
			for (Research research : researchesToSend) {
				research.setHospital(research.getHospital());
				Log.d("DEBUG", new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(research));
				if (research.getCreatedAt() == null) {
					connection.newResearch(research);
					research.setCreatedAt("");
				} else {
					connection.updateResearch(research.getId(), research);
				}
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
