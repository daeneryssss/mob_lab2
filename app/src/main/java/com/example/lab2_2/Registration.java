package com.example.lab2_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://logicgame-be4b7-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText login = findViewById(R.id.loginReg);
        EditText password = findViewById(R.id.passwordReg);
        EditText email = findViewById(R.id.emailReg);
        Button register = (Button) findViewById(R.id.registration);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered(email.getText().toString(), login.getText().toString(), password.getText().toString());
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
                Intent irg = new Intent(Registration.this, MainActivity.class);
                startActivity(irg);
                return true;
            case R.id.menuLogin:
                Intent irl = new Intent(Registration.this, Login.class);
                startActivity(irl);
                return true;
            case R.id.menuParam:
                Intent irp = new Intent(Registration.this, start.class);
                startActivity(irp);
                return true;
            case R.id.menuReg:
                Toast.makeText(Registration.this, "Ви на екрані реєстрації!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void checkDataEntered(String email, String login, String password)
    {
        if (email.equals(""))
        {
            Toast.makeText(this, "Введіть електронну пошту.", Toast.LENGTH_SHORT).show();
        }
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
                        databaseReference.child("users").child(login).child("e-mail").setValue(email);
                        Toast.makeText(Registration.this, "Успішно зареєстровано!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Registration.this, start.class);
                        i.putExtra("login", login);
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