package br.ufba.hupes.hospitaladmissionforram.activity;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.adapter.PagerAdapter;
import br.ufba.hupes.hospitaladmissionforram.fragment.NewResearchFragment;
import br.ufba.hupes.hospitaladmissionforram.helper.DatabaseHelper;
import br.ufba.hupes.hospitaladmissionforram.helper.Validator;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;
import br.ufba.hupes.hospitaladmissionforram.model.Status;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;


/**
 * Created by denis on 09/09/13.
 */
public class NewResearch extends FragmentActivity {

	private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.edit();
    }

    private void edit() {
        this.setContentView(R.layout.form_research);
        this.createTab();
//        this.load();
    }

    private void createTab() {
        pager  = (ViewPager) findViewById(R.id.pager);
    	pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                getActionBar().setSelectedNavigationItem(position);
            }
        });

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        ActionBar actionBar = getActionBar();
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                int position = tab.getPosition();
                pager.setCurrentItem(position);
            }
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }
        };
        String[] tabNames = {"Dados\nGerais", "RAM", "Outras", "Info\nAdicionais", "Algoritmo"};
        for (int i = 0; i < tabNames.length; i++) {
            actionBar.addTab(actionBar.newTab().setText(tabNames[i]).setTabListener(tabListener));
        }
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
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

    public void showBirthdayPickerDialog(View v) throws SQLException {
        EditText birthday = (EditText) this.findViewById(R.id.birthday);
        DialogFragment newFragment = new DatePickerFragment(birthday);
        newFragment.show(this.getFragmentManager(), "timePicker");
    }

    public void showAdmissionPickerDialog(View v) throws SQLException {
        EditText admission = (EditText) this.findViewById(R.id.admission);
        DialogFragment newFragment = new DatePickerFragment(admission);
        newFragment.show(this.getFragmentManager(), "timePicker");
    }

    public void showHeightPickerDialog(View v) {
        final NumberPicker np = new NumberPicker(this);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np.setMinValue(10);
        np.setMaxValue(260);
        np.setValue(169);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(np);
        alert.setTitle("Selecione a Altura (cm): ");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                EditText height = (EditText) NewResearch.this.findViewById(R.id.height);
                height.setText(np.getValue() + "");
            }
        });

        alert.show();
    }

    public void showWeightPickerDialog(View v) {
        final NumberPicker np = new NumberPicker(this);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np.setMinValue(1);
        np.setMaxValue(200);
        np.setValue(69);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(np);
        alert.setTitle("Selecione o Peso (kg): ");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                EditText weight = (EditText) NewResearch.this.findViewById(R.id.weight);
                weight.setText(np.getValue() + "");
            }
        });

        alert.show();
    }

    public void save() {
        Fragment[] fragments = pagerAdapter.getFragments();
        for (Fragment fragment : fragments) {
            try {
                ((NewResearchFragment) fragment).save();
            } catch (Exception ex) {}
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private EditText view;

        public DatePickerFragment() {

        }

        public DatePickerFragment(EditText view) {
            this.view = view;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();

            if(this.view.getText() != null) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    c.setTime(dateFormat.parse(this.view.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            this.view.setText(day + "/" + (month + 1) + "/" + year);
        }
    }
}
