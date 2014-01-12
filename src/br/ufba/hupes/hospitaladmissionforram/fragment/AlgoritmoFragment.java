package br.ufba.hupes.hospitaladmissionforram.fragment;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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

	@ViewById
	LinearLayout linear;

	@AfterViews
	public void init() {
		algNaranjo.setText(research.getAlgNaranjo());
		algOms.setText(research.getAlgOMS());
		algRucam.setText(research.getAlgRUCAM());
		algUe.setText(research.getAlgUE());
		
		if (!research.isOpen()) {
	        ArrayList<View> list = linear.getTouchables();
	        for (View view : list) {
				view.setEnabled(false);
			}
        }
	}

	public boolean save() {
		research.setAlgNaranjo(algNaranjo.getText().toString());
		research.setAlgOMS(algOms.getText().toString());
		research.setAlgRUCAM(algRucam.getText().toString());
		research.setAlgUE(algUe.getText().toString());
		return true;
	}
}