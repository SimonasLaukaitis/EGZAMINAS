package com.example.egzaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ThirdActivity extends AppCompatActivity {

    private TextView inputTemp, countedTemp;
    private Button celsius, farenheit, random, openGoogle,back;
    private ImageView portretas;

    private double temp;
    private int skaicius;

    private int[] paveiksliukai = {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5};
    private Random sk = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        inputTemp = findViewById(R.id.editTextNumberTemperature);
        countedTemp = findViewById(R.id.textViewTemperatureCounted);
        celsius = findViewById(R.id.buttonCelsius);
        farenheit = findViewById(R.id.buttonFarenheit);
        portretas = findViewById(R.id.imageViewPortretas);
        random = findViewById(R.id.buttonRandom);
        openGoogle = findViewById(R.id.buttonGoogle);
        back = findViewById(R.id.buttonBack);

        //Prisijungus randomisinam paveiksliuka
        skaicius = sk.nextInt(5);
        portretas.setImageResource(paveiksliukai[skaicius]);

        //Temp skaiciavimas

        farenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isTextViewNullOrEmpty(inputTemp)){
                    temp =Integer.parseInt(inputTemp.getText().toString());
                    countedTemp.setText(String.valueOf(String.format("%.2f", celsiusToFahrenheit(temp)) + " F°"));
                } else {
                    countedTemp.setText(String.valueOf("LAUKELIS TUŠČIAS"));
                }

            }
        });

        celsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(isTextViewNullOrEmpty(inputTemp)){
                    temp =Integer.parseInt(inputTemp.getText().toString());
                    countedTemp.setText(String.valueOf(String.format("%.2f", fahrenheitToCelsius(temp)) + " C°"));
                } else {
                    countedTemp.setText(String.valueOf("LAUKELIS TUŠČIAS"));
                }
            }
        });

        //Randomisinam paveiksliuka su mygtuku
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                skaicius = sk.nextInt(5);
                portretas.setImageResource(paveiksliukai[skaicius]);


            }
        });

        openGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(browserIntent);


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ThirdActivity.this,SecondActivity.class);
                startActivity(intent);


            }
        });



    }

     double celsiusToFahrenheit(double celsius) {
        double fahrenheit = (celsius * 1.8) + 32;
        return fahrenheit;
    }

    double fahrenheitToCelsius(double fahrenheit) {
        double celsius = (fahrenheit - 32) / 1.8;
        return celsius;
    }

    boolean isTextViewNullOrEmpty(TextView textView) {
        return !textView.getText().toString().trim().isEmpty();
    }
}