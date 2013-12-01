package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.text.Editable;
import android.text.TextWatcher;

public class RealNumberWatcher implements TextWatcher {

	private String textToChange;

	@Override
	public void afterTextChanged(Editable s) {
		if (textToChange != null) {
			Float f = Float.parseFloat(textToChange.replace(',', '.'));
			s.replace(0, s.length(),f.toString().replace('.', ','));
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		textToChange = null;
		String str = s.toString();
		int indexOf = str.indexOf(',');
		if (indexOf < 0) return;
		if (indexOf != str.lastIndexOf(',')) {
			str = str.replace(",", "");
			StringBuilder sb = new StringBuilder();
			sb.append(str.substring(0,indexOf));
			sb.append(",");
			sb.append(str.substring(indexOf));
			textToChange = sb.toString();
		}
	}

}
