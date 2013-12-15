//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0-SNAPSHOT.
//


package br.ufba.hupes.hospitaladmissionforram.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import br.ufba.hupes.hospitaladmissionforram.R.id;
import br.ufba.hupes.hospitaladmissionforram.R.layout;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;


/**
 * We use @SuppressWarning here because our java code
 * generator doesn't know that there is no need
 * to import OnXXXListeners from View as we already
 * are in a View.
 * 
 */
@SuppressWarnings("unused")
public final class ViewMedication_
    extends ViewMedication
    implements HasViews, OnViewChangedListener
{

    private Context context_;
    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private boolean mAlreadyInflated_ = false;

    public ViewMedication_(Context context) {
        super(context);
        init_();
    }

    public ViewMedication_(Context context, AttributeSet attrs) {
        super(context, attrs);
        init_();
    }

    public ViewMedication_(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init_();
    }

    private void init_() {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        context_ = getContext();
        if (context_ instanceof Activity) {
            Activity activity = ((Activity) context_);
        }
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
    }

    /**
     * The mAlreadyInflated_ hack is needed because of an Android bug
     * which leads to infinite calls of onFinishInflate()
     * when inflating a layout with a parent and using
     * the <merge /> tag.
     * 
     */
    @Override
    public void onFinishInflate() {
        if (!mAlreadyInflated_) {
            mAlreadyInflated_ = true;
            inflate(getContext(), layout.item_medication, this);
            onViewChangedNotifier_.notifyViewChanged(this);
        }
        super.onFinishInflate();
    }

    public static ViewMedication build(Context context) {
        ViewMedication_ instance = new ViewMedication_(context);
        instance.onFinishInflate();
        return instance;
    }

    public static ViewMedication build(Context context, AttributeSet attrs) {
        ViewMedication_ instance = new ViewMedication_(context, attrs);
        instance.onFinishInflate();
        return instance;
    }

    public static ViewMedication build(Context context, AttributeSet attrs, int defStyle) {
        ViewMedication_ instance = new ViewMedication_(context, attrs, defStyle);
        instance.onFinishInflate();
        return instance;
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        indication = ((TextView) hasViews.findViewById(id.indication));
        dose = ((TextView) hasViews.findViewById(id.dose));
        date = ((TextView) hasViews.findViewById(id.date));
        way = ((TextView) hasViews.findViewById(id.way));
        name = ((TextView) hasViews.findViewById(id.name));
        if (hasViews.findViewById(id.bt_delete)!= null) {
            hasViews.findViewById(id.bt_delete).setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    ViewMedication_.this.btDelete();
                }

            }
            );
        }
        init();
    }

}
