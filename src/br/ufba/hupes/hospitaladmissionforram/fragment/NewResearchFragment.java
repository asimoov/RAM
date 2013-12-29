package br.ufba.hupes.hospitaladmissionforram.fragment;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import br.ufba.hupes.hospitaladmissionforram.helper.DatabaseHelper;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.RAM;
import br.ufba.hupes.hospitaladmissionforram.model.Research;
import br.ufba.hupes.hospitaladmissionforram.model.Status;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

/**
 * Created by Leandro on 15/10/13.
 */
public abstract class NewResearchFragment extends Fragment {

    private DatabaseHelper databaseHelper;

    static Research research;

	public static void resetResearch() {
		research = null;
	}
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            research = getResearch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getActivity().getActionBar().setTitle(research.getHospital().getAcronym());
    }

    private Research getResearch() throws SQLException {
    	if (research != null) return research;
        Bundle extras = getActivity().getIntent().getExtras();
        String researchId = extras.getString("RESEARCH_ID");
        if (researchId != null) {
            Dao dao = this.getHelper().getDao(Research.class);
            UUID id = UUID.fromString(researchId);
            Research queryForId = (Research) dao.queryForId(id);
			return queryForId;
        }

        long hospitalId = extras.getLong("HOSPITAL_ID",0);
        if(hospitalId != 0) {
            Dao dao = this.getHelper().getDao(Hospital.class);
			Hospital hospital = (Hospital) dao.queryForId(hospitalId);

            Research research = new Research();
            research.setHospital(hospital);
            Dao dao2 = this.getHelper().getDao(Research.class);
            return research;
        }
        return null;
    }

    public abstract boolean save();

    protected DatabaseHelper getHelper() {
        if (this.databaseHelper == null) {
            this.databaseHelper = (DatabaseHelper) OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }

        return this.databaseHelper;
    }

	public void saveResearchOnDatabase() throws SQLException {
    	if(research.isOpen()) {
            research.setStatus(Status.OPEN.ordinal());

            //TODO
            research.setUpdateAt(new Date().toString());
            Dao<Research, UUID> dao = getHelper().getDao(Research.class);
            dao.createOrUpdate(research);
            saveRAMOnDatabase(research);

            getActivity().setResult(Activity.RESULT_OK);
            Toast.makeText(getActivity(), "Salvo", Toast.LENGTH_SHORT).show();
        } else {
            research.setStatus(Status.CLOSE.ordinal());
        }
    	research = null;
	}

	private void saveRAMOnDatabase(Research research2) throws SQLException {
		RAM ram = research.getRam();
		Dao<RAM, UUID> dao = getHelper().getDao(RAM.class);
		dao.createOrUpdate(ram);
	}
}
