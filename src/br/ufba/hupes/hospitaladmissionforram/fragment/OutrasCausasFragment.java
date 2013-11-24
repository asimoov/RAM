package br.ufba.hupes.hospitaladmissionforram.fragment;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.widget.LinearLayout;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.fragment.NovoMedicamentoFragment.NovoMedicamentoListener;
import br.ufba.hupes.hospitaladmissionforram.model.Medication;
import br.ufba.hupes.hospitaladmissionforram.view.ViewMedication;
import br.ufba.hupes.hospitaladmissionforram.view.ViewMedication_;

@EFragment(R.layout.frag_outras_causas)
public class OutrasCausasFragment extends NewResearchFragment implements NovoMedicamentoListener {

	@ViewById
	LinearLayout medications;

	ArrayList<Medication> medicationList = new ArrayList<Medication>();

	@AfterViews
	public void init() {
	}

	public void save() {

	}

	@Click
	public void addMedication() {
		NovoMedicamentoFragment fragment = NovoMedicamentoFragment_.builder()
				.build();
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
}