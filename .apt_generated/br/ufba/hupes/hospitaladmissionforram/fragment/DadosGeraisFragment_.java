//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0-SNAPSHOT.
//


package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import br.ufba.hupes.hospitaladmissionforram.R.array;
import br.ufba.hupes.hospitaladmissionforram.R.layout;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class DadosGeraisFragment_
    extends DadosGeraisFragment
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private View contentView_;

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        Resources resources_ = getActivity().getResources();
        arrayColors = resources_.getStringArray(array.colors);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.frag_dados_gerais, container, false);
        }
        return contentView_;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public View findViewById(int id) {
        if (contentView_ == null) {
            return null;
        }
        return contentView_.findViewById(id);
    }

    public static DadosGeraisFragment_.FragmentBuilder_ builder() {
        return new DadosGeraisFragment_.FragmentBuilder_();
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        sexFemale = ((RadioButton) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.sexFemale));
        height = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.height));
        birthday = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.birthday));
        sexMale = ((RadioButton) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.sexMale));
        handbook = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.handbook));
        bed = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.bed));
        admission = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.admission));
        unit = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.unit));
        name = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.name));
        color = ((Spinner) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.color));
        number = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.number));
        weight = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.weight));
        init();
    }

    public static class FragmentBuilder_ {

        private Bundle args_;

        private FragmentBuilder_() {
            args_ = new Bundle();
        }

        public DadosGeraisFragment build() {
            DadosGeraisFragment_ fragment_ = new DadosGeraisFragment_();
            fragment_.setArguments(args_);
            return fragment_;
        }

    }

}
