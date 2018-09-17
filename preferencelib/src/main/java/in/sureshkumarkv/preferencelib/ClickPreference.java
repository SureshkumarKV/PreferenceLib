package in.sureshkumarkv.preferencelib;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class ClickPreference extends Preference implements Preference.OnPreferenceClickListener{
    CheckBox checkBox;

    public ClickPreference(Context context) {
        super(context);
        setOnPreferenceClickListener(this);
    }

    public ClickPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnPreferenceClickListener(this);
    }

    public ClickPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        int value = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).getInt(getKey(), 0);
        value++;
        if(value>100){
            value = 0;
        }

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).edit();
        editor.putInt(getKey(), value);
        editor.commit();
        return true;
    }
}
