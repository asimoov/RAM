package br.ufba.hupes.hospitaladmissionforram.fragment;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.fragment.NovoMedicamentoFragment.NovoMedicamentoListener;
import br.ufba.hupes.hospitaladmissionforram.model.Cause;
import br.ufba.hupes.hospitaladmissionforram.model.Medication;
import br.ufba.hupes.hospitaladmissionforram.model.RAM;
import br.ufba.hupes.hospitaladmissionforram.view.ViewMedication;
import br.ufba.hupes.hospitaladmissionforram.view.ViewMedication_;
import br.ufba.hupes.hospitaladmissionforram.view.ViewMedication.Listener;

@EFragment(R.layout.frag_ram)
public class RAMFragment extends NewResearchFragment implements NovoMedicamentoListener, Listener {

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
    	//TODO suspects must have at least 1 element
    	if (medicationList.size() == 0) {
    		Toast.makeText(getActivity(), "Você precisa adicionar pelo menos um medicamento", Toast.LENGTH_SHORT).show();
    		return false;
    	}
    	
    	RAM ram = new RAM();
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
		view.bind(med, this);
		view.setTag(medicationList.size()); //Guarda a posicao da view
		medications.addView(view);
		medicationList.add(med);
	}

	@Override
	public void onUpdate(int position) {
		
	}
	
	@Override
	public void onDelete(int position) {
		medicationList.remove(position);
		medications.removeViewAt(position);
	}
	
	public void showMedications(){
		for (int i = 0, length = medicationList.size(); i < length; i++) {
			Medication med = medicationList.get(i);
			ViewMedication view = ViewMedication_.build(getActivity());
			view.bind(med, this);
			view.setTag(i);
			medications.addView(view);
		}
	}
}