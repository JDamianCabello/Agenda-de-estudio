package es.jdamiancabello.agendadeestudio.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;
import es.jdamiancabello.agendadeestudio.ui.aboutme.AboutMeActivity;
import es.jdamiancabello.agendadeestudio.ui.dashboard.DashboardActivity;
import es.jdamiancabello.agendadeestudio.ui.register.RegisterActivity;
import es.jdamiancabello.agendadeestudio.ui.verifyemail.VerifyEmailFragment;
import es.jdamiancabello.agendadeestudio.ui.verifyemail.VerifyEmailPresenter;

public class LoginActivity extends AppCompatActivity implements LoginFragment.onLoginListener, VerifyEmailFragment.OnFragmentInteractions{

    private LoginFragment loginFragment;
    private LoginPresenter loginPresenter;

    private VerifyEmailFragment verifyEmailFragment;
    private VerifyEmailPresenter verifyEmailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginFragment = LoginFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.loginContainer,loginFragment,LoginFragment.TAG);
        fragmentTransaction.commit();

        loginPresenter = new LoginPresenter(loginFragment);
        loginFragment.setPresenter(loginPresenter);

    }

    @Override
    public void showHelp() {
        startActivity(new Intent(this, AboutMeActivity.class));
    }

    @Override
    public void onSuccesLogin() {
        if(FocusApplication.getUser().isVerified()) {
            goDashboard();
        }
        else{
            showVerifyFragment();
        }
    }

    private void goDashboard() {
        startActivity(new Intent(this, DashboardActivity.class));
        finishAffinity();
    }

    private void showVerifyFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Subject.SUBJECT_KEY,FocusApplication.getUser());
        verifyEmailFragment = VerifyEmailFragment.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.loginContainer,verifyEmailFragment,VerifyEmailFragment.TAG);
        fragmentTransaction.addToBackStack(LoginFragment.TAG);
        fragmentTransaction.commit();

        verifyEmailPresenter = new VerifyEmailPresenter(verifyEmailFragment);
        verifyEmailFragment.setPresenter(verifyEmailPresenter);
    }

    private void showChangePassFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Subject.SUBJECT_KEY,FocusApplication.getUser());
        verifyEmailFragment = VerifyEmailFragment.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.loginContainer,verifyEmailFragment,VerifyEmailFragment.TAG);
        fragmentTransaction.addToBackStack(LoginFragment.TAG);
        fragmentTransaction.commit();

        verifyEmailPresenter = new VerifyEmailPresenter(verifyEmailFragment);
        verifyEmailFragment.setPresenter(verifyEmailPresenter);
    }

    @Override
    public void showRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    @Override
    public void onVerified() {
        goDashboard();
    }

    @Override
    public void onReturnLoginView() {
        onBackPressed();
    }

}