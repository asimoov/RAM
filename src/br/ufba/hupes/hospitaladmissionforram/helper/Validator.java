package br.ufba.hupes.hospitaladmissionforram.helper;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            // em qualquer outra condição e gerado um erro
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }

    public static boolean validateDateFormat(View pView, String pDateFormat,
                                             String pMessage) {
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
}
