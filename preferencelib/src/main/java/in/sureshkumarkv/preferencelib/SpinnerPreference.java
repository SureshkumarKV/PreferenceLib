package in.sureshkumarkv.preferencelib;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerPreference extends Preference {
    Spinner spinner;
    CharSequence[] mEntries;

    public SpinnerPreference(Context context) {
        super(context);
        setWidgetLayoutResource(R.layout.preference_spinner);
    }

    public SpinnerPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
        setWidgetLayoutResource(R.layout.preference_spinner);
    }

    public SpinnerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        setWidgetLayoutResource(R.layout.preference_spinner);
    }

    private void init(Context context, AttributeSet attrs){
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SpinnerPreference, 0, 0);
        mEntries = a.getTextArray(R.styleable.SpinnerPreference_android_entries);
        a.recycle();
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        spinner = (Spinner) view.findViewById(R.id.spinner_pref_spinner);
        spinner.setAdapter(new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, mEntries));
        spinner.setSelection(getPersistedInt(0));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).edit();
                editor.putInt(getKey(), position);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
