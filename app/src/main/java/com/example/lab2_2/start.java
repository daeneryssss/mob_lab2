package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

public class start extends AppCompatActivity {

    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent i = getIntent();
        login = i.getStringExtra("login");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_param, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.menuGame:
                Intent ipg = new Intent(start.this, MainActivity.class);
                ipg.putExtra("login", login);
                startActivity(ipg);
                return true;
            case R.id.menuLogin:
                Intent ipl = new Intent(start.this, Login.class);
                startActivity(ipl);
                return true;
            case R.id.menuParam:
                Toast.makeText(start.this, "Ви вже обираєте налаштування!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuReg:
                Intent ipr = new Intent(start.this, Registration.class);
                startActivity(ipr);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private int level = 1;
    public boolean timerOn = false;

    public void checkTimer(View view) {
        timerOn = ((CheckBox) view).isChecked();
    }

    public void wrongAnswer(View view) {
        boolean checkAns = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.zero:
                level = 1;
                break;
            case R.id.minus:
                level = 2;
                break;
            case R.id.endGame:
                level = 3;
                break;
        }
    }

    public void startGame(View view) {
        Intent i = new Intent(start.this, MainActivity.class);
        i.putExtra("level", level);
        i.putExtra("timerIsOn", timerOn);
        i.putExtra("login", login);
        startActivity(i);
    }
}