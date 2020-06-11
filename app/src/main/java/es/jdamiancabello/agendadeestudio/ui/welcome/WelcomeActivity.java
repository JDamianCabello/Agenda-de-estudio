package es.jdamiancabello.agendadeestudio.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.ui.dashboard.DashboardActivity;
import es.jdamiancabello.agendadeestudio.ui.login.LoginActivity;
import es.jdamiancabello.agendadeestudio.ui.register.RegisterActivity;

public class WelcomeActivity extends AppCompatActivity
implements WelcomeFragment.OnWelcomeListener{

    private WelcomeFragment welcomeFragment;
    private WelcomePresenter welcomePresenter;


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
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onGoRegister() {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void onGoDashboard() {
        startActivity(new Intent(this, DashboardActivity.class));
        finishAffinity();
    }
}
