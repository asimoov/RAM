package br.ufba.hupes.hospitaladmissionforram.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.ufba.hupes.hospitaladmissionforram.MainApp;
import br.ufba.hupes.hospitaladmissionforram.MainService;
import br.ufba.hupes.hospitaladmissionforram.MainService_;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.connection.RequestInterceptor;
import br.ufba.hupes.hospitaladmissionforram.helper.Validator;

@EActivity(R.layout.login)
public class LoginActivity extends BaseActivity {

	@Bean
	RequestInterceptor requestInterceptor;
	
	@ViewById
	EditText editLogin;

	@ViewById
	EditText editPass;
	
    BroadcastReceiver broadReceiver = new BroadcastReceiver() {
    	
    	@Override
    	public void onReceive(Context context, Intent intent) {
    		dismissProgressDialog();
    		String extra = intent.getStringExtra(MainService.ERROR);
			if (extra != null) {
				showAlertDialog(extra.isEmpty() ? "Erro no login, tente novamente" : extra);
			} else {
				update();
			}
    	}
	};
	
	@AfterViews
	public void init() {
		String idUser = MainApp.getInstance().getUser().id().get();
		if (idUser != null && idUser.length() > 0) {
			update();
			doLogin();
			return;
		}
		//editLogin.setText("awe@awe.com");
		//editPass.setText("12345678");
	}

	protected void update() {
		startActivity(new Intent(this, MainActivity_.class));
	}

	@Click
	public void btLogin() {
		if (Validator.validateNotNull(editLogin, "Digite o Login.")
				&& Validator.validateNotNull(editPass, "Digite a senha.")) {

			requestInterceptor.setLogin(editLogin.getText().toString());
			requestInterceptor.setPass(editPass.getText().toString());
			doLogin();
		}
	}

	@Background
	protected void doLogin() {
		showProgressDialog("Autenticando...");
		Intent intent = new Intent(this, MainService_.class);
		intent.setAction(MainService.LOGIN);
		startService(intent);
	}
	

    @Override
    protected void onStart() {
    	super.onStart();
		LocalBroadcastManager.getInstance(this).registerReceiver(broadReceiver, new IntentFilter(MainService.LOGIN));
    };
    
    @Override
    protected void onStop() {
    	super.onStop();
    	LocalBroadcastManager.getInstance(this).unregisterReceiver(broadReceiver);
    }
}