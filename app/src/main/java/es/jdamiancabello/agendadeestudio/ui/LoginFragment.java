package es.jdamiancabello.agendadeestudio.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
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
    private Fragment dashboardFragment;
    private Fragment aboutMeFragment;
    private onLoginListener activityListener;


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
                getFragmentManager().popBackStack();
            }
        });

        tvRegister = view.findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.showRegister();
            }
        });

        tvHelp = view.findViewById(R.id.tvHelp);
        tvHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityListener.showHelp();
            }
        });


        tiedEmail = view.findViewById(R.id.login_tiedEmail);
        tiedPassword = view.findViewById(R.id.login_tiedPassword);

        register_btLogin = view.findViewById(R.id.registerBtLogin);
        register_btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityListener.checkUser(tiedEmail.getText().toString(),tiedPassword.getText().toString());
            }
        });
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activityListener = (onLoginListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    interface onLoginListener{
        void showHelp();
        void checkUser(String user, String password);
        void showRegister();
    }
}
