package br.ufba.hupes.hospitaladmissionforram.fragment;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.view.View;
import android.widget.LinearLayout;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.fragment.NovoMedicamentoFragment.NovoMedicamentoListener;
import br.ufba.hupes.hospitaladmissionforram.model.Medication;
import br.ufba.hupes.hospitaladmissionforram.view.ViewMedication;
import br.ufba.hupes.hospitaladmissionforram.view.ViewMedication.Listener;
import br.ufba.hupes.hospitaladmissionforram.view.ViewMedication_;

@EFragment(R.layout.frag_outras_causas)
public class OutrasCausasFragment extends NewResearchFragment implements NovoMedicamentoListener, Listener {

	@ViewById
	LinearLayout medications;

	ArrayList<Medication> medicationList = new ArrayList<Medication>();

	@ViewById
	LinearLayout linear;

	@AfterViews
	public void init() {
		ArrayList<Medication> list = research.getMedications();
		if (list != null) {
			medicationList = list;
			showMedications();
		}
		if (!research.isOpen()) {
	        ArrayList<View> list2 = linear.getTouchables();
	        for (View view : list2) {
				view.setEnabled(false);
			}
        }
	}

	public boolean save() {
		research.setMedications(medicationList);
		return true;
	}
    
    @Click
    public void addMedication() {
    	NovoMedicamentoFragment fragment = NovoMedicamentoFragment_.builder().showCheckPlant(true).build();
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