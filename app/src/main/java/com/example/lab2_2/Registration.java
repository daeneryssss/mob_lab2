package com.example.lab2_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Registration extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://logicgame-be4b7-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText login = findViewById(R.id.loginReg);
        EditText password = findViewById(R.id.passwordReg);
        Button register = (Button) findViewById(R.id.registration);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered(login.getText().toString(), password.getText().toString());
            }
        });
    }

    public void checkDataEntered(String login, String password)
    {
        if (login.equals(""))
        {
            Toast.makeText(this, "Введіть логін.", Toast.LENGTH_SHORT).show();
        }
        else if (password.equals(""))
        {
            Toast.makeText(this, "Введіть пароль.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(login))
                    {
                        Toast.makeText(Registration.this, "Такий логін вже зареєстрований.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        databaseReference.child("users").child(login).child("password").setValue(password);
                        Toast.makeText(Registration.this, "Успішно зареєстровано!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Registration.this, start.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void goToLog(View view)
    {
        Intent goLog = new Intent(Registration.this, Login.class);
        startActivity(goLog);
    }
}