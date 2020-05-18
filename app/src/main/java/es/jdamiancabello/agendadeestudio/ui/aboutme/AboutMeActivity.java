package es.jdamiancabello.agendadeestudio.ui.aboutme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.ui.welcome.WelcomeActivity;

public class AboutMeActivity extends AppCompatActivity implements
AboutMeFragment.OnFragmentInteractionListener{
    AboutMeFragment aboutMeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        aboutMeFragment = AboutMeFragment.newInstance(false);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.aboutMeContainer,aboutMeFragment,AboutMeFragment.TAG);
        fragmentTransaction.commit();

    }

    @Override
    public void onLogout() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedUserDataLogin),MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();

        editor.apply();

        ApiRestClientToken.loggout();
        goWelcome();
    }

    private void goWelcome() {
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }
}
