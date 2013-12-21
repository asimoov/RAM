package br.ufba.hupes.hospitaladmissionforram.helper;

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
                    edText.setFocusable(false);

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

    public static boolean validateDateFormat(View pView, String pDateFormat, String pMessage) {
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
                        edText.setFocusable(false);
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
		if (pInitialDate instanceof EditText && pFinalDate instanceof EditText) {
			EditText edText = (EditText) pInitialDate;
			EditText edText2 = (EditText) pFinalDate;
            Editable textIni = edText.getText();
            Editable textFin = edText2.getText();
            if (textIni != null && textFin != null) {
            	String strIniDate = edText.getText().toString();
            	String strFinDate = edText2.getText().toString();
		        if (!TextUtils.isEmpty(strIniDate) && !TextUtils.isEmpty(strFinDate)) {
		            SimpleDateFormat format = new SimpleDateFormat(pDateFormat);
		            try {
		            	Date iniDate = format.parse(strIniDate);
		            	Date finDate = format.parse(strFinDate);
		                if (!finDate.before(iniDate)) { 
			            	edText.setError(null);
			                edText.setFocusable(false);
			                return true;
		                }
		            } catch (ParseException pe) {
		
		            }
		        }
            }
	        // em qualquer outra condição é gerado um erro
	        edText.setError(pMessage);
	        edText.setFocusable(true);
	        edText.requestFocus();
		}
        return false;
	}
}
