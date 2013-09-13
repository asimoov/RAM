package br.ufba.hupes.hospitaladmissionforram.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;

public class HospitalAdapter extends ArrayAdapter<Hospital> {

    HospitalFilter hospitalsFilter;
    List<Hospital> filterhospitals;
    List<Hospital> originHospital;

	static class HospitalHolder {
		TextView county;
		TextView acronym;
		TextView name;
		TextView quantity;
	}

	public HospitalAdapter(Context context, int textViewResourceId,
			List<Hospital> hospitals) {
		super(context, textViewResourceId, hospitals);

        this.filterhospitals = new ArrayList<Hospital>();
        this.filterhospitals.addAll(hospitals);

        this.originHospital = new ArrayList<Hospital>();
        this.originHospital.addAll(hospitals);
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
			holder.quantity = (TextView) view.findViewById(R.id.quantity);
			holder.acronym = (TextView) view.findViewById(R.id.acronym);
			holder.name =  (TextView) view.findViewById(R.id.name);
			holder.county = (TextView) view.findViewById(R.id.county);
			
			view.setTag(holder);
		} else {
            holder = (HospitalHolder) view.getTag();
        }

        String styledText = "<font color='red'><sup>" + hospital.getQuantityOpen() + "</sup></font>/<sub>" + hospital.getQuantity() + "</sub>";
		holder.quantity.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
		holder.acronym.setText(hospital.getAcronym());
		holder.name.setText(hospital.getName().toString());
		holder.county.setText(hospital.getCounty());
		
		return view;
	}

    @Override
    public Filter getFilter() {
        if (hospitalsFilter == null) {
            hospitalsFilter = new HospitalFilter();
        }
        return hospitalsFilter;
    }

    private class HospitalFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d("DEBUG", constraint.toString());

            FilterResults results = new FilterResults();
            List<Hospital> hospitals = HospitalAdapter.this.originHospital;

            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = hospitals;
                results.count = hospitals.size();
            }
            else {
                // We perform filtering operation
                List<Hospital> nHospitalList = new ArrayList<Hospital>();

                for (Hospital p : hospitals) {
                    if (p.getAcronym().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        nHospitalList.add(p);
                }

                results.values = nHospitalList;
                results.count = nHospitalList.size();
            }
            Log.d("DEBUG", String.valueOf(results.count));
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterhospitals = (ArrayList<Hospital>) results.values;
            notifyDataSetChanged();

            clear();
            for(int i = 0, l = filterhospitals.size(); i < l; i++) {
                add(filterhospitals.get(i));
            }
            notifyDataSetInvalidated();
        }
    }
}
