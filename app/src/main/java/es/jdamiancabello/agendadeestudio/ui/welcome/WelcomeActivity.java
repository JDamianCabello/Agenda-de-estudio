package es.jdamiancabello.agendadeestudio.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.ui.aboutme.AboutMeActivity;
import es.jdamiancabello.agendadeestudio.ui.dashboard.DashboardActivity;
import es.jdamiancabello.agendadeestudio.ui.login.LoginFragment;
import es.jdamiancabello.agendadeestudio.ui.login.LoginPresenter;
import es.jdamiancabello.agendadeestudio.ui.register.RegisterActivity;

public class WelcomeActivity extends AppCompatActivity
implements WelcomeFragment.OnWelcomeListener,
LoginFragment.onLoginListener{

    private WelcomeFragment welcomeFragment;
    private WelcomePresenter welcomePresenter;
    private LoginFragment loginFragment;
    protected LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeFragment = WelcomeFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.welcomeContainer,welcomeFragment,WelcomeFragment.TAG);
        fragmentTransaction.commit();

        welcomePresenter = new WelcomePresenter(welcomeFragment);
        welcomeFragment.setPresenter(welcomePresenter);
    }

    @Override
    public void onGoLogin() {
        loginFragment = LoginFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.welcomeContainer,loginFragment,LoginFragment.TAG);
        fragmentTransaction.commit();

        loginPresenter = new LoginPresenter(loginFragment);
        loginFragment.setPresenter(loginPresenter);
    }

    @Override
    public void onGoRegister() {
        startActivity(new Intent(this,RegisterActivity.class));
        finish();
    }

    @Override
    public void onGoDashboard() {
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    @Override
    public void showHelp() {
        startActivity(new Intent(this, AboutMeActivity.class));
        finish();
    }

    @Override
    public void onSuccesLogin() {
        onGoDashboard();
    }

    @Override
    public void showRegister() {
        startActivity(new Intent(this,RegisterActivity.class));
        finish();
    }

    @Override
    public void showFacebookRegister(AccessToken accessToken) {
        //TODO: Implementar login con FB
    }
}
