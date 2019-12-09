package es.jdamiancabello.agendadeestudio.ui.welcome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
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

    public final static String TAG = "WELCOMEFRAGMENT";
    private OnWelcomeListener onWelcomeListener;

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btLogin = view.findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWelcomeListener.onGoLogin();
            }
        });

        tvRegister = view.findViewById(R.id.tvRegisterWelcome);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWelcomeListener.onGoRegister();
            }
        });
    }

    public interface OnWelcomeListener {
        void onGoLogin();

        void onGoRegister();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnWelcomeListener) {
            onWelcomeListener = (OnWelcomeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnWelcomeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onWelcomeListener = null;


    }
}
