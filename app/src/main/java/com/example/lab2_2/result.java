package com.example.lab2_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class result extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://logicgame-be4b7-default-rtdb.firebaseio.com/");
    String login;
    int res;
    TextView resPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resPoints = findViewById(R.id.resultPoints);

        Intent i = getIntent();
        res = i.getIntExtra("result", 0);
        login = i.getStringExtra("login");
        resPoints.setText(String.valueOf(res));

        registerForContextMenu(resPoints);
    };

    public void showPopup(View view)
    {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.menuGame:
                        Intent ilg = new Intent(result.this, MainActivity.class);
                        startActivity(ilg);
                        return true;
                    case R.id.menuLogin:
                        Intent i = new Intent(result.this, Login.class);
                        startActivity(i);
                        return true;
                    case R.id.menuParam:
                        Intent ilp = new Intent(result.this, start.class);
                        startActivity(ilp);
                        return true;
                    case R.id.menuReg:
                        Intent ilr = new Intent(result.this, Registration.class);
                        startActivity(ilr);
                        return true;
                    default:
                        return false;
                }
            }
        });
        inflater.inflate(R.menu.menu_param, popup.getMenu());
        popup.show();
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.sendResToEmail:

                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(login))
                        {
                            String getEmail = snapshot.child(login).child("e-mail").getValue(String.class);
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_CC, getEmail);
                            intent.putExtra(Intent.EXTRA_TEXT, resPoints.getText().toString());
                            intent.setType("message/rfc882");
                            if(intent.resolveActivity(getPackageManager()) != null) {
                                startActivity(intent);
                                Toast.makeText(result.this, "Результат надіслано!", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(result.this, "Щось пішло не так...", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}