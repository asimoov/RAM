package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @ViewById
    LinearLayout linear;
    
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
        	if (research.getWeight() > 0)
        		weight.setText(String.valueOf(research.getWeight()).replace('.', ','));
        } catch (Exception e) {
        }

        try {
        	if (research.getHeight() > 0)
        		height.setText(String.valueOf(research.getHeight()).replace('.', ','));
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
        
        if (!research.isOpen()) {
	        ArrayList<View> list = linear.getTouchables();
	        for (View view : list) {
				view.setEnabled(false);
			}
        }
    }

    public boolean save() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (Validator.validateNotNull(name, "O nome nao pode estar em branco") &&
                Validator.validateNotNull(handbook, "O prontuário nao pode estar em branco") &&
                Validator.validateNotNull(bed, "O leito nao pode estar em branco") &&
                Validator.validateNotNull(admission, "O Admissao nao pode estar em branco") &&
                Validator.validateDateFormat(birthday, "dd/MM/yyyy", "A data de nascimento está no formato errada") &&
                Validator.validateDateIsAfter("01/01/2014",admission.getText().toString(), "dd/MM/yyyy", admission, "Data de admissão incorreta")) {
            try {
                research.setName(name.getText().toString());
                research.setHandbook(handbook.getText().toString());
                research.setBed(bed.getText().toString());
                research.setBirthday(birthday.getText().toString());
                research.setAdmission(admission.getText().toString());
                research.setWeight(Float.parseFloat(weight.getText().toString().replace(',', '.')));
                research.setHeight(Float.parseFloat(height.getText().toString().replace(',', '.')));
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