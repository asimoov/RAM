package br.ufba.hupes.hospitaladmissionforram.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import br.ufba.hupes.hospitaladmissionforram.R;
import br.ufba.hupes.hospitaladmissionforram.model.Research;

public class ResearchAdapter extends ArrayAdapter<Research> {

	static class ResearchHolder {
		TextView name;
		TextView handbook;
		TextView cns;
	}

	public ResearchAdapter(Context context, int textViewResourceId,
			Research[] researches) {
		super(context, textViewResourceId, researches);
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
			holder.cns = (TextView) view.findViewById(R.id.cns);
			holder.name =  (TextView) view.findViewById(R.id.name);
			
			view.setTag(holder);
		} else {
            holder = (ResearchHolder) view.getTag();
        }
		
		holder.handbook.setText(research.getHandbook());
		holder.cns.setText(research.getCns());
		holder.name.setText(research.getName());
		
		return view;
	}
}
