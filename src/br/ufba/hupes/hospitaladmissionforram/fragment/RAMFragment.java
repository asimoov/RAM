package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.helper.Validator;
import br.ufba.hupes.hospitaladmissionforram.model.Research;
import br.ufba.hupes.hospitaladmissionforram.model.Status;

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