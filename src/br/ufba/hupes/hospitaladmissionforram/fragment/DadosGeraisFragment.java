package br.ufba.hupes.hospitaladmissionforram.fragment;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.helper.Validator;

@EFragment(R.layout.frag_dados_gerais)
public class DadosGeraisFragment extends NewResearchFragment {

    @ViewById
    EditText name;
    @ViewById
    EditText handbook;
    @ViewById
    EditText bed;
    @ViewById
    EditText number;
    @ViewById
    RadioButton sexFemale;
    @ViewById
    RadioButton sexMale;
    @ViewById
    EditText weight;
    @ViewById
    EditText height;
    @ViewById
    Spinner color;
    @ViewById
    EditText birthday;
    @ViewById
    EditText admission;
    @ViewById
    EditText unit;

    @StringArrayRes(R.array.colors)
    String[] arrayColors;
    private List<String> colors;

    @AfterViews
    public void init() {
    	weight.addTextChangedListener(new RealNumberWatcher());
    	height.addTextChangedListener(new RealNumberWatcher());
    	
        name.setText(research.getName());
        handbook.setText(research.getHandbook());
        bed.setText(research.getBed());
        number.setText(research.getId().toString());
        if (research.getSex() != null && research.getSex().equals("Feminino")) {
            sexFemale.setChecked(true);
            sexMale.setChecked(false);
        } else {
            sexFemale.setChecked(false);
            sexMale.setChecked(true);
        }

        try {
            weight.setText(String.valueOf(research.getWeight()));
        } catch (Exception e) {
        }

        try {
            height.setText(String.valueOf(research.getHeight()));
        } catch (Exception e) {
        }

        try {
            birthday.setText(research.getBirthday());
        } catch (Exception e) {
        }

        try {
            admission.setText(research.getAdmission());
        } catch (Exception e) {
        }

        colors = Arrays.asList(arrayColors);
        color.setSelection(colors.indexOf(research.getColor()));

        unit.setText(research.getUnit());
    }

    public boolean save() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (Validator.validateNotNull(name, "O nome nao pode estar em branco") &&
                Validator.validateNotNull(handbook, "O prontu√°rio nao pode estar em branco") &&
                Validator.validateNotNull(bed, "O leito nao pode estar em branco") &&
                Validator.validateNotNull(admission, "O Admissao nao pode estar em branco") &&
                Validator.validateDateFormat(birthday, "dd/MM/yyyy", "A data de nascimento est· no formato errada")) {
            try {
                research.setName(name.getText().toString());
                research.setHandbook(handbook.getText().toString());
                research.setBed(bed.getText().toString());
                research.setBirthday(birthday.getText().toString());
                research.setAdmission(admission.getText().toString());
                research.setWeight(Long.parseLong(weight.getText().toString(), 10));
                research.setHeight(Long.parseLong(height.getText().toString(), 10));
                research.setUnit(unit.getText().toString());

                research.setColor(colors.get(color.getSelectedItemPosition()));

                if(sexFemale.isChecked()) {
                    research.setSex("Feminino");
                } else {
                    research.setSex("Masculino");
                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
        	return false;
        }
        return true;
    }
}