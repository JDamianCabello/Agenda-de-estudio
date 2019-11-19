package es.jdamiancabello.agendadeestudio.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import es.jdamiancabello.agendadeestudio.R;

public class WelcomeFragment extends Fragment {
    private Button btLogin;
    private TextView tvRegister;
    private Fragment loginFragment;
    private Fragment registerFragment;
    public final static String TAG = "WELCOMEFRAGMENT";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_welcome, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btLogin = view.findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFragment = LoginFragment.newInstance();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.content,loginFragment,LoginFragment.TAG);
                fragmentTransaction.commit();
            }
        });

        tvRegister = view.findViewById(R.id.tvRegisterWelcome);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WelcomeFragment.this, RegisterFragment.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            }
        });
    }

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }
}
