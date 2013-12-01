package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.UUID;

import br.ufba.hupes.hospitaladmissionforram.helper.DatabaseHelper;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;

/**
 * Created by Leandro on 15/10/13.
 */
public abstract class NewResearchFragment extends Fragment {


    private DatabaseHelper databaseHelper;

    Research research;

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
        Bundle extras = getActivity().getIntent().getExtras();
        String researchId = extras.getString("RESEARCH_ID");
        if (researchId != null) {
            Dao dao = this.getHelper().getDao(Research.class);
            UUID id = UUID.fromString(researchId);
            return (Research) dao.queryForId(id);
        }

        String hospitalId = extras.getString("HOSPITAL_ID");
        if(hospitalId != null) {
            Dao dao = this.getHelper().getDao(Hospital.class);
            UUID id = UUID.fromString(hospitalId);
            Hospital hospital = (Hospital) dao.queryForId(id);

            Research research = new Research();
            research.setHospital(hospital);

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

}
