package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import java.util.List;
import java.util.UUID;

import br.ufba.hupes.hospitaladmissionforram.DatabaseHelper;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.ResearchActivity;
import br.ufba.hupes.hospitaladmissionforram.adapter.ResearchAdapter;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;

@SuppressLint("NewApi")
public class ListResearches extends Fragment {

    private DatabaseHelper databaseHelper;
    private View view;

    public ListResearches() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.list_researches, null);

        this.update();
        return this.view;
    }

    private void update() {
        final ListView listView = (ListView) this.view.findViewById(R.id.list_researches);
        final TextView header = (TextView) this.view.findViewById(R.id.header);

        try {
            Hospital hospital = this.getHospital();
            header.setText(hospital.getAcronym());

            final List<Research> researches = new ArrayList<Research>(hospital.getResearches());
            final ResearchAdapter adapter = new ResearchAdapter(this.getActivity(),
                    R.layout.item_research, researches);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    Research research = researches.get(position);
                    Intent intent = new Intent(getActivity(), ResearchActivity.class);
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
        UUID id = UUID.fromString(this.getArguments().getString("HOSPITAL_ID"));
        return (Hospital) HospitalDao.queryForId(id);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                try {
                    Intent intent = new Intent(getActivity(), ResearchActivity.class);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case Activity.RESULT_OK:
                this.update();
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_research, menu);
    }

    private DatabaseHelper getHelper() {
        if (this.databaseHelper == null) {
            this.databaseHelper = (DatabaseHelper) OpenHelperManager.getHelper(this.getActivity(), DatabaseHelper.class);
        }
        return this.databaseHelper;
    }

}
