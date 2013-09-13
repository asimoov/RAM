package br.ufba.hupes.hospitaladmissionforram;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.ufba.hupes.hospitaladmissionforram.adapter.ResearchAdapter;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;

/**
 * Created by denis on 12/09/13.
 */
public class ListResearches extends Activity {
    DatabaseHelper databaseHelper;

    ResearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_researches);

        this.update();
    }

    private void update() {
        final ListView listView = (ListView) this.findViewById(R.id.list_researches);

        try {
            Hospital hospital = this.getHospital();
            this.getActionBar().setTitle(hospital.getAcronym());


            final List<Research> researches = new ArrayList<Research>(hospital.getResearches());
            this.adapter = new ResearchAdapter(this,
                    R.layout.item_research, researches);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    Research research = researches.get(position);
                    Intent intent = new Intent(ListResearches.this, NewResearch.class);
                    intent.putExtra("RESEARCH_ID", research.getId().toString());
                    startActivityForResult(intent, 1);
                }
            });
        } catch (SQLException ex) {
            Log.e("ListResearches", ex.getMessage());
        }
    }

    private Hospital getHospital() throws SQLException {
        Dao HospitalDao = this.getHelper().getDao(Hospital.class);
        UUID id = UUID.fromString(this.getIntent().getExtras().getString("HOSPITAL_ID"));
        return (Hospital) HospitalDao.queryForId(id);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                try {
                    Intent intent = new Intent(this, NewResearch.class);
                    intent.putExtra("HOSPITAL_ID", getHospital().getId().toString());
                    startActivityForResult(intent, 1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_research, menu);

        // Get the SearchView and set the searchable configuration
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setOnQueryTextListener(queryTextListener);
        searchView.setIconifiedByDefault(false);
        return true;
    }

    final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextChange(String newText) {
            adapter.getFilter().filter(newText);
            return true;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            return true;
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case Activity.RESULT_OK:
                this.update();
                break;
        }
    }

    private DatabaseHelper getHelper() {
        if (this.databaseHelper == null) {
            this.databaseHelper = (DatabaseHelper) OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return this.databaseHelper;
    }
}
