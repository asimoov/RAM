package br.ufba.hupes.hospitaladmissionforram.helper;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by denis on 13/09/13.
 */
public class Validator {

    public static boolean validateNotNull(View pView, String pMessage) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String strText = text.toString();
                if (!TextUtils.isEmpty(strText)) {
                    edText.setError(null);
                    return true;
                }
            }
            // em qualquer outra condição é gerado um erro
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }

    @SuppressLint("SimpleDateFormat")
    public static boolean validateDateFormat(View pView, String pDateFormat, String pMessage) {
        return validateDateFormat(pView, pDateFormat, false, pMessage);
    }

    @SuppressLint("SimpleDateFormat")
    public static boolean validateDateFormat(View pView, String pDateFormat, boolean canBeNull, String pMessage) {
        if (canBeNull) return true;
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String strText = text.toString();
                if (!TextUtils.isEmpty(strText)) {
                    SimpleDateFormat format = new SimpleDateFormat(pDateFormat);
                    try {
                        format.parse(strText);
                        edText.setError(null);
                        return true;
                    } catch (ParseException pe) {

                    }
                }
            }
            // em qualquer outra condição é gerado um erro
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }

    public static boolean validateDateRange(View pInitialDate, View pFinalDate, String pDateFormat, String pMessage) {
        return validateDateRange(pInitialDate, pFinalDate, pDateFormat, false, pMessage);
    }

    public static boolean validateDateRange(View pInitialDate, View pFinalDate, String pDateFormat, boolean canBeNull, String pMessage) {
		if (canBeNull) return validateDateFormat(pInitialDate, pDateFormat, pMessage);
        if (pInitialDate instanceof EditText && pFinalDate instanceof EditText) {
			EditText edText = (EditText) pInitialDate;
			EditText edText2 = (EditText) pFinalDate;
            Editable textIni = edText.getText();
            Editable textFin = edText2.getText();
            if (textIni != null && textFin != null) {
            	String strIniDate = edText.getText().toString();
            	String strFinDate = edText2.getText().toString();
		        return validateDateIsAfter(strIniDate, strFinDate, pDateFormat, edText, pMessage);
            }
	        // em qualquer outra condi��o � gerado um erro
	        edText.setError(pMessage);
	        edText.setFocusable(true);
	        edText.requestFocus();
		}
        return false;
	}

	@SuppressLint("SimpleDateFormat")
	public static boolean validateDateIsAfter(String strIniDate, String strFinDate, String pDateFormat, EditText edText, String pMessage) {
		if (!TextUtils.isEmpty(strIniDate) && !TextUtils.isEmpty(strFinDate)) {
		    SimpleDateFormat format = new SimpleDateFormat(pDateFormat);
		    try {
		    	Date iniDate = format.parse(strIniDate);
		    	Date finDate = format.parse(strFinDate);
		        if (!finDate.before(iniDate)) { 
		        	edText.setError(null);
		            return true;
		        }
		    } catch (ParseException pe) {

		    }
		}
        // em qualquer outra condição é gerado um erro
        edText.setError(pMessage);
        edText.setFocusable(true);
        edText.requestFocus();
		return false;
	}
}
