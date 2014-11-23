package br.ufba.hupes.hospitaladmissionforram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Environment;

import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufba.hupes.hospitaladmissionforram.model.UserHolder_;

@EApplication
public class MainApp extends Application {

	@Pref
	UserHolder_ user;
	
	private static MainApp app;

	private static SimpleDateFormat sdf;

	@SuppressLint("SimpleDateFormat")
	public static String getDateFormatted(Date date, String format) {
		if (sdf == null) sdf = new SimpleDateFormat(format);
		else sdf.applyLocalizedPattern(format);
		return sdf.format(date);
	}
	
	@SuppressLint("SimpleDateFormat")
	public static int compareDatesFormatted(String s1, String s2, String format) {
		if (sdf == null) sdf = new SimpleDateFormat(format);
		else sdf.applyLocalizedPattern(format);
		try {
			Date d1 = sdf.parse(s1);
			Date d2 = sdf.parse(s2);
			return d1.compareTo(d2);
		} catch (ParseException e) {
		}
		return 0;
	}
	
	public UserHolder_ getUser() {
		return user;
	}

	public void setUser(UserHolder_ user) {
		this.user = user;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		app = this;
	}

	public static MainApp getInstance() {
		return app;
	}
	
	public static void copyDatabaseToSDCard() {
		try {
			File database = app.getDatabasePath("hospitaladmissionforram1.db");
			FileInputStream fis = new FileInputStream(database);
			File out = new File(Environment.getExternalStorageDirectory(), "database.db");
			FileOutputStream fos = new FileOutputStream(out);
			
			for (byte[] buffer = new byte[1024]; fis.read(buffer) != 0;) {
				fos.write(buffer);
			}
			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logoff(final Activity actv) {
		AlertDialog alert = new AlertDialog.Builder(actv)
						.setMessage("Deseja mesmo sair?")
						.setPositiveButton("Sim", new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								user.clear();
								actv.finish();
							}
						}).setNegativeButton("Não", null)
						.show();
	}
}
