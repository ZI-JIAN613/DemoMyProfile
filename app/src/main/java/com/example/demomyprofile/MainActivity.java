package com.example.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etName, etGPA;
    Button btnRetrieve, btnStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        btnStore = findViewById(R.id.btnStore);

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                String name = prefs.getString("name", "Unknown");
                float gpa = prefs.getFloat("gpa", 4.0f);
                etName.setText(name);
                etGPA.setText(gpa + "");
            }
        });

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("name", name);
                prefEdit.putFloat("gpa", gpa);
                prefEdit.commit();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String name = prefs.getString("name", "Unknown");
        float gpa = prefs.getFloat("gpa", 4.0f);
        etName.setText(name);
        etGPA.setText(gpa + "");
    }

    @Override
    protected void onPause() {
        super.onPause();

        String name = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("name", name);
        prefEdit.putFloat("gpa", gpa);
        prefEdit.commit();
    }
}

