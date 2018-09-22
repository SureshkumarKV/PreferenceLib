package in.sureshkumarkv.preferencelib;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ChooserPreference extends Preference {
    public static final int RESULT_CODE = 236434;

    public ChooserPreference(Context context) {
        super(context);
        setWidgetLayoutResource(R.layout.preference_chooser);
    }

    public ChooserPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setWidgetLayoutResource(R.layout.preference_chooser);
    }

    public ChooserPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWidgetLayoutResource(R.layout.preference_chooser);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        final Button button = view.findViewById(R.id.chooser_pref_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mediaChooser = new Intent(Intent.ACTION_GET_CONTENT);
                mediaChooser.setType("video/*");
                ((Activity)button.getContext()).startActivityForResult(mediaChooser, RESULT_CODE);
            }
        });
    }
}
