package br.ufba.hupes.hospitaladmissionforram.fragment;

import java.util.ArrayList;
import java.util.Arrays;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import br.ufba.hupes.hospitaladmissionforram.R;

@EFragment(R.layout.frag_algoritmos)
public class AlgoritmoFragment extends NewResearchFragment {

	@ViewById
    Spinner algNaranjo;
	
	@ViewById
    Spinner algOms;
	
	@ViewById
    Spinner algRucam;
	
	@ViewById
    Spinner algUe;

	@ViewById
	LinearLayout linear;

    @StringArrayRes(R.array.alg_naranjo)
    String[] arrayNarajo;

    @StringArrayRes(R.array.alg_oms)
    String[] arrayOMS;

    @StringArrayRes(R.array.alg_ue)
    String[] arrayUE;

    @StringArrayRes(R.array.alg_rucam)
    String[] arrayRucam;

	@AfterViews
	public void init() {
        String s = research.getAlgNaranjo();
        if (s != null && s.length() > 0) {
            algNaranjo.setSelection(Arrays.asList(arrayNarajo).indexOf(s));
        }

        s = research.getAlgOMS();
        if (s != null && s.length() > 0) {
            algOms.setSelection(Arrays.asList(arrayOMS).indexOf(s));
        }

        s = research.getAlgRUCAM();
        if (s != null && s.length() > 0) {
            algRucam.setSelection(Arrays.asList(arrayRucam).indexOf(s));
        }

        s = research.getAlgUE();
        if (s != null && s.length() > 0) {
            algUe.setSelection(Arrays.asList(arrayUE).indexOf(s));
        }
		
		if (!research.isOpen()) {
	        ArrayList<View> list = linear.getTouchables();
	        for (View view : list) {
				view.setEnabled(false);
			}
        }
	}

	public boolean save() {
		research.setAlgNaranjo(algNaranjo.getSelectedItem().toString());
		research.setAlgOMS(algOms.getSelectedItem().toString());
		research.setAlgRUCAM(algRucam.getSelectedItem().toString());
		research.setAlgUE(algUe.getSelectedItem().toString());
		return true;
	}
}