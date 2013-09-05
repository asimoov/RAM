package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import br.ufba.hupes.hospitaladmissionforram.DatabaseHelper;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.adapter.ResearchAdapter;
import br.ufba.hupes.hospitaladmissionforram.model.Research;

/**
 * Created by denis on 03/09/13.
 */
@SuppressLint("NewApi")
public class SearchResearches extends Fragment {

    private DatabaseHelper databaseHelper;

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

        try {
            Dao dao = this.getHelper().getDao(Research.class);
            final List<Research> researches = dao.queryForAll();

            final ResearchAdapter adapter = new ResearchAdapter(this.getActivity(),
                    R.layout.item_research, researches);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    Toast.makeText(SearchResearches.this.getActivity(), "" + researches.get(position),
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
