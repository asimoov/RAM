package br.ufba.hupes.hospitaladmissionforram.activity;

import java.sql.SQLException;
import java.util.List;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import br.ufba.hupes.hospitaladmissionforram.MainApp;
import br.ufba.hupes.hospitaladmissionforram.MainService;
import br.ufba.hupes.hospitaladmissionforram.MainService_;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.adapter.HospitalAdapter;
import br.ufba.hupes.hospitaladmissionforram.helper.DatabaseHelper;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

@SuppressLint("NewApi")
@EActivity
public class MainActivity extends BaseActivity {

    private DatabaseHelper databaseHelper;

    private HospitalAdapter adapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		update();
    }

	@Override
	protected void onResume() {
		super.onResume();
	};
	
    @UiThread
    protected void update() {
        try {
            final List<Hospital> hospitals = getHospitals();
            this.adapter = new HospitalAdapter(this,
                    R.layout.item_hospital, hospitals);

            final GridView listView = (GridView) this.findViewById(R.id.grid_hospitals);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    Hospital hospital = hospitals.get(position);
                    Intent intent = new Intent(MainActivity.this, ListResearches_.class);
                    intent.putExtra("HOSPITAL_ID", hospital.getId());
                    startActivityForResult(intent, 1);
                }
            });
        } catch(SQLException ex) {
            Log.e("ListResearches", ex.getMessage());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case Activity.RESULT_OK:
                this.update();
                break;
        }
    }

	private List<Hospital> getHospitals() throws SQLException {
        Dao<Hospital,?> dao = this.getHelper().getDao(Hospital.class);
        return dao.queryForAll();
    }
    
    private DatabaseHelper getHelper() {
        if (this.databaseHelper == null) {
            this.databaseHelper = (DatabaseHelper) OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return this.databaseHelper;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);

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

	@Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
            	MainApp.getInstance().logoff(this);
            	return true;
            case R.id.menu_sync:
            	showProgressDialog("Sincronizando...");
            	startService(new Intent(this, MainService_.class).setAction(MainService.UPDATE_HOSPITALS));
            	return true;
        }
        return false;
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
    
    BroadcastReceiver broadReceiver = new BroadcastReceiver(){
    	
    	@Override
    	public void onReceive(Context context, Intent intent) {
    		dismissProgressDialog();
    		String extra = intent.getStringExtra(MainService.ERROR);
			if (extra != null) {
				showAlertDialog(extra.isEmpty() ? "Erro desconhecido na sincronização, tente novamente" : extra);
			}
    		update();
    	}
	};

    @Override
    protected void onStart() {
    	super.onStart();
		LocalBroadcastManager.getInstance(this).registerReceiver(broadReceiver, new IntentFilter(MainService.UPDATE_HOSPITALS));
    };
    
    @Override
    protected void onStop() {
    	super.onStop();
    	LocalBroadcastManager.getInstance(this).unregisterReceiver(broadReceiver);
    }
    
}
