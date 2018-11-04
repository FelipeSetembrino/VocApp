package app.gramatic.gramaticapp.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import app.gramatic.gramaticapp.Controle.MetodosUtilidades;
import app.gramatic.gramaticapp.Modelo.ConfiguracaoFirebase;
import app.gramatic.gramaticapp.Modelo.Usuarios;
import app.gramatic.gramaticapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editMail;
    private EditText editSenha;
    private Button btnLogar;
    private Button btnSignin;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editMail = (EditText) findViewById(R.id.editMail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        btnLogar = (Button) findViewById(R.id.btnLogin);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MetodosUtilidades.checkNull(editMail.getText().toString(), editSenha.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Preencher os campos de email e/ou senha!", Toast.LENGTH_SHORT).show();
                }
                else{
                    usuarios = new Usuarios();
                    usuarios.setEmail(editMail.getText().toString());
                    usuarios.setSenha(editSenha.getText().toString());
                    validarLogin();
                }
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingnActivity();
            }
        });

    }

    public void validarLogin(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    openActivityMain();
                    Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openActivityMain(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
    }

    public void openSingnActivity(){
        Intent intent = new Intent(LoginActivity.this, SinginActivity.class);
    }

}
