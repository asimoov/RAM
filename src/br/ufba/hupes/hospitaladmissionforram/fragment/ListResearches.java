package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import br.ufba.hupes.hospitaladmissionforram.DatabaseHelper;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.adapter.HospitalAdapter;
import br.ufba.hupes.hospitaladmissionforram.adapter.ResearchAdapter;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;

@SuppressLint("NewApi")
public class ListResearches extends Fragment {

    private DatabaseHelper databaseHelper;

    public ListResearches() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.list_researches, null);
		final ListView listView = (ListView) view.findViewById(R.id.list_researches);
        final TextView header = (TextView) view.findViewById(R.id.header);

        try {
            Dao HospitalDao = this.getHelper().getDao(Hospital.class);
            UUID id = UUID.fromString(this.getArguments().getString("HOSPITAL_ID"));
            Hospital hospital = (Hospital) HospitalDao.queryForId(id);
            header.setText(hospital.getAcronym());

            final List<Research> researches = new ArrayList<Research>(hospital.getResearches());
            final ResearchAdapter adapter = new ResearchAdapter(this.getActivity(),
                    R.layout.item_research, researches);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    Toast.makeText(ListResearches.this.getActivity(), "" + researches.get(position),
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch(SQLException ex) {
            Log.e("ListResearches", ex.getMessage());
        }


		return view;
	}

    private DatabaseHelper getHelper() {
        if (this.databaseHelper == null) {
            this.databaseHelper = (DatabaseHelper) OpenHelperManager.getHelper(this.getActivity(), DatabaseHelper.class);
        }
        return this.databaseHelper;
    }

}
