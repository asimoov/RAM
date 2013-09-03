package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.adapter.ResearchAdapter;
import br.ufba.hupes.hospitaladmissionforram.model.Research;

/**
 * Created by denis on 03/09/13.
 */
@SuppressLint("NewApi")
public class SearchResearches extends Fragment {

    private String query;

    public SearchResearches(String query) {
        this.query = query;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.list_researches, null);
        final ListView listView = (ListView) view.findViewById(R.id.list_researches);
        final TextView header = (TextView) view.findViewById(R.id.header);
        header.setText(this.query);

        Research researches[] = new Research[] {
                new Research("123456", "Ivelisse Sousa", "098765"),
                new Research("123456", "Glaucia Noblat", "098765") };

        final ResearchAdapter adapter = new ResearchAdapter(this.getActivity(),
                R.layout.item_research, researches);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                Toast.makeText(SearchResearches.this.getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
