//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0-SNAPSHOT.
//


package br.ufba.hupes.hospitaladmissionforram.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import br.ufba.hupes.hospitaladmissionforram.R.array;
import br.ufba.hupes.hospitaladmissionforram.R.layout;
import br.ufba.hupes.hospitaladmissionforram.model.Medication;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class NovoMedicamentoFragment_
    extends NovoMedicamentoFragment
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private View contentView_;

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        Resources resources_ = getActivity().getResources();
        ways = resources_.getStringArray(array.ways);
        injectFragmentArguments_();
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
            contentView_ = inflater.inflate(layout.table_medications, container, false);
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

    public static NovoMedicamentoFragment_.FragmentBuilder_ builder() {
        return new NovoMedicamentoFragment_.FragmentBuilder_();
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        way = ((Spinner) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.way));
        dose = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.dose));
        initialDate = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.initial_date));
        indication = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.indication));
        finalDate = ((EditText) hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.final_date));
        if (hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.btCancelar)!= null) {
            hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.btCancelar).setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    NovoMedicamentoFragment_.this.btCancelar();
                }

            }
            );
        }
        if (hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.final_date)!= null) {
            hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.final_date).setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    NovoMedicamentoFragment_.this.finalDate();
                }

            }
            );
        }
        if (hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.initial_date)!= null) {
            hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.initial_date).setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    NovoMedicamentoFragment_.this.initialDate();
                }

            }
            );
        }
        if (hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.btOk)!= null) {
            hasViews.findViewById(br.ufba.hupes.hospitaladmissionforram.R.id.btOk).setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    NovoMedicamentoFragment_.this.btOk();
                }

            }
            );
        }
        init();
    }

    private void injectFragmentArguments_() {
        Bundle args_ = getArguments();
        if (args_!= null) {
            if (args_.containsKey("medicationItem")) {
                try {
                    medicationItem = ((Medication) args_.getSerializable("medicationItem"));
                } catch (ClassCastException e) {
                    Log.e("NovoMedicamentoFragment_", "Could not cast argument to the expected type, the field is left to its default value", e);
                }
            }
        }
    }

    public static class FragmentBuilder_ {

        private Bundle args_;

        private FragmentBuilder_() {
            args_ = new Bundle();
        }

        public NovoMedicamentoFragment build() {
            NovoMedicamentoFragment_ fragment_ = new NovoMedicamentoFragment_();
            fragment_.setArguments(args_);
            return fragment_;
        }

        public NovoMedicamentoFragment_.FragmentBuilder_ medicationItem(Medication medicationItem) {
            args_.putSerializable("medicationItem", medicationItem);
            return this;
        }

    }

}
