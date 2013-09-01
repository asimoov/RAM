package com.example.projectx.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.projectx.R;
import com.example.projectx.model.Hospital;

public class HospitalAdapter extends ArrayAdapter<Hospital> {

	static class HospitalHolder {
		TextView acronym;
		TextView county;
		TextView name;
		TextView quantity;
	}

	public HospitalAdapter(Context context, int textViewResourceId,
			Hospital[] hospitals) {
		super(context, textViewResourceId, hospitals);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Hospital hospital = this.getItem(position);
		HospitalHolder holder = null;
		
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = ((Activity)this.getContext()).getLayoutInflater();
			view = inflater.inflate(R.layout.item_hospital, parent, false);

			holder = new HospitalHolder();
			holder.acronym = (TextView) view.findViewById(R.id.acronym);
			holder.county = (TextView) view.findViewById(R.id.county);
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.quantity = (TextView) view.findViewById(R.id.quantity);
			
			view.setTag(holder);
		} else {
            holder = (HospitalHolder) view.getTag();
        }
		
		holder.acronym.setText(hospital.getAcronym());
		holder.county.setText(hospital.getCounty());
		holder.name.setText(hospital.getName());
		holder.quantity.setText(hospital.getQuantity().toString());

		return view;
	}
}
