package br.ufba.hupes.hospitaladmissionforram.fragment;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.widget.EditText;
import android.widget.LinearLayout;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.fragment.NovoMedicamentoFragment.NovoMedicamentoListener;
import br.ufba.hupes.hospitaladmissionforram.model.Cause;
import br.ufba.hupes.hospitaladmissionforram.model.Medication;
import br.ufba.hupes.hospitaladmissionforram.model.RAM;
import br.ufba.hupes.hospitaladmissionforram.view.ViewMedication;
import br.ufba.hupes.hospitaladmissionforram.view.ViewMedication_;

@EFragment(R.layout.frag_ram)
public class RAMFragment extends NewResearchFragment implements NovoMedicamentoListener {

	@ViewById
	LinearLayout medications;
	
	@ViewById
	EditText cause;

	@ViewById
	EditText comorbidity;

	@ViewById
	EditText otherCauses;

    @ViewById
    EditText initialDate;
    
    @ViewById
    EditText finalDate;
	
    ArrayList<Medication> medicationList = new ArrayList<Medication>();
	
    @AfterViews
    public void init() {
    	RAM ram = research.getRam();
    	if (ram != null) {
			cause.setText(ram.getCause().getDisease());
			otherCauses.setText(ram.getOtherCauses());
			comorbidity.setText(ram.getComorbidity().getDisease());
			initialDate.setText(ram.getInitialDate());
			finalDate.setText(ram.getFinalDate());
			ArrayList<Medication> suspects = ram.getSuspects();
			if (suspects != null) {
				medicationList = suspects;
				showMedications();
			}
    	}
    }

    public boolean save() {
    	RAM ram = new RAM();
    	//TODO suspects must have at least 1 element
    	ram.setSuspects(medicationList);
    	ram.setComorbidity(new Cause(comorbidity.getText().toString(), null));
    	ram.setOtherCauses(otherCauses.getText().toString());
    	ram.setCause(new Cause(cause.getText().toString(), null));
    	ram.setSuspects(medicationList);
    	ram.setInitialDate(initialDate.getText().toString());
    	ram.setFinalDate(finalDate.getText().toString());
    	
    	research.setRam(ram);
    	ram.setResearch(research);
    	
    	return true;
    }
    
    @Click
    public void addMedication() {
    	NovoMedicamentoFragment fragment = NovoMedicamentoFragment_.builder().build();
    	fragment.setListener(this);
    	fragment.show(getFragmentManager(), "NovoMedicamento");
    }

	@Override
	public void saveMedication(Medication med) {
		ViewMedication view = ViewMedication_.build(getActivity());
		view.bind(med);
		medications.addView(view);
		medicationList.add(med);
	}

	public void showMedications(){
		for (Medication med : medicationList) {
			ViewMedication view = ViewMedication_.build(getActivity());
			view.bind(med);
			medications.addView(view);
		}
	}
}