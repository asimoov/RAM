package br.ufba.hupes.hospitaladmissionforram.fragment;


import java.util.Arrays;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.activity.NewResearch.DatePickerFragment;
import br.ufba.hupes.hospitaladmissionforram.helper.Validator;
import br.ufba.hupes.hospitaladmissionforram.model.Medication;

@EFragment(R.layout.table_medications)
public class NovoMedicamentoFragment extends DialogFragment {

	@FragmentArg
	Medication medicationItem;
	
    @ViewById
    EditText medication;
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

    @StringArrayRes
    String[] ways;

	private NovoMedicamentoListener listener;
    
    @AfterViews
    public void init() {
    	try {
			getDialog().setTitle("Adicionar Medicamento");
		} catch (Exception e) {
		}
    	
    	if (medicationItem != null) {
    		medication.setText(medicationItem.getMedication());
    		try {
				way.setSelection(Arrays.binarySearch(ways,medicationItem.getWay()));
			} catch (Exception e) {}
    		dose.setText(medicationItem.getDose());
    		indication.setText(medicationItem.getIndication());
    		initialDate.setText(medicationItem.getInitialDate());
    		finalDate.setText(medicationItem.getFinalDate());
    	}
    	
    }

    @Click
    public void btCancelar() {
    	dismiss();
	}

    @Click
    public void btOk() {
		if (isValid()) {
			Medication med = new Medication(medication.getText().toString(),
					way.getSelectedItem().toString(),
					dose.getText().toString().trim() + " " + doseType.getSelectedItem().toString(), 
					indication.getText().toString(),
					initialDate.getText().toString(), 
					finalDate.getText().toString());
			if (listener != null)
				listener.saveMedication(med);
    		dismiss();
		}
	}

	private boolean isValid() {
		return (Validator.validateNotNull(medication, "O medicamento não pode estar em branco") &&
                Validator.validateNotNull(dose, "A dose não pode estar em branco") &&
                Validator.validateNotNull(indication, "A indicação não pode estar em branco") &&
                Validator.validateDateFormat(initialDate, "dd/MM/yyyy", "A data inicial está no formato errado") &&
                Validator.validateDateFormat(finalDate, "dd/MM/yyyy", "A data final está no formato errado")&&
                Validator.validateDateRange(initialDate, finalDate, "dd/MM/yyyy", "A data final está no formato errado"));
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