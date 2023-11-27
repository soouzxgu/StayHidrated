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
    }

    public void voltar (View v){

        Intent i = new Intent(this,CadastroActivity.class);
        startActivity(i);
    }

    private void inicializar(){

        campoPeso = findViewById(R.id.peso_imc);
        campoAltura = findViewById(R.id.altura_imc);

    }

    public void calcularIMC(){


    }
}