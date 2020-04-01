package com.example.activities_lab_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.button3);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, ThirdActivity.class);
            startActivityForResult(intent,REQUEST_CODE);
        });

    }

    //Пример явного намерение (Intent указывает на наш текущий класс и означает «ссылка на контекст»)
    public void handleClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
    //Пример неявного намерение (приложение для отправки электронной почты)
    public void handleClick2(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);

        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Важное сообщение");
        i.putExtra(Intent.EXTRA_TEXT, "Привет, как дела?");


        PackageManager manager = this.getPackageManager();
        // Cписок обработчиков намерения
        List<ResolveInfo> list = manager.queryIntentActivities(i, 0);

        if (list.size() > 0) {
            startActivity(i);//операционная система выбирает нужное Activity из списка всех Activity приложений, которые установлены на данном устройстве
        } else {
            Toast.makeText(this,"Невозможно обработать намерение!",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    // Обработка результата в первом Activity
    // Обработка результат, который пришел из ThirdActivity
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) { //равно 0
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Пользователь вышел из ThirdActivity", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_OK) { //равно -1

                if (data != null) {
                    String text = data.getStringExtra("result");
                    if (text != null) {
                        TextView tv = findViewById(R.id.textView5);
                        tv.setText(text);
                    }
                }
            }
        }
    }
}
