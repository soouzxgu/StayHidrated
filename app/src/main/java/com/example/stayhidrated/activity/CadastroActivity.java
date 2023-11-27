package com.example.stayhidrated.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stayhidrated.R;
import com.example.stayhidrated.Util.ConfigBD;
import com.example.stayhidrated.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    Usuario usuario;
    FirebaseAuth autenticacao;
    EditText campoNome, campoEmail, campoSenha;
    Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializar();
    }

    public void voltar (View v){

        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    private void inicializar(){

        campoNome = findViewById(R.id.nome_cadastro);
        campoEmail = findViewById(R.id.email_cadastro);
        campoSenha = findViewById(R.id.senha_cadastro);
        btCadastrar = findViewById(R.id.bt_cadastrar);


    }

    public void validarCampos(View v){

        String nome = campoNome.getText().toString();
        String senha = campoSenha.getText().toString();
        String email = campoEmail.getText().toString();

        if(!nome.isEmpty()){
            if(!email.isEmpty()){
                if(!senha.isEmpty()){

                    usuario = new Usuario();

                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha(senha);

                //Cadastrar Usuário

        cadastrarUsuario();

                }else{
                    Toast.makeText(this,"Preencha sua senha!", Toast.LENGTH_SHORT).show();

                }

            }
            else{
                Toast.makeText(this,"Preencha seu e-mail!", Toast.LENGTH_SHORT).show();

            }

        }
        else{
            Toast.makeText(this,"Preencha seu usuário!", Toast.LENGTH_SHORT).show();
        }
    }

    private void cadastrarUsuario() {

        autenticacao = ConfigBD.Firebaseautenticador();

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()

        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar o usuário!", Toast.LENGTH_SHORT).show();

                    abrirIMC();

                }else{

                    String excecao = "";

                    try{
                        throw task.getException();

                    }catch(FirebaseAuthWeakPasswordException e){

                        excecao = "Digite uma senha mais forte";

                    }catch(FirebaseAuthInvalidCredentialsException e){

                        excecao = "Digite um  e-mail inválido!";

                    }catch(FirebaseAuthUserCollisionException e){

                        excecao = "E-mail já cadastrado!";

                    }catch(Exception e){

                        excecao = "Erro ao cadastar usuário!" + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    private void abrirIMC() {

        Intent i = new Intent(CadastroActivity.this,IMCActivity.class);
        startActivity(i);
    }

}
