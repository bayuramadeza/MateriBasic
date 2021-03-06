package com.binar.academy.materiapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private final String NAMA_PREF = "namaPref";
    private final String PREF_KEY_NAMA = "PREF_KEY_NAMA";
    private final String PREF_KEY_UMUR = "PREF_KEY_UMUR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSharedPref();

        final EditText etNama = findViewById(R.id.et_name);
        final TextView tvNama = findViewById(R.id.tv_name);

        final EditText etUmur = findViewById(R.id.et_umur);
        final TextView tvUmur = findViewById(R.id.tv_umur);

        final Button btnSave = findViewById(R.id.btn_save);
        final Button btnClear = findViewById(R.id.btn_clear);

        tvNama.setText(preferences.getString(PREF_KEY_NAMA, "INI DEFAULT VALUE"));
        tvUmur.setText(String.format("%s Tahun",
                preferences.getString(PREF_KEY_UMUR, "17")));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();
                tvNama.setText(nama);

                String umur = etUmur.getText().toString();
                tvUmur.setText(String.format("%s Tahun", umur));

                save(nama, umur);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear(PREF_KEY_NAMA);
                clear(PREF_KEY_UMUR);
                tvNama.setText("");
                tvUmur.setText("");
            }
        });

    }

    private void setupSharedPref() {
        preferences = getSharedPreferences(NAMA_PREF, MODE_PRIVATE);
    }

    private void save(String nama, String umur) {
        preferences.edit().putString(PREF_KEY_NAMA, nama).apply();
        preferences.edit().putString(PREF_KEY_UMUR, umur).apply();
    }

    private void clear(String key) {
        preferences.edit().remove(key).apply();
    }
}
