package app.gramatic.gramaticapp.Activities;

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
import com.google.firebase.auth.FirebaseUser;

import app.gramatic.gramaticapp.Modelo.Base64Custom;
import app.gramatic.gramaticapp.Modelo.ConfiguracaoFirebase;
import app.gramatic.gramaticapp.Modelo.Preferencias;
import app.gramatic.gramaticapp.Modelo.Usuarios;
import app.gramatic.gramaticapp.R;

public class SinginActivity extends AppCompatActivity {

    private EditText signinEmail;
    private EditText signinSenha;
    private EditText signinNome;
    private EditText signinSobrenome;
    private EditText signinNascimento;
    private EditText signinSexo;

    private Button signBtn;

    private Usuarios usuarios;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);

        signinEmail = (EditText)findViewById(R.id.signMail);
        signinSenha = (EditText)findViewById(R.id.signSenha);

        signBtn = (Button)findViewById(R.id.btnSignin);

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implementar verificação de igualdade de senha
                usuarios = new Usuarios();
                usuarios.setEmail(signinEmail.getText().toString());
                usuarios.setSenha(signinSenha.getText().toString());
            }
        });
    }

    private void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()
        ).addOnCompleteListener(SinginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SinginActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    String identificadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setId(identificadorUsuario);
                    usuarios.salvar();

                    Preferencias preferencias = new Preferencias(SinginActivity.this);
                    preferencias.salvarUsuarioPreferencias(identificadorUsuario, usuarios.getNome());
                }
            }
        });
    }

}
