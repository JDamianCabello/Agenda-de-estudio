package es.jdamiancabello.agendadeestudio.ui.welcome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import es.jdamiancabello.agendadeestudio.R;

public class WelcomeFragment extends Fragment implements WelcomeContract.view{
    WelcomeContract.Presenter presenter;
    private Button btLogin;
    private TextView tvRegister;

    public final static String TAG = "WELCOMEFRAGMENT";
    private OnWelcomeListener onWelcomeListener;

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter.checkUserLogged();
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

    @Override
    public void onSucess() {

    }

    @Override
    public void setPresenter(WelcomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {

    }

    @Override
    public void existUserLogged() {
        onWelcomeListener.onGoDashboard();
    }

    public interface OnWelcomeListener {
        void onGoLogin();
        void onGoRegister();
        void onGoDashboard();
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
