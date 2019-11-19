package es.jdamiancabello.agendadeestudio.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.textfield.TextInputEditText;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.repository.UserRepository;

public class LoginFragment extends Fragment {
    private ImageView ivBack;
    private TextView tvRegister;
    private TextView tvHelp;
    private Button register_btLogin;
    private TextInputEditText tiedEmail;
    private TextInputEditText tiedPassword;
    public final static String TAG = "LOGINFRAGMENT";



    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_register,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivBack = view.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LoginFragment.this, WelcomeFragment.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            }
        });

        tvRegister = view.findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LoginFragment.this, RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));

            }
        });

        tvHelp = view.findViewById(R.id.tvHelp);
        tvHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(LoginFragment.this, AboutMe.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            }
        });


        register_btLogin = view.findViewById(R.id.register_btLogin);
        register_btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserRepository.getInstance().UserLogin(tiedEmail.getText().toString(),tiedPassword.getText().toString())) {
                    //startActivity(new Intent(LoginFragment.this, DashboardActivity.class));
                }
                else
                    Toast.makeText(getContext(),"No existe el usuario",Toast.LENGTH_SHORT).show();
            }
        });

        tiedEmail = view.findViewById(R.id.login_tiedEmail);
        tiedPassword = view.findViewById(R.id.login_tiedPassword);
    }
}
