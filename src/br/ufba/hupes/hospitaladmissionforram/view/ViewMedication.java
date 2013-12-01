package br.ufba.hupes.hospitaladmissionforram.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.model.Medication;

@EViewGroup(R.layout.item_medication)
public class ViewMedication extends RelativeLayout {

	@ViewById
	TextView medication;
	@ViewById
	TextView way;
	@ViewById
	TextView dose;
	@ViewById
	TextView indication;
	@ViewById
	TextView date;

	public ViewMedication(Context context) {
		super(context);
	}

	public ViewMedication(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ViewMedication(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@AfterViews
	protected void init() {

	}

	public void bind(Medication obj) {
		if (obj != null) {
			medication.setText(obj.getName());
			way.setText(obj.getWay());
			dose.setText(obj.getDose());
			indication.setText(obj.getIndication());
			date.setText(obj.getStart() + " a " + obj.getEnd());
		}

	}
}
