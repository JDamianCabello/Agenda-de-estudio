package es.jdamiancabello.agendadeestudio.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.data.service.FocusBroadcastReceiver;

public class LoginFragment extends Fragment implements LoginContract.View{
    private TextView tvRegister;
    private TextView tvHelp;
    private Button register_btLogin, noti;
    private TextInputEditText tiedEmail;
    private TextInputEditText tiedPassword;
    private Switch swMantenerSesion;
    public final static String TAG = "LoginFragment";
    private onLoginListener activityListener;
    private LoginContract.Presenter presenter;



    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swMantenerSesion = view.findViewById(R.id.login_mantenerSesion);

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
                presenter.loginUser(tiedEmail.getText().toString(),tiedPassword.getText().toString(),swMantenerSesion.isChecked());
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


    @Override
    public void showWrongUserPassMessage() {
        showSnackBarError(getString(R.string.login_err_wrongLoginText));
    }

    private void showSnackBarError(String asd) {
        Snackbar.make(getView(),asd,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showDontExistEmail() {
        showSnackBarError(getString(R.string.login_err_dontExistEmail));
    }

    @Override
    public void showUnknowError() {
        showSnackBarError(getString(R.string.login_err_unknow));
    }

    @Override
    public void saveUSerData(User user,String password) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(getContext().getString(R.string.sharedUserDataLogin), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(User.userKey,user.getEmail());
        editor.putString(User.passwordKey,password);
        editor.putString(User.userToken,user.getApi_token());

        editor.apply();

        activityListener.onSuccesLogin();
    }

    @Override
    public void onSucess() {
        activityListener.onSuccesLogin();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {

    }

    public interface onLoginListener{
        void showHelp();
        void onSuccesLogin();
        void showRegister();
    }
}
