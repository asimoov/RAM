package br.ufba.hupes.hospitaladmissionforram.model;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(SharedPref.Scope.UNIQUE)
public interface UserHolder {

	boolean admin();
	
	public String id();
	
	public String login();
	
	public String pass();
}