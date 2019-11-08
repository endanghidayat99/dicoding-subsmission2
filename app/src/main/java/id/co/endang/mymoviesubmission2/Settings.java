package id.co.endang.mymoviesubmission2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Locale;

public class Settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences sharedPreferences = getSharedPreferences("LANGUAGE", MODE_PRIVATE);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.setting);

        RadioGroup rgLanguage = findViewById(R.id.rgLanguage);
        RadioButton rbIndonesia = findViewById(R.id.rbIndonesia);

        if (getLanguage(sharedPreferences).equals("in"))
            rbIndonesia.setChecked(true);

        rgLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked) {
                switch (checked) {
                    case R.id.rbIndonesia:
                        saveLanguage("in", sharedPreferences);
                        setLocale();
                        break;
                    default:
                        setLocale();
                        saveLanguage("en", sharedPreferences);
                        break;
                }
            }
        });
    }

    public void saveLanguage(String language, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("language", language);
        editor.commit();
    }

    public static void setLanguage(Context context, SharedPreferences sharedPreferences) {
        Locale locale = new Locale(getLanguage(sharedPreferences));
        Configuration configuration = context.getResources().getConfiguration();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration,
                context.getResources().getDisplayMetrics());
    }

    private static String getLanguage(SharedPreferences sharedPreferences) {
        String language = sharedPreferences.getString("language", "en");
        return language;
    }

    private void setLocale() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
