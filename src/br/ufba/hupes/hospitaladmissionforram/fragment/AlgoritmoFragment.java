package br.ufba.hupes.hospitaladmissionforram.fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.widget.EditText;
import br.ufba.hupes.hospitaladmissionforram.R;

@EFragment(R.layout.frag_algoritmos)
public class AlgoritmoFragment extends NewResearchFragment {

	@ViewById
	EditText algNaranjo;
	
	@ViewById
	EditText algOms;
	
	@ViewById
	EditText algRucam;
	
	@ViewById
	EditText algUe;

	@AfterViews
	public void init() {

	}

	public boolean save() {
		research.setAlgNaranjo(algNaranjo.getText().toString());
		research.setAlgOMS(algOms.getText().toString());
		research.setAlgRUCAM(algRucam.getText().toString());
		research.setAlgUE(algUe.getText().toString());
		return true;
	}
}