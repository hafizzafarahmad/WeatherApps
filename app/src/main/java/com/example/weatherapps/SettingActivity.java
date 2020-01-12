package com.example.weatherapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weatherapps.Utils.SaveDataPreference;

public class SettingActivity extends AppCompatActivity {

    private EditText edt_name, edt_zip;
    private Button btnSave;

    String nama, zip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        edt_name = findViewById(R.id.edtnama);
        edt_zip = findViewById(R.id.edtzip);
        btnSave = findViewById(R.id.savebtn);

        edt_name.setText("");
        edt_zip.setText("");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = edt_name.getText().toString();
                zip = edt_zip.getText().toString();

                if (nama.equals("") || zip.equals("")){
                    Toast.makeText(SettingActivity.this, "Field dont Empty", Toast.LENGTH_SHORT).show();
                }else {
                    SaveDataPreference.setName(SettingActivity.this,nama);
                    SaveDataPreference.setZip(SettingActivity.this, zip);
                    startActivity(new Intent(SettingActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}
