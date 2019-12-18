package es.jdamiancabello.agendadeestudio.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import es.jdamiancabello.agendadeestudio.R;

public class LoginFragment extends Fragment implements LoginContract.View{
    private ImageView ivBack;
    private TextView tvRegister;
    private TextView tvHelp;
    private Button register_btLogin;
    private LoginButton fbLoginButton;
    private CallbackManager callbackManager;
    private TextInputEditText tiedEmail;
    private TextInputEditText tiedPassword;
    private Switch swMantenerSesion;
    public final static String TAG = "LoginFragment";
    private onLoginListener activityListener;
    private LoginContract.Presenter presenter;

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken != null)
                loadUserProfile(currentAccessToken);
        }
    };

    private void loadUserProfile(AccessToken newtoken){
        GraphRequest request = GraphRequest.newMeRequest(newtoken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String user = object.getString("first_name");
                    String mail = object.getString("email");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("fields","first_name,email");

        request.setParameters(bundle);

        request.executeAsync();
    }

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
        swMantenerSesion = view.findViewById(R.id.login_mantenerSesion);
        fbLoginButton = view.findViewById(R.id.login_fbloginbutton);
        callbackManager = CallbackManager.Factory.create();

        fbLoginButton.setReadPermissions(Arrays.asList("email","public_profile"));
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

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
                presenter.loginUser(tiedEmail.getText().toString(),tiedPassword.getText().toString(),swMantenerSesion.isChecked());
            }
        });


        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showWrongLogin() {
        Snackbar.make(getView(),R.string.login_wrongLoginText,Snackbar.LENGTH_SHORT).show();
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
        void showFacebookRegister();
    }
}
