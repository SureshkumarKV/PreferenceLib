package in.sureshkumarkv.preferencelib;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

public class LayoutPreference extends Preference {
    private int mTargetLayout;
    private int mTargetLayoutId;

    public LayoutPreference(Context context) {
        super(context);
    }

    public LayoutPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
        setWidgetLayoutResource(mTargetLayout);
    }

    public LayoutPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        setWidgetLayoutResource(mTargetLayout);
    }

    private void init(Context context, AttributeSet attrs){
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LayoutPreference, 0, 0);
        String targetLayoutName = a.getString(R.styleable.LayoutPreference_targetLayout);
        String targetLayoutIdName = a.getString(R.styleable.LayoutPreference_targetLayoutId);
        mTargetLayout = context.getResources().getIdentifier(targetLayoutName, "layout", context.getApplicationContext().getPackageName());
        mTargetLayoutId = context.getResources().getIdentifier(targetLayoutIdName, "id", context.getApplicationContext().getPackageName());
        a.recycle();
    }

    @Override
    protected View onCreateView(final ViewGroup parent) {
        final View v = super.onCreateView(parent);

        v.postOnAnimation(new Runnable() {
            @Override
            public void run() {
                View container = getDecendant(v, mTargetLayoutId);
                container.measure(View.MeasureSpec.makeMeasureSpec(container.getMeasuredWidth(), View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                v.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, container.getMeasuredHeight()));
            }
        });

        return v;
    }

    private View getDecendant(View view, int id){
        if(view.getId() == id){
            return view;
        }

        if(view instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup)view;
            for(int i=0; i<viewGroup.getChildCount(); i++){
                View subView = getDecendant(viewGroup.getChildAt(i), id);
                if(subView != null){
                    return subView;
                }
            }
        }

        return null;
    }
}
