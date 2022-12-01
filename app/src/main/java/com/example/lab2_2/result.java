package com.example.lab2_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
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
    DatabaseReference leaderboard = FirebaseDatabase.getInstance().getReferenceFromUrl("https://logicgame-be4b7-default-rtdb.firebaseio.com/");
    String login;
    int res;
    TextView resPoints;
    String tempL;
    int tempR;
    String top1 = "1";
    String top2 = "2";
    String top3 = "3";
    String top4 = "4";
    String top5 = "5";
    boolean btnIsClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resPoints = findViewById(R.id.resultPoints);

        Intent i = getIntent();
        res = i.getIntExtra("result", 0);
        login = i.getStringExtra("login");
        resPoints.setText(String.valueOf(res));

        FrameLayout container = (FrameLayout) findViewById(R.id.container);
        Button showTop = (Button) findViewById(R.id.showTop);
        btnIsClicked = false;
        showTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnIsClicked == false) {
                    container.setVisibility(View.VISIBLE);
                    btnIsClicked = true;
                }
                else
                {
                    container.setVisibility(View.INVISIBLE);
                    btnIsClicked = false;
                }
            }
        });

        registerForContextMenu(resPoints);

        if (!login.isEmpty())
        {
            leaderboard.child("leaderboard").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(top1) && snapshot.child(top1).child("result").getValue(Integer.class) < res)
                    {
                        tempL = snapshot.child(top4).child("login").getValue(String.class);
                        leaderboard.child("leaderboard").child(top5).child("login").setValue(tempL);
                        tempR = snapshot.child(top4).child("result").getValue(Integer.class);
                        leaderboard.child("leaderboard").child(top5).child("result").setValue(tempR);
                        tempL = snapshot.child(top3).child("login").getValue(String.class);
                        leaderboard.child("leaderboard").child(top4).child("login").setValue(tempL);
                        tempR = snapshot.child(top3).child("result").getValue(Integer.class);
                        leaderboard.child("leaderboard").child(top4).child("result").setValue(tempR);
                        tempL = snapshot.child(top2).child("login").getValue(String.class);
                        leaderboard.child("leaderboard").child(top3).child("login").setValue(tempL);
                        tempR = snapshot.child(top2).child("result").getValue(Integer.class);
                        leaderboard.child("leaderboard").child(top3).child("result").setValue(tempR);
                        tempL = snapshot.child(top1).child("login").getValue(String.class);
                        leaderboard.child("leaderboard").child(top2).child("login").setValue(tempL);
                        tempR = snapshot.child(top1).child("result").getValue(Integer.class);
                        leaderboard.child("leaderboard").child(top2).child("result").setValue(tempR);
                        leaderboard.child("leaderboard").child(top1).child("login").setValue(login);
                        leaderboard.child("leaderboard").child(top1).child("result").setValue(res);
                    }
                    else if (snapshot.hasChild(top2) && snapshot.child(top2).child("result").getValue(Integer.class) < res)
                    {
                        tempL = snapshot.child(top4).child("login").getValue(String.class);
                        leaderboard.child("leaderboard").child(top5).child("login").setValue(tempL);
                        tempR = snapshot.child(top4).child("result").getValue(Integer.class);
                        leaderboard.child("leaderboard").child(top5).child("result").setValue(tempR);
                        tempL = snapshot.child(top3).child("login").getValue(String.class);
                        leaderboard.child("leaderboard").child(top4).child("login").setValue(tempL);
                        tempR = snapshot.child(top3).child("result").getValue(Integer.class);
                        leaderboard.child("leaderboard").child(top4).child("result").setValue(tempR);
                        tempL = snapshot.child(top2).child("login").getValue(String.class);
                        leaderboard.child("leaderboard").child(top3).child("login").setValue(tempL);
                        tempR = snapshot.child(top2).child("result").getValue(Integer.class);
                        leaderboard.child("leaderboard").child(top3).child("result").setValue(tempR);
                        leaderboard.child("leaderboard").child(top2).child("login").setValue(login);
                        leaderboard.child("leaderboard").child(top2).child("result").setValue(res);
                    }
                    else if (snapshot.hasChild(top3) && snapshot.child(top3).child("result").getValue(Integer.class) < res)
                    {
                        tempL = snapshot.child(top4).child("login").getValue(String.class);
                        leaderboard.child("leaderboard").child(top5).child("login").setValue(tempL);
                        tempR = snapshot.child(top4).child("result").getValue(Integer.class);
                        leaderboard.child("leaderboard").child(top5).child("result").setValue(tempR);
                        tempL = snapshot.child(top3).child("login").getValue(String.class);
                        leaderboard.child("leaderboard").child(top4).child("login").setValue(tempL);
                        tempR = snapshot.child(top3).child("result").getValue(Integer.class);
                        leaderboard.child("leaderboard").child(top4).child("result").setValue(tempR);
                        leaderboard.child("leaderboard").child(top3).child("login").setValue(login);
                        leaderboard.child("leaderboard").child(top3).child("result").setValue(res);
                    }
                    else if (snapshot.hasChild(top4) && snapshot.child(top4).child("result").getValue(Integer.class) < res)
                    {
                        tempL = snapshot.child(top4).child("login").getValue(String.class);
                        leaderboard.child("leaderboard").child(top5).child("login").setValue(tempL);
                        tempR = snapshot.child(top4).child("result").getValue(Integer.class);
                        leaderboard.child("leaderboard").child(top5).child("result").setValue(tempR);
                        leaderboard.child("leaderboard").child(top4).child("login").setValue(login);
                        leaderboard.child("leaderboard").child(top4).child("result").setValue(res);
                    }
                    else if (snapshot.hasChild(top5) && snapshot.child(top5).child("result").getValue(Integer.class) < res)
                    {
                        leaderboard.child("leaderboard").child(top5).child("login").setValue(login);
                        leaderboard.child("leaderboard").child(top5).child("result").setValue(res);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        TextView login1 = (TextView) findViewById(R.id.login1);
        TextView login2 = (TextView) findViewById(R.id.login2);
        TextView login3 = (TextView) findViewById(R.id.login3);
        TextView login4 = (TextView) findViewById(R.id.login4);
        TextView login5 = (TextView) findViewById(R.id.login5);
        TextView res1 =  (TextView) findViewById(R.id.res1);
        TextView res2 = (TextView) findViewById(R.id.res2);
        TextView res3 = (TextView) findViewById(R.id.res3);
        TextView res4 = (TextView) findViewById(R.id.res4);
        TextView res5 = (TextView) findViewById(R.id.res5);

        leaderboard.child("leaderboard").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(top1))
                {
                    login1.setText(snapshot.child(top1).child("login").getValue(String.class));
                    res1.setText(snapshot.child(top1).child("result").getValue(Integer.class).toString());
                }
                if (snapshot.hasChild(top2)) {
                    login2.setText(snapshot.child(top2).child("login").getValue(String.class));
                    res2.setText(snapshot.child(top2).child("result").getValue(Integer.class).toString());
                }
                if (snapshot.hasChild(top3)) {
                    login3.setText(snapshot.child(top3).child("login").getValue(String.class));
                    res3.setText(snapshot.child(top3).child("result").getValue(Integer.class).toString());
                }
                if (snapshot.hasChild(top4)) {
                    login4.setText(snapshot.child(top4).child("login").getValue(String.class));
                    res4.setText(snapshot.child(top4).child("result").getValue(Integer.class).toString());
                }
                if (snapshot.hasChild(top5)) {
                    login5.setText(snapshot.child(top5).child("login").getValue(String.class));
                    res5.setText(snapshot.child(top5).child("result").getValue(Integer.class).toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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