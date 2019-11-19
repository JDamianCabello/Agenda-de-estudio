package es.jdamiancabello.agendadeestudio.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    public final static String TAG = "LoginFragment";
    private Fragment registerFragment;
    private Fragment welcomeFragment;
    private Fragment dashboardFragment;
    private Fragment aboutMeFragment;


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_login,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivBack = view.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeFragment = WelcomeFragment.newInstance();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(LoginFragment.TAG);
                fragmentTransaction.replace(android.R.id.content,welcomeFragment,WelcomeFragment.TAG);
                fragmentTransaction.commit();
            }
        });

        tvRegister = view.findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerFragment = RegisterFragment.newInstance();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.content,registerFragment,RegisterFragment.TAG);
                fragmentTransaction.commit();

            }
        });

        tvHelp = view.findViewById(R.id.tvHelp);
        tvHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutMeFragment = AboutMeFragment.newInstance();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.content,aboutMeFragment,AboutMeFragment.TAG);
                fragmentTransaction.commit();
            }
        });


        register_btLogin = view.findViewById(R.id.registerBtLogin);
        register_btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserRepository.getInstance().UserLogin(tiedEmail.getText().toString(),tiedPassword.getText().toString())) {
                    dashboardFragment = DashboardFragment.newInstance();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(android.R.id.content,dashboardFragment,DashboardFragment.TAG);
                    fragmentTransaction.commit();
                }
                else
                    Toast.makeText(getContext(),"No existe el usuario",Toast.LENGTH_SHORT).show();
            }
        });

        tiedEmail = view.findViewById(R.id.login_tiedEmail);
        tiedPassword = view.findViewById(R.id.login_tiedPassword);
    }
}
