package com.example.lab2_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
                Intent ilg = new Intent(Login.this, MainActivity.class);
                startActivity(ilg);
                return true;
            case R.id.menuLogin:
                Toast.makeText(Login.this, "Ви на екрані входу!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuParam:
                Intent ilp = new Intent(Login.this, start.class);
                startActivity(ilp);
                return true;
            case R.id.menuReg:
                Intent ilr = new Intent(Login.this, Registration.class);
                startActivity(ilr);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                            i.putExtra("login", login);
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