package com.example.stayhidrated.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.stayhidrated.R;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void voltar (View v){

        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    public void imc (View v){

        Intent i = new Intent(this,IMCActivity.class);
        startActivity(i);
    }
}
