package br.ufba.hupes.hospitaladmissionforram.fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import br.ufba.hupes.hospitaladmissionforram.R;

@EFragment(R.layout.frag_ram)
public class RAMFragment extends NewResearchFragment {

    @ViewById
    EditText medication;
    @ViewById
    Spinner way;
    @ViewById
    EditText dose;
    @ViewById
    EditText indication;
    @ViewById
    EditText initialDate;
    @ViewById
    EditText finalDate;

//    @StringArrayRes(R.array.colors)
//    String[] arrayColors;
//    private List<String> colors;

    @AfterViews
    public void init() {
    }

    public void save() {

    }
}