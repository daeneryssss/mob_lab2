package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import org.w3c.dom.Text;
import android.view.Menu;
import android.widget.Toast;

import java.lang.String;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        timerIsOn = i.getBooleanExtra("timerIsOn", true);
        level = i.getIntExtra("level", 1);
        login = i.getStringExtra("login");

        TextView txtSeconds = (TextView) findViewById(R.id.txtSeconds);
        if (timerIsOn == true) {
            CountDownTimer timerGame = new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long l) {
                    txtSeconds.setText("Часу залишилося: " + l / 1000);
                }

                @Override
                public void onFinish() {
                    seeResult();
                }
            };
            timerGame.start();
        }
        else
            txtSeconds.setText("Таймер вимкнений");

        points = 0;
        randomGeneration();

        Button buttonYes = findViewById(R.id.bYes),
                buttonNo = findViewById(R.id.bNo);

        TextView test = findViewById(R.id.test);

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedYes = true;
                nextQuestion();
                test.setText(String.valueOf(points));
            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedNo = true;
                nextQuestion();
                test.setText(String.valueOf(points));
            }
        });
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
                Toast.makeText(MainActivity.this, "Ви вже граєте!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuLogin:
                Intent igl = new Intent(MainActivity.this, Login.class);
                startActivity(igl);
                return true;
            case R.id.menuParam:
                Intent igp = new Intent(MainActivity.this, start.class);
                startActivity(igp);
                return true;
            case R.id.menuReg:
                Intent igr = new Intent(MainActivity.this, Registration.class);
                startActivity(igr);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    boolean timerIsOn;
    int level;
    public static String result;
    boolean clickedYes = false, clickedNo = false;
    private int points = 0;
    public int textLeftIndex, textRightIndex, colorLeftIndex, colorRightIndex;
    Random random = new Random();

    int[] colors = {
            R.color.black,
            R.color.teal,
            R.color.blue,
            R.color.brown,
            R.color.green,
            R.color.orange,
            R.color.pink,
            R.color.purple,
            R.color.red,
            R.color.yellow,
    };
    String[] colorsName = {
            "чорний", "блакитний", "синій", "коричневий", "зелений", "помаранчевий", "рожевий",
            "фіолетовий", "червоний", "жовтий",
    };

    public void randomGeneration()
    {
        TextView textLeft = findViewById(R.id.textViewLeft);
        TextView textRight = findViewById(R.id.textViewRight);

        textLeftIndex = random.nextInt(colorsName.length);
        textRightIndex = random.nextInt(colorsName.length);
        colorLeftIndex = random.nextInt(colorsName.length);
        colorRightIndex = random.nextInt(colorsName.length);

        textLeft.setText(colorsName[textLeftIndex]);
        textLeft.setTextColor(getResources().getColor(colors[colorLeftIndex]));

        textRight.setText(colorsName[textRightIndex]);
        textRight.setTextColor(getResources().getColor(colors[colorRightIndex]));
    }

    public void nextQuestion() {
        if (textLeftIndex == colorRightIndex && clickedYes == true)
            points = points + 1;
        else if (textLeftIndex != colorRightIndex && clickedNo == true)
            points = points + 1;
        else if (level == 2)
            points = points - 1;
        else if (level == 3)
            seeResult();
        clickedYes = false;
        clickedNo = false;
        randomGeneration();
    }

    public void stop(View view) {
        seeResult();
    }

    public void seeResult() {
        Intent pass = new Intent(MainActivity.this, result.class);
        pass.putExtra("result", points);
        pass.putExtra("login", login);
        startActivity(pass);
        finish();
    }
}