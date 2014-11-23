package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.Arrays;

import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.activity.NewResearch.DatePickerFragment;
import br.ufba.hupes.hospitaladmissionforram.helper.Validator;
import br.ufba.hupes.hospitaladmissionforram.model.Medication;

@EFragment(R.layout.table_medications)
public class NovoMedicamentoFragment extends DialogFragment {

	@FragmentArg
	Medication medicationItem;
	
	@FragmentArg
	boolean showCheckPlant;
	
    @ViewById
    EditText name;
	
    @ViewById
    TextView labelName;
    
    @ViewById
    Spinner way;
    @ViewById
    EditText dose;
    @ViewById
    Spinner doseType;
    @ViewById
    EditText indication;
    @ViewById
    EditText initialDate;
    @ViewById
    EditText finalDate;
	
	@ViewById(R.id.chk_plantas_med)
	CheckBox checkPlantasMed;
	
	@ViewById
	View linearMed;

	@ViewById
	View linearPlantas;

	@ViewById(R.id.planta_uso)
	EditText plantaUso;
	
	@ViewById(R.id.planta_parte)
	AutoCompleteTextView plantaParte;

	@ViewById(R.id.planta_preparo)
	AutoCompleteTextView plantaPreparo;

	@ViewById(R.id.planta_dose)
	EditText plantaDose;

	@ViewById(R.id.planta_modo_obtencao)
	AutoCompleteTextView plantaObtencao;
	
    @StringArrayRes
    String[] ways;

    @ViewById
    View btDeleteFinalDate;

    private NovoMedicamentoListener listener;

    @AfterViews
    public void init() {
    	try {
			getDialog().setTitle("Adicionar Medicamento");
			getDialog().setCanceledOnTouchOutside(false);
		} catch (Exception e) {
		}
        finalDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btDeleteFinalDate.setVisibility(editable.toString().length() > 0 ? View.VISIBLE : View.GONE);
            }
        });

        checkPlantasMed.setVisibility(showCheckPlant ? View.VISIBLE : View.GONE);
    	
    	if (medicationItem != null) {
    		name.setText(medicationItem.getName());
    		try {
				way.setSelection(Arrays.binarySearch(ways,medicationItem.getWay()));
			} catch (Exception e) {}
    		dose.setText(medicationItem.getDose());
    		if (medicationItem.getDose().endsWith("ml")) {
    			doseType.setSelection(1);
    		}
    		indication.setText(medicationItem.getIndication());
    		initialDate.setText(medicationItem.getStart());
    		finalDate.setText(medicationItem.getEnd());
    	}
    	
    	checkPlantasMed.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				linearMed.setVisibility(isChecked ? View.GONE: View.VISIBLE);
				linearPlantas.setVisibility(isChecked ? View.VISIBLE : View.GONE);
				labelName.setText(isChecked ? "Planta" : "Medicamento");
			}
		});
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.planta_partes));
    	plantaParte.setAdapter(adapter);
    	adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.planta_modo_preparo));
    	plantaPreparo.setAdapter(adapter);
    	adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.planta_modo_obtencao));
    	plantaObtencao.setAdapter(adapter);
    	
    	OnFocusChangeListener focusListener = new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					((AutoCompleteTextView)v).showDropDown();
				}
			}
		};
		plantaParte.setOnFocusChangeListener(focusListener);
		plantaPreparo.setOnFocusChangeListener(focusListener);
		plantaObtencao.setOnFocusChangeListener(focusListener);
    }

    @Click
    void btDeleteFinalDate() {
        finalDate.setText("");
    }

    @Click
    public void btCancelar() {
    	dismiss();
	}

    @Click
    public void btOk() {
		if (isValid()) {
			Medication med;
			if (checkPlantasMed.isChecked()) {
				med = new Medication(name.getText().toString(),
						plantaUso.getText().toString(),
						plantaParte.getText().toString(),
						plantaPreparo.getText().toString(),
						plantaDose.getText().toString(),
						plantaObtencao.getText().toString(),
						initialDate.getText().toString(), 
						finalDate.getText().toString());
			} else {  
				med = new Medication(name.getText().toString(),
					way.getSelectedItem().toString(),
					dose.getText().toString().trim() + " " + doseType.getSelectedItem().toString(), 
					indication.getText().toString(),
					initialDate.getText().toString(), 
					finalDate.getText().toString());
			}
			if (listener != null)
				listener.saveMedication(med);
			dismiss();
		}
	}

	private boolean isValid() {
		if (checkPlantasMed.isChecked()) {
			return (Validator.validateNotNull(name,"O nome da planta não pode estar em branco")
					&& Validator.validateNotNull(plantaUso, "O uso não pode estar em branco")
					&& Validator.validateNotNull(plantaParte, "A parte usada não pode estar em branco")
					&& Validator.validateNotNull(plantaPreparo, "A forma de preparo não pode estar em branco")
					&& Validator.validateNotNull(plantaDose, "A dosagem não pode estar em branco")
					&& Validator.validateNotNull(plantaObtencao, "A forma de obtenção não pode estar em branco")
					&& Validator.validateDateFormat(initialDate, "dd/MM/yyyy","A data inicial está no formato errado")
					&& Validator.validateDateFormat(finalDate.getText().length() > 0 ? finalDate : null, "dd/MM/yyyy",true, "A data final está no formato errado")
					&& Validator.validateDateRange(initialDate, finalDate.getText().length() > 0 ? finalDate : null,"dd/MM/yyyy",true, "A data final está no formato errado"));
		} else {
			return (Validator.validateNotNull(name,"O medicamento não pode estar em branco")
					&& Validator.validateNotNull(dose,"A dose não pode estar em branco")
					&& Validator.validateNotNull(indication,"A indicação não pode estar em branco")
					&& Validator.validateDateFormat(initialDate, "dd/MM/yyyy","A data inicial está no formato errado")
					&& Validator.validateDateFormat(finalDate.getText().length() > 0 ? finalDate : null, "dd/MM/yyyy",true, "A data final está no formato errado")
					&& Validator.validateDateRange(initialDate, finalDate.getText().length() > 0 ? finalDate : null,"dd/MM/yyyy",true, "A data final está no formato errado"));
		}
	}

	public void setListener(NovoMedicamentoListener listener) {
		this.listener = listener;
	}

	@Click
	void initialDate() {
		showPickerDialog(initialDate);
	}

	@Click
	void finalDate() {
		showPickerDialog(finalDate);
	}
	
    public void showPickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment((TextView) v);
        newFragment.show(this.getFragmentManager(), "timePicker");
    }

	interface NovoMedicamentoListener {
		void saveMedication(Medication med);
	}
}