package es.jdamiancabello.agendadeestudio.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.utils.CommonUtils;

public class LoginFragment extends Fragment implements LoginContract.View{
    private TextView tvRegister;
    private TextView tvHelp;
    private TextView recoverPass;
    private Button register_btLogin, noti;
    private TextInputEditText tiedEmail;
    private TextInputEditText tiedPassword;
    private Switch swMantenerSesion;
    public final static String TAG = "LoginFragment";
    private onLoginListener activityListener;
    private LoginContract.Presenter presenter;
    private AlertDialog alertDialog;



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

        recoverPass = view.findViewById(R.id.tvForgotPass);
        recoverPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecoverPass();
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
        showToastError(getString(R.string.login_err_wrongLoginText));
    }

    private void showToastError(String asd) {
        Toast toast = Toast.makeText(getContext(), asd, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    public void showDontExistEmail() {
        showToastError(getString(R.string.login_err_dontExistEmail));
    }

    private void showRecoverPass(){
        View recoverPassView = getLayoutInflater().inflate(R.layout.recoverpass_view, null);

        EditText edEmail = recoverPassView.findViewById(R.id.recoverPass_mail);
        Button resendEmail = recoverPassView.findViewById(R.id.recoverPass_btn_sendmail);
        EditText verifyCode = recoverPassView.findViewById(R.id.recoverPass_verificationCode);
        EditText password = recoverPassView.findViewById(R.id.recoverPass_password);
        EditText verifyPass = recoverPassView.findViewById(R.id.recoverPass_checkPassword);
        Button btnUpdatePass = recoverPassView.findViewById(R.id.recoverPass_btn_updatePass);
        TextInputLayout tilEmail = recoverPassView.findViewById(R.id.recoverPass_tilEmail);
        TextInputLayout tilPass = recoverPassView.findViewById(R.id.recoverPass_tilPassword);
        TextInputLayout tilconfirmPass = recoverPassView.findViewById(R.id.recoverPass_tilConfirmPassword);


        tilPass.setVisibility(View.GONE);
        tilconfirmPass.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        verifyPass.setVisibility(View.GONE);
        btnUpdatePass.setVisibility(View.GONE);

        resendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CommonUtils.patternEmail(edEmail.getText().toString()))
                    tilEmail.setError(getString(R.string.errEmail));
                else {
                    tilEmail.setError("");
                    presenter.sendRecoverPassMail(edEmail.getText().toString());
                }
            }
        });

        verifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 8){
                    tilPass.setVisibility(View.VISIBLE);
                    tilconfirmPass.setVisibility(View.VISIBLE);
                    edEmail.setEnabled(false);
                    resendEmail.setVisibility(View.GONE);
                    password.setVisibility(View.VISIBLE);
                    verifyPass.setVisibility(View.VISIBLE);
                    btnUpdatePass.setVisibility(View.VISIBLE);
                }else{
                    tilPass.setVisibility(View.GONE);
                    tilconfirmPass.setVisibility(View.GONE);
                    edEmail.setEnabled(true);
                    resendEmail.setVisibility(View.VISIBLE);
                    password.setVisibility(View.GONE);
                    verifyPass.setVisibility(View.GONE);
                    btnUpdatePass.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnUpdatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CommonUtils.patterPassword(password.getText().toString()))
                    tilPass.setError(getString(R.string.errPassword));
                else{
                    tilPass.setError("");
                    if(!password.getText().toString().equals(verifyPass.getText().toString()))
                        tilconfirmPass.setError(getString(R.string.errNotMatchPass));
                    else
                        presenter.updatePass(password.getText().toString(), verifyCode.getText().toString());
                }
            }
        });


        TextView title = new TextView(getContext());
        title.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark,null));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);
        title.setText(getString(R.string.recoverPass_title));

        alertDialog = new AlertDialog.Builder(getContext())
                .setView(recoverPassView)
                .setCustomTitle(title)
                .setNegativeButton((getResources().getString(R.string.calendarManage_dontSave)), null)
                .show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
    }

    @Override
    public void showUnknowError() {
        showToastError(getString(R.string.login_err_unknow));
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
    public void onDoneRecoverPassEmailSend() {
        showToastError(getString(R.string.recoverPass_emailSendText));
    }

    @Override
    public void onFailRecoverPassEmailSend() {
        showToastError(getString(R.string.recoverPass_failEmailSendText));
    }

    @Override
    public void onSuccesPassUpdated() {
        if(alertDialog != null)
            alertDialog.dismiss();
        showToastError(getString(R.string.recoverPass_passUpdated));
    }

    @Override
    public void onFailedPassupdated() {
        showToastError(getString(R.string.recoverPass_failPassUpdated));
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
