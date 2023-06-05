package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView loginLockedTextView;
    private TextView attemptsTextView;
    private TextView numberOfAttemptsTextView;

    // Число для подсчета попыток залогиниться:
    private int numberOfRemainingLoginAttempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализируем элементы интерфейса по id:
        usernameEditText = findViewById(R.id.edit_user);
        passwordEditText = findViewById(R.id.edit_password);
        loginButton = findViewById(R.id.button_login);
        loginLockedTextView = findViewById(R.id.login_locked);
        attemptsTextView = findViewById(R.id.attempts);
        numberOfAttemptsTextView = findViewById(R.id.number_of_attempts);

        // Устанавливаем количество оставшихся попыток залогиниться в текстовое поле:
        numberOfAttemptsTextView.setText(Integer.toString(numberOfRemainingLoginAttempts));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }

    // Обрабатываем нажатие кнопки "Войти":
    public void login(View view) {

        // Проверяем, что введенный логин и пароль равны "admin":
        if (usernameEditText.getText().toString().equals("admin") &&
                passwordEditText.getText().toString().equals("admin")) {
            // Показываем Toast сообщение об успешном входе:
            Toast.makeText(getApplicationContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show();

            // Выполняем переход на другой экран:
            Intent intent = new Intent(MainActivity.this, Second.class);
            startActivity(intent);
        } else {
            // Выводим сообщение об ошибке в случае, если введен неправильный логин или пароль:
            Toast.makeText(getApplicationContext(), "Неправильные данные!", Toast.LENGTH_SHORT).show();

            // Уменьшаем количество оставшихся попыток на 1:
            numberOfRemainingLoginAttempts--;

            // Делаем видимыми текстовые поля, указывающие на количество оставшихся попыток:
            attemptsTextView.setVisibility(View.VISIBLE);
            numberOfAttemptsTextView.setVisibility(View.VISIBLE);
            numberOfAttemptsTextView.setText(Integer.toString(numberOfRemainingLoginAttempts));

            // Если количество оставшихся попыток равно 0, блокируем кнопку и выводим сообщение об этом:
            if (numberOfRemainingLoginAttempts == 0) {
                loginButton.setEnabled(false);
                loginLockedTextView.setVisibility(View.VISIBLE);
                loginLockedTextView.setBackgroundColor(Color.RED);
                loginLockedTextView.setText("Вход заблокирован!!!");
            }
        }
    }
}
