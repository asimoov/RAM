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

import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.adapter.HospitalAdapter;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;

@SuppressLint("NewApi")
public class GridHospitals extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.grid_hospitals, null);
		final GridView listView = (GridView) view.findViewById(R.id.grid_hospitals);

		Hospital hospitals[] = new Hospital[] {
				new Hospital("Hospital Universitário Professor Edgard Santos", "HUPES", "Salvador"),
				new Hospital("Hospital Geral do Estado", "HGE", "Salvador") };

		final HospitalAdapter adapter = new HospitalAdapter(this.getActivity(),
				R.layout.item_hospital, hospitals);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Log.d("GridHospitals", "3");
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.ambos, new ListResearches());
                ft.addToBackStack(null);
				ft.commit();
				Log.d("GridHospitals", "4");
			}
		});

		return view;
	}
}
