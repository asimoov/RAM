package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import br.ufba.hupes.hospitaladmissionforram.DatabaseHelper;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.adapter.HospitalAdapter;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;

@SuppressLint("NewApi")
public class GridHospitals extends Fragment {
    private DatabaseHelper databaseHelper;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.grid_hospitals, null);
		final GridView listView = (GridView) view.findViewById(R.id.grid_hospitals);

        try {
            Dao dao = this.getHelper().getDao(Hospital.class);
            final List<Hospital> hospitals = dao.queryForAll();

            final HospitalAdapter adapter = new HospitalAdapter(this.getActivity(),
                    R.layout.item_hospital, hospitals);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    Hospital hospital = hospitals.get(position);
                    Log.d("GridHospitals", hospitals.toString());
                    Log.d("GridHospitals", hospital.getId().toString());
                    Fragment fragment = new ListResearches();

                    Bundle args = new Bundle();
                    args.putString("HOSPITAL_ID", hospital.getId().toString());
                    fragment.setArguments(args);

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.ambos, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
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
