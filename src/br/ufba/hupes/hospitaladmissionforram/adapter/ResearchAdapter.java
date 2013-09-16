package br.ufba.hupes.hospitaladmissionforram.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;
import br.ufba.hupes.hospitaladmissionforram.model.Status;

public class ResearchAdapter extends ArrayAdapter<Research> {

    ResearchFilter researchsFilter;
    List<Research> filterResearches;
    List<Research> originResearch;

	static class ResearchHolder {
		TextView name;
		TextView handbook;
        ImageView status;
	}

	public ResearchAdapter(Context context, int textViewResourceId,
			List<Research> researches) {
		super(context, textViewResourceId, researches);

        this.filterResearches = new ArrayList<Research>();
        this.filterResearches.addAll(researches);

        this.originResearch = new ArrayList<Research>();
        this.originResearch.addAll(researches);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Research research = this.getItem(position);
		ResearchHolder holder = null;
		
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = ((Activity)this.getContext()).getLayoutInflater();
			view = inflater.inflate(R.layout.item_research, parent, false);

			holder = new ResearchHolder();
			holder.handbook = (TextView) view.findViewById(R.id.handbook);
			holder.name =  (TextView) view.findViewById(R.id.name);
            holder.status =  (ImageView) view.findViewById(R.id.status);
			
			view.setTag(holder);
		} else {
            holder = (ResearchHolder) view.getTag();
        }

        holder.handbook.setText(research.getHandbook());
		holder.name.setText(research.getName());

        if(research.getStatus() == null || research.getStatus() == Status.OPEN.ordinal()) {
            holder.status.setImageResource(R.drawable.rejected);
        }

		return view;
	}

    @Override
    public Filter getFilter() {
        if (researchsFilter == null) {
            researchsFilter = new ResearchFilter();
        }
        return researchsFilter;
    }

    private class ResearchFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d("DEBUG", constraint.toString());

            FilterResults results = new FilterResults();
            List<Research> researches = ResearchAdapter.this.originResearch;

            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = researches;
                results.count = researches.size();
            }
            else {
                // We perform filtering operation
                List<Research> nResearchList = new ArrayList<Research>();

                for (Research p : researches) {
                    if (p.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        nResearchList.add(p);
                }

                results.values = nResearchList;
                results.count = nResearchList.size();
            }
            Log.d("DEBUG", String.valueOf(results.count));
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterResearches = (ArrayList<Research>) results.values;
            notifyDataSetChanged();

            clear();
            for(int i = 0, l = filterResearches.size(); i < l; i++) {
                add(filterResearches.get(i));
            }
            notifyDataSetInvalidated();
        }
    }
}
