package br.ufba.hupes.hospitaladmissionforram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import br.ufba.hupes.hospitaladmissionforram.DatabaseHelper;
import br.ufba.hupes.hospitaladmissionforram.NewResearch;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.adapter.ResearchAdapter;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;

/**
 * Created by denis on 03/09/13.
 */
@SuppressLint("NewApi")
public class SearchResearches extends Activity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.list_researches);

        final ListView listView = (ListView) this.findViewById(R.id.list_researches);
        this.getActionBar().setTitle(this.getIntent().getExtras().getString("QUERY"));

        try {
            Dao dao = this.getHelper().getDao(Research.class);
            final List<Research> researches = dao.queryForAll();

            final ResearchAdapter adapter = new ResearchAdapter(this,
                    R.layout.item_research, researches);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    Research research = researches.get(position);
                    Intent intent = new Intent(SearchResearches.this, NewResearch.class);
                    intent.putExtra("RESEARCH_ID", research.getId().toString());
                    startActivityForResult(intent, 1);
                }
            });
        } catch(SQLException ex) {
            Log.e("ListResearches", ex.getMessage());
        }
    }

    private DatabaseHelper getHelper() {
        if (this.databaseHelper == null) {
            this.databaseHelper = (DatabaseHelper) OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return this.databaseHelper;
    }
}
