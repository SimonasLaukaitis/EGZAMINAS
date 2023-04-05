package com.example.egzaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SecondActivity extends AppCompatActivity {

    private TextView name,email;
    private Button signOutBtn, pirmyn;

    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name = (TextView)findViewById(R.id.textViewName);
        email = (TextView)findViewById(R.id.textViewEmail);
        signOutBtn = (Button)findViewById(R.id.buttonSignOut);
        pirmyn = (Button)findViewById(R.id.buttonPirmyn);


        //LoginInas
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText("Sveiki, " + personName);
            email.setText(personEmail);

        }

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signOut();
            }
        });

        pirmyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openThirdActivity();
            }
        });


    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>(){
            @Override
            public void onComplete( Task<Void> task) {
                finish();
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
            }
        });
    }

    void openThirdActivity(){
        Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
        startActivity(intent);
    };
}