package com.example.lab2_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://logicgame-be4b7-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText login = findViewById(R.id.login);
        EditText password = findViewById(R.id.password);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
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
            Toast.makeText(this, "Введіть електронну пошту.", Toast.LENGTH_SHORT).show();
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
                        String getPassword = snapshot.child(login).child("password").getValue(String.class);
                        if(getPassword.equals(password))
                        {
                            Toast.makeText(Login.this, "Вхід гравця: успішно!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Login.this, start.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(Login.this, "Неправильний пароль.", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(Login.this, "Неправильний логін.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void goToRegister(View view)
    {
        Intent goReg = new Intent(Login.this, Registration.class);
        startActivity(goReg);
    }
}