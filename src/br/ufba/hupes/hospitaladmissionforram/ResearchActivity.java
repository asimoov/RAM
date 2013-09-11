package br.ufba.hupes.hospitaladmissionforram;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.SearchManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TimePicker;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;

import static com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;

/**
 * Created by denis on 09/09/13.
 */
public class ResearchActivity extends Activity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Research research = this.getResearch(getIntent().getExtras().getString("RESEARCH_ID"));
            this.edit(research);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void edit(Research research) {
        this.setContentView(R.layout.form_research);
        this.createTab();
    }

    private void createTab() {
        TabHost tabs = (TabHost) this.findViewById(R.id.tabHost);
        tabs.setup();

        TabHost.TabSpec tspec1 = tabs.newTabSpec("First Tab");
        tspec1.setIndicator("Anamnese");
        tspec1.setContent(R.id.tab1);
        tabs.addTab(tspec1);

        TabHost.TabSpec tspec2 = tabs.newTabSpec("Second Tab");
        tspec2.setIndicator("Suspeitos");
        tspec2.setContent(R.id.tab2);
        tabs.addTab(tspec2);

        TabHost.TabSpec tspec3 = tabs.newTabSpec("Third Tab");
        tspec3.setIndicator("Causas");
        tspec3.setContent(R.id.tab3);
        tabs.addTab(tspec3);

        TabHost.TabSpec tspec4 = tabs.newTabSpec("Fourth Tab");
        tspec4.setIndicator("Outros");
        tspec4.setContent(R.id.tab4);
        tabs.addTab(tspec4);

        TabHost.TabSpec tspec5 = tabs.newTabSpec("Fifth Tab");
        tspec5.setIndicator("Extras");
        tspec5.setContent(R.id.tab5);
        tabs.addTab(tspec5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_research, menu);

        return true;
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(this.getFragmentManager(), "timePicker");
    }

    public void load(Research research) {
        EditText name = (EditText) this.findViewById(R.id.name);
        name.setText(research.getName());

        EditText handbook = (EditText) this.findViewById(R.id.handbook);
        handbook.setText(research.getHandbook());

        EditText bed = (EditText) this.findViewById(R.id.bed);
        bed.setText(research.getBed());

        EditText birthday = (EditText) this.findViewById(R.id.birthday);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        birthday.setText(dateFormat.format(research.getBirthday()));
    }

    public void save(Research research) {
        EditText name = (EditText) this.findViewById(R.id.name);
        research.setName(name.getText().toString());

        EditText handbook = (EditText) this.findViewById(R.id.handbook);
        research.setHandbook(handbook.getText().toString());

        EditText bed = (EditText) this.findViewById(R.id.bed);
        research.setBed(bed.getText().toString());

        try {
            EditText birthday = (EditText) this.findViewById(R.id.birthday);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            research.setBirthday(dateFormat.parse(birthday.getText().toString()));
        } catch (ParseException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        try {
            Dao dao = getHelper().getDao(Research.class);
            dao.createOrUpdate(research);
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private Research getResearch(String research_id) throws SQLException {
        Dao dao = this.getHelper().getDao(Research.class);
        UUID id = UUID.fromString(research_id);
        return (Research) dao.queryForId(id);
    }

    private DatabaseHelper getHelper() {
        if (this.databaseHelper == null) {
            this.databaseHelper = (DatabaseHelper) OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return this.databaseHelper;
    }

    static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            EditText birthday = (EditText) getActivity().findViewById(R.id.birthday);
            birthday.setText(day + "/" + month + "/" + year);
        }
    }
}
