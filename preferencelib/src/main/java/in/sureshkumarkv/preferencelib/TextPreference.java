package in.sureshkumarkv.preferencelib;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class TextPreference extends Preference {
    EditText editText;

    public TextPreference(Context context) {
        super(context);
        setWidgetLayoutResource(R.layout.preference_text);
    }

    public TextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setWidgetLayoutResource(R.layout.preference_text);
    }

    public TextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWidgetLayoutResource(R.layout.preference_text);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        editText = (EditText) view.findViewById(R.id.text_pref_editText);
        editText.setText(PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).getString(getKey(), ""));
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).edit();
                    editor.putString(getKey(), editText.getText().toString());
                    editor.commit();
                    return true;
                }
                return false;
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(!hasFocus){
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).edit();
                    editor.putString(getKey(), editText.getText().toString());
                    editor.commit();
                }
            }
        });
    }
}
