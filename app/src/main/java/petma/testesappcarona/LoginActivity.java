package petma.testesappcarona;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {


    // Email, password EditText
    EditText inputMatricula , inputPassword;

    // login button
    Button btnLogin;

    // Session Manager Class
    SessionManager session; // criando um objeto da classe sessionmanager

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Session Manager -- craia objeto da classe SessionManager
        session = new SessionManager(getApplicationContext());

        // Email, Password input text
        inputMatricula = (EditText) findViewById(R.id.login_input_matricula);
        inputPassword = (EditText) findViewById(R.id.input_password);

        // Pegando o numero de matrícula e a senha


        // Login button
        btnLogin = (Button) findViewById(R.id.login_btn);

        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String matricula = inputMatricula.getText().toString();
                String password = inputPassword.getText().toString();

                // Check if username, password is filled
                if(matricula.trim().length() < 8 && password.trim().length() < 6){
                    Toast toast =  Toast.makeText(getApplicationContext(),"Usuário ou senha inválidos, \n" +
                            "para mais informações clique em 'HELP'",Toast.LENGTH_LONG);
                   toast.setGravity(Gravity.CENTER,0,0);
                   toast.show();
                }
                else if(matricula.trim().length() < 8){
                    inputMatricula.setError("Digite seu número de matrícula");
                }
                else if(password.trim().length() < 6){
                    inputPassword.setError("Digite sua senha do CAGR/MOODLE");
                }
                else{
                    // try to log in
                    // catch --> display error, incrementa contador ?
                    //session.createLoginSession(); // só cria log_in se já tem cadastro
                    session.createUserSession(null,null,null, matricula); // coloca matricula em Sharede Pref
                    session.createPass(password);
                    // se foi feito log_in, vai ir pra cadastrar
                    //TODO: Verificar se ta no DB, se ta, goto lista, se não goto cadastro

                    open(); // abre a próxima activity
                    //


                }

            }
        });

    }

    // onBackPressed lida com as ações quando o usuário clica no botão de retorno
    @Override
    public void onBackPressed() {
        Toast teste = Toast.makeText(getApplicationContext(),"Can´t return",Toast.LENGTH_LONG);
        teste.setGravity(0,0,Gravity.CENTER);
        teste.show();
    }

    public void open(){
        Intent i = new Intent(getApplicationContext(), cadastro.class);
        startActivity(i);
        finish();
    }

}