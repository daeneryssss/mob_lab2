package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resPoints = findViewById(R.id.resultPoints);

        Intent i = getIntent();
        int res = i.getIntExtra("result", 0);
        resPoints.setText(String.valueOf(res));

        Button bAgain = (Button) findViewById(R.id.again);
        bAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    };

    @Override
    public void onBackPressed()
    {
        Button bAgain = (Button) findViewById(R.id.again);
        bAgain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    };
}