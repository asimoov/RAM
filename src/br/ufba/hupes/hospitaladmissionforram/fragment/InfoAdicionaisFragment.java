package br.ufba.hupes.hospitaladmissionforram.fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import br.ufba.hupes.hospitaladmissionforram.R;

@EFragment(R.layout.frag_info_adic)
public class InfoAdicionaisFragment extends NewResearchFragment {

	@ViewById(R.id.chk_med_anterior)
	CheckBox checkMedAnterior;

	@ViewById(R.id.chk_desenv_reacao)
	CheckBox checkReacao;
	
	@ViewById(R.id.chk_reacao_passada)
	CheckBox checkReacaoPassada;
	
	@ViewById(R.id.chk_plantas_med)
	CheckBox checkPlantasMed;

	@ViewById
	View viewReacaoPassada;

	@ViewById
	View viewPlantasMed;
	
    @AfterViews
    public void init() {
    	checkMedAnterior.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				checkReacao.setVisibility(isChecked ? View.VISIBLE : View.GONE);
			}
		});
    	checkReacaoPassada.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				viewReacaoPassada.setVisibility(isChecked ? View.VISIBLE : View.GONE);
			}
		});
    	checkReacaoPassada.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				viewPlantasMed.setVisibility(isChecked ? View.VISIBLE : View.GONE);
			}
		});
    }
    
    

    public void save() {

    }
}