package br.ufba.hupes.hospitaladmissionforram.fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import br.ufba.hupes.hospitaladmissionforram.R;

@EFragment(R.layout.frag_info_adic)
public class InfoAdicionaisFragment extends NewResearchFragment {

	@ViewById
	CheckBox chkAnotherLocation;

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
	
	@ViewById
	View viewPlantasMedFreq;

	@ViewById
	Spinner spnTabagismo;

	@ViewById
	Spinner spnEtilismo;

	@ViewById
	Spinner spinnerResultado;

	@ViewById
	View viewTempoTabagismo;
	
	@ViewById
	View viewTempoEtilismo;
	
	@ViewById
	View viewDataObito;

	@ViewById
	EditText editReacaoPassada;

	@ViewById
	EditText editPlantasMed;

	@ViewById
	EditText editPlantasMedFreq;

	@ViewById
	Spinner spnTempoTabagismo;

	@ViewById
	Spinner spnTempoEtilismo;

	@ViewById
	CheckBox chkCocaina, chkCrack, chkMaconha, chkLsd;

	@ViewById
	Spinner spnTratamento;

	@ViewById
	EditText editDataObito;
	
	@ViewById
	EditText editSequelas;

	@ViewById
	Spinner spnGravidade;
	
	@ViewById
	TextView editDataAlta;

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
    	
    	checkPlantasMed.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				viewPlantasMed.setVisibility(isChecked ? View.VISIBLE : View.GONE);
				viewPlantasMedFreq.setVisibility(isChecked ? View.VISIBLE : View.GONE);
			}
		});

    	spnTabagismo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v, int position, long arg3) {
				viewTempoTabagismo.setVisibility(position > 0 ? View.VISIBLE : View.GONE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
    	
    	spnEtilismo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v, int position, long arg3) {
				viewTempoEtilismo.setVisibility(position > 0 ? View.VISIBLE : View.GONE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
    	
    	spinnerResultado.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v, int position, long arg3) {
				viewDataObito.setVisibility(position == adapter.getCount()-1 ? View.VISIBLE : View.GONE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
    }
    
    @Override
    public boolean save() {
    	research.setAnotherLocation(chkAnotherLocation.isChecked());
    	research.setExposicaoPrevia(checkMedAnterior.isChecked());
    	research.setDesenvolveuReacao(checkMedAnterior.isChecked() && checkReacao.isChecked());
    	research.setReacoesAdversas(editReacaoPassada.getText().toString());
    	research.setPlantasMedicinais(editPlantasMed.getText().toString());
    	research.setFreqPlantasMedicinais(editPlantasMedFreq.getText().toString());

    	if (spnTabagismo.getSelectedItemPosition() > 0) {
    		research.setTabagismo(spnTabagismo.getSelectedItem().toString());
    		research.setTempoTabagismo(spnTempoTabagismo.getSelectedItem().toString());
		}

    	if (spnEtilismo.getSelectedItemPosition() > 0) {
    		research.setEtilismo(spnEtilismo.getSelectedItem().toString());
    		research.setTempoEtilismo(spnTempoEtilismo.getSelectedItem().toString());
		}
    	
    	research.setUsaCrack(chkCrack.isChecked());
    	research.setUsaCocaina(chkCocaina.isChecked());
    	research.setUsaMaconha(chkMaconha.isChecked());
    	research.setUsaLSD(chkLsd.isChecked());
    	
    	research.setSequels(editSequelas.getText().toString());
    	research.setResult(spinnerResultado.getSelectedItem().toString());
    	research.setDischargeDate(editDataAlta.getText().toString());
    	research.setGravity(spnGravidade.getSelectedItem().toString());
    	return true;
    }
}