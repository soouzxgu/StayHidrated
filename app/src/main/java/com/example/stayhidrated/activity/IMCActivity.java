package com.example.stayhidrated.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.stayhidrated.R;

public class IMCActivity extends AppCompatActivity {

    EditText campoPeso, campoAltura;
    Button btCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imcactivity);

        campoPeso = findViewById(R.id.peso_imc);
        campoAltura = findViewById(R.id.altura_imc);
        btCalcular = findViewById(R.id.btCalcular);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double peso = Double.parseDouble(campoPeso.getText().toString());
                double altura = Double.parseDouble(campoAltura.getText().toString());
                double imc = peso / (altura * altura);

                    if(imc < 18.5){

                        imc = 200;
                    }

                    else if(imc >= 18.5 && imc > 25){

                        imc = 250;

                    }

                    else if(imc >=25 && imc > 30){

                        imc = 350;

                    }

                    else if(imc>=30 && imc > 40){

                        imc = 400;

                    }

            }
        });

    }

    public void voltar (View v){

        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    }
