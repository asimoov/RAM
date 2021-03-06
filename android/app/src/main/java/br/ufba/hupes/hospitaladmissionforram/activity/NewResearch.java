package br.ufba.hupes.hospitaladmissionforram.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.adapter.PagerAdapter;
import br.ufba.hupes.hospitaladmissionforram.fragment.NewResearchFragment;
import br.ufba.hupes.hospitaladmissionforram.model.UserHolder_;

@EActivity
public class NewResearch extends FragmentActivity {

	private ViewPager pager;
    private PagerAdapter pagerAdapter;

    private boolean isNewResearch;

	@Pref
	UserHolder_ userHolder;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NewResearchFragment.resetResearch();
        this.setContentView(R.layout.form_research);
        this.createTab();
        isNewResearch = getIntent().getStringExtra("RESEARCH_ID") == null;
    }

    private void createTab() {
        pager  = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(5);
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
        int length = tabNames.length - (userHolder.admin().get() ? 0 : 1);
		for (int i = 0; i < length; i++) {
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
    	if (getIntent().getBooleanExtra("OPEN", true)) {
    		getMenuInflater().inflate(R.menu.save_research, menu);
    	}

        return true;
    }

    public void showPickerDialog(View v) throws SQLException {
        DialogFragment newFragment = new DatePickerFragment((TextView) v);
        newFragment.show(this.getSupportFragmentManager(), "timePicker");
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
        boolean readyToSave = true;
        NewResearchFragment researchFragment = null;
        int i;
        int length = fragments.length - (userHolder.admin().get() ? 0 : 1);
		for (i = 0; i < length; i++) {
		   try {
            	if (readyToSave) {
            		if (fragments[i] != null || isNewResearch) {
	            		researchFragment = (NewResearchFragment) fragments[i];
						readyToSave = researchFragment.save();
            		}
				} else break;
            } catch (Exception ex) {
            	ex.printStackTrace();
            	readyToSave = false;
            }
        }
		
        if (readyToSave) {
        	try {
				researchFragment.saveResearchOnDatabase();
				finish();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        } else {
        	pager.setCurrentItem(i-1);
        }
    }

    @SuppressLint({ "ValidFragment", "SimpleDateFormat" })
	public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private TextView view;

        public DatePickerFragment() {

        }

        public DatePickerFragment(TextView view) {
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
    
    @Override
    public void onBackPressed() {
    	if (getIntent().getBooleanExtra("OPEN", true)) {
    		AlertDialog.Builder alert = new AlertDialog.Builder(this);
	        alert.setTitle("Deseja sair? Você perderá todos os dados não salvos");
	        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            	NewResearch.super.onBackPressed();
	            }
	        });
	        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            	dialog.dismiss();
	            }
	        });
	        alert.show();
    	} else {
    		super.onBackPressed();
    	}
    }
}
