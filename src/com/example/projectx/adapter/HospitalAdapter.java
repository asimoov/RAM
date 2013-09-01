package com.example.projectx.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.projectx.model.Hospital;

public class HospitalAdapter extends ArrayAdapter<Hospital> {

	public HospitalAdapter(Context context, int textViewResourceId,
			Hospital[] objects) {
		super(context, textViewResourceId, objects);
	}
}
