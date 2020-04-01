package com.example.activities_lab_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText et = findViewById(R.id.editText);

        Button button = findViewById(R.id.button4);
        button.setOnClickListener(v -> {

            String text = et.getText().toString(); // Считываем значение поля

            Intent intent = new Intent();//формирование намерения
            intent.putExtra("result",text); //запись данных

            setResult(RESULT_OK,intent); // Устанавливаем результат

            // Закрываем Activity
            finish();
        });
    }
}
