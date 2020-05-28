package es.jdamiancabello.agendadeestudio.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.ui.dashboard.DashboardActivity;

public class RegisterActivity extends AppCompatActivity
implements  RegisterFragment.OnFragmentInteractionListener{
    private RegisterFragment registerFragment;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerFragment = RegisterFragment.newInstance(null);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.registerContainer, registerFragment, RegisterFragment.TAG);
        fragmentTransaction.commit();

        registerPresenter = new RegisterPresenter(registerFragment);
        registerFragment.setPresenter(registerPresenter);
    }

    @Override
    public void onDoneRegister() {
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }
}
