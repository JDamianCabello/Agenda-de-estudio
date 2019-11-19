package es.jdamiancabello.agendadeestudio.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import es.jdamiancabello.agendadeestudio.R;

public class WelcomeActivity extends AppCompatActivity {
    private Button btLogin;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                Animatoo.animateSlideLeft(WelcomeActivity.this);
            }
        });

        tvRegister = findViewById(R.id.tvRegisterWelcome);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                Animatoo.animateSlideUp(WelcomeActivity.this);

            }
        });


    }
}
