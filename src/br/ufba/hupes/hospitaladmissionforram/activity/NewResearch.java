package br.ufba.hupes.hospitaladmissionforram.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import br.ufba.hupes.hospitaladmissionforram.helper.DatabaseHelper;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.helper.Validator;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;


/**
 * Created by denis on 09/09/13.
 */
public class NewResearch extends Activity {

    private DatabaseHelper databaseHelper;

    private Research research;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.research = this.getResearch();
            this.edit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void edit() {
        this.setContentView(R.layout.form_research);
        this.createTab();
        this.load();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_save:
                this.save();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_research, menu);

        return true;
    }

    public void showTimePickerDialog(View v) throws SQLException {
        DialogFragment newFragment = new DatePickerFragment(this.getResearch());
        newFragment.show(this.getFragmentManager(), "timePicker");
    }

    public void load() {
        this.getActionBar().setTitle(research.getHospital().getAcronym());

        EditText name = (EditText) this.findViewById(R.id.name);
        name.setText(research.getName());

        EditText handbook = (EditText) this.findViewById(R.id.handbook);
        handbook.setText(research.getHandbook());

        EditText bed = (EditText) this.findViewById(R.id.bed);
        bed.setText(research.getBed());

        try {
            EditText birthday = (EditText) this.findViewById(R.id.birthday);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            birthday.setText(dateFormat.format(research.getBirthday()));
        } catch (NullPointerException e) {
        }
    }

    public void save() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        EditText name = (EditText) this.findViewById(R.id.name);
        EditText handbook = (EditText) this.findViewById(R.id.handbook);
        EditText bed = (EditText) this.findViewById(R.id.bed);
        EditText birthday = (EditText) this.findViewById(R.id.birthday);

        if (Validator.validateNotNull(name, "O nome não pode estar em branco") &&
            Validator.validateNotNull(handbook, "O prontuário não pode estar em branco") &&
            Validator.validateNotNull(bed, "O leito não pode estar em branco") &&
            Validator.validateDateFormat(birthday, "dd/MM/yyyy", "A data de nascimento estar no formato errada")) {
            try {
                research.setName(name.getText().toString());
                research.setHandbook(handbook.getText().toString());
                research.setBed(bed.getText().toString());
                research.setBirthday(dateFormat.parse(birthday.getText().toString()));

                Dao dao = getHelper().getDao(Research.class);
                dao.createOrUpdate(research);

                this.setResult(Activity.RESULT_OK);
                Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Research getResearch() throws SQLException {
        String researchId = getIntent().getExtras().getString("RESEARCH_ID");
        if (researchId != null) {
            Dao dao = this.getHelper().getDao(Research.class);
            UUID id = UUID.fromString(researchId);
            return (Research) dao.queryForId(id);
        }

        String hospitalId = getIntent().getExtras().getString("HOSPITAL_ID");
        if(hospitalId != null) {
            Dao dao = this.getHelper().getDao(Hospital.class);
            UUID id = UUID.fromString(hospitalId);
            Hospital hospital = (Hospital) dao.queryForId(id);

            Research research = new Research();
            research.setHospital(hospital);

            return research;
        }

        return null;
    }

    private DatabaseHelper getHelper() {
        if (this.databaseHelper == null) {
            this.databaseHelper = (DatabaseHelper) OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }

        return this.databaseHelper;
    }

    static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private Research research;

        public DatePickerFragment(Research research) {
            this.research = research;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();

            if(research.getBirthday() != null) {
                c.setTime(research.getBirthday());
            }

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            EditText birthday = (EditText) getActivity().findViewById(R.id.birthday);
            birthday.setText(day + "/" + (month + 1) + "/" + year);
        }
    }
}
