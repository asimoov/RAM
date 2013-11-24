//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0-SNAPSHOT.
//


package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import br.ufba.hupes.hospitaladmissionforram.R.layout;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class RAMFragment_
    extends RAMFragment
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private View contentView_;

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
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
            contentView_ = inflater.inflate(layout.frag_ram, container, false);
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

    public static RAMFragment_.FragmentBuilder_ builder() {
        return new RAMFragment_.FragmentBuilder_();
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        otherCauses = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.other_causes));
        cause = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.cause));
        medications = ((LinearLayout) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.medications));
        comorbidities = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.comorbidities));
        if (hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.addMedication)!= null) {
            hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.addMedication).setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    RAMFragment_.this.addMedication();
                }

            }
            );
        }
        init();
    }

    public static class FragmentBuilder_ {

        private Bundle args_;

        private FragmentBuilder_() {
            args_ = new Bundle();
        }

        public RAMFragment build() {
            RAMFragment_ fragment_ = new RAMFragment_();
            fragment_.setArguments(args_);
            return fragment_;
        }

    }

}
