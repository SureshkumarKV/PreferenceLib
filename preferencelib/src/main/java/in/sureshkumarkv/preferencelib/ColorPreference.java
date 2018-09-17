package in.sureshkumarkv.preferencelib;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;

import in.sureshkumarkv.androidcolorpickerview.ColorPickerView;
import in.sureshkumarkv.androidcolorpickerview.ColorView;

public class ColorPreference extends Preference implements Preference.OnPreferenceClickListener{
    ColorView mColorView;

    public ColorPreference(Context context) {
        super(context);
        setOnPreferenceClickListener(this);
        setWidgetLayoutResource(R.layout.preference_color);
    }

    public ColorPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnPreferenceClickListener(this);
        setWidgetLayoutResource(R.layout.preference_color);
    }

    public ColorPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnPreferenceClickListener(this);
        setWidgetLayoutResource(R.layout.preference_color);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        mColorView = (ColorView) view.findViewById(R.id.color_pref_colorView);
        mColorView.setColor(getPersistedInt(0));
        mColorView.setClickable(false);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.preference_dialog_colorpicker);
        dialog.show();

        dialog.findViewById(R.id.id_settings_dialog_color_button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ((ColorPickerView)dialog.findViewById(R.id.id_settings_dialog_color_picker)).getColor();

                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).edit();
                editor.putInt(getKey(), color);
                editor.commit();

                mColorView.setColor(color);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.id_settings_dialog_color_button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return true;
    }
}
