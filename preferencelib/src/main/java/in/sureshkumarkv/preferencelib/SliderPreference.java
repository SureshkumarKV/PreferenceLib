package in.sureshkumarkv.preferencelib;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SliderPreference extends Preference {
    SeekBar seekBar;

    public SliderPreference(Context context) {
        super(context);
        setWidgetLayoutResource(R.layout.preference_slider);
    }

    public SliderPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setWidgetLayoutResource(R.layout.preference_slider);
    }

    public SliderPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWidgetLayoutResource(R.layout.preference_slider);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        seekBar = (SeekBar) view.findViewById(R.id.slider_pref_seekBar);
        seekBar.setProgress(getPersistedInt(50));
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).edit();
                editor.putInt(getKey(), seekBar.getProgress());
                editor.commit();
                //persistInt(seekBar.getProgress());// This is not working across processes !
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }
        });
    }
}
