package com.example.stayhidrated.activity;

import static com.example.stayhidrated.R.id.senha_login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stayhidrated.R;
import com.example.stayhidrated.Util.ConfigBD;
import com.example.stayhidrated.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText campoEmail, campoSenha;
    Button btAcessar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciarComponetes();
        auth = ConfigBD.Firebaseautenticador();
    }

    public void validarAutenticacao(View view){

        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(!email.isEmpty()){

            if(!senha.isEmpty()){

                Usuario usuario = new Usuario();

                usuario.setEmail(email);
                usuario.setSenha(senha);

                logar(usuario);

            }else{

                Toast.makeText(this, "Preencha o campo senha!", Toast.LENGTH_SHORT).show();
            }

        }else{

            Toast.makeText(this, "Preencha o campo email!", Toast.LENGTH_SHORT).show();

        }

    }

    private void logar(Usuario usuario) {

        auth.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(task -> {

            if(task.isSuccessful()){

                abrirHome();

            }else{

                String excec;

                try{

                    throw Objects.requireNonNull(task.getException());

                }catch(FirebaseAuthInvalidUserException e){

                    excec = "Usuário não cadastrado!";

                }catch(FirebaseAuthInvalidCredentialsException e){

                    excec = "E-mail ou senha incorreto!";

                }catch(Exception e){

                    excec = "Erro ao acessar!"+e.getMessage();
                    e.printStackTrace();
                }

                Toast.makeText(LoginActivity.this, excec, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void abrirHome() {

        Intent i = new Intent(LoginActivity.this,PrincipalActivity.class);
        startActivity(i);
    }

    public void cadastrar (View v){

        Intent i = new Intent(this,CadastroActivity.class);
        startActivity(i);
    }

    private void iniciarComponetes(){

        campoEmail = findViewById(R.id.email_login);
        campoSenha = findViewById(R.id.senha_login);
        btAcessar = findViewById(R.id.btAcessar);
    }



}