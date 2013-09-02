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
import android.widget.Toast;

import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.adapter.HospitalAdapter;
import br.ufba.hupes.hospitaladmissionforram.adapter.ResearchAdapter;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;

@SuppressLint("NewApi")
public class ListResearches extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View viewLista = inflater.inflate(R.layout.list_researches, null);
		final ListView listView = (ListView) viewLista.findViewById(R.id.list_researches);

		Research researches[] = new Research[] {
				new Research("123456", "Ivelisse Sousa", "098765"),
				new Research("123456", "Glaucia Noblat", "098765") };

		final ResearchAdapter adapter = new ResearchAdapter(this.getActivity(),
				R.layout.item_research, researches);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Toast.makeText(ListResearches.this.getActivity(), "" + position,
						Toast.LENGTH_SHORT).show();
			}
		});

		return viewLista;
	}
}
