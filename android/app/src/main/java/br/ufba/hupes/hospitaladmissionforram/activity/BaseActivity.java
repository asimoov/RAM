package br.ufba.hupes.hospitaladmissionforram.activity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import br.ufba.hupes.hospitaladmissionforram.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface.OnClickListener;

@EActivity
public abstract class BaseActivity extends Activity {

	private ProgressDialog _progressDialog;
	private AlertDialog _alertDialog;
	
	@UiThread
	public void showProgressDialog(String message) {
		_progressDialog = new ProgressDialog(this);
		_progressDialog.setCancelable(false);
		_progressDialog.setMessage(message);
		_progressDialog.show();
	}
	
	@UiThread
	public void dismissProgressDialog() {
		_progressDialog.dismiss();
	}
	
	public void showAlertDialog(String message) {
		showAlertDialog(message, null, null, null, null);
	}
	
	public void showAlertDialog(String message, String buttonString, String buttonListener) {
		showAlertDialog(message, buttonString, buttonListener, null, null);
	}
	@UiThread
	public void showAlertDialog(String message, String positiveButton, String negativeButton, OnClickListener positiveListener, OnClickListener negativeListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		if (positiveButton != null)
			builder.setPositiveButton(positiveButton, positiveListener);
		if (negativeButton != null)
			builder.setPositiveButton(positiveButton, negativeListener);
		_alertDialog = builder.setTitle(R.string.app_name).setMessage(message).show();
	}
	
	@UiThread
	public void dismissAlertDialog() {
		_alertDialog.dismiss();
	}

}