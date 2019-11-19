package es.jdamiancabello.agendadeestudio.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.textfield.TextInputEditText;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.repository.UserRepository;

public class LoginActivity extends AppCompatActivity {
    private ImageView ivBack;
    private TextView tvRegister;
    private TextView tvHelp;
    private Button register_btLogin;
    private TextInputEditText tiedEmail;
    private TextInputEditText tiedPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, WelcomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                Animatoo.animateSlideRight(LoginActivity.this);

            }
        });

        tvRegister = findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                Animatoo.animateSlideDown(LoginActivity.this);

            }
        });

        tvHelp = findViewById(R.id.tvHelp);
        tvHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, AboutMe.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                Animatoo.animateSlideRight(LoginActivity.this);
            }
        });


        register_btLogin = findViewById(R.id.register_btLogin);
        register_btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserRepository.getInstance().UserLogin(tiedEmail.getText().toString(),tiedPassword.getText().toString())) {
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    Animatoo.animateSlideRight(LoginActivity.this);
                    finish();
                }
                else
                    Toast.makeText(LoginActivity.this,"No existe el usuario",Toast.LENGTH_SHORT).show();
            }
        });

        tiedEmail = findViewById(R.id.login_tiedEmail);
        tiedPassword = findViewById(R.id.login_tiedPassword);
    }
}
