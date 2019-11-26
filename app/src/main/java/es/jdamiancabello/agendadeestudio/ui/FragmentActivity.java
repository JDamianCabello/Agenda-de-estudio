package es.jdamiancabello.agendadeestudio.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.repository.UserRepository;

public class FragmentActivity extends AppCompatActivity implements SubjectListFragment.onSubjectListListener, DashboardFragment.onDashboardListener,LoginFragment.onLoginListener{

    private Fragment welcomeFragment;
    private Fragment subjectList;
    private Fragment aboutMeFragment;
    private Fragment registerFragment;
    private Fragment dashboardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        welcomeFragment = WelcomeFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(android.R.id.content,welcomeFragment,LoginFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void addSubject(Bundle subject) {

    }

    @Override
    public void showSubjectsList() {
        subjectList = getSupportFragmentManager().findFragmentByTag(SubjectListFragment.TAG);
        if (subjectList==null)
            subjectList= SubjectListFragment.newInstance();

        subjectList = SubjectListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
        .replace(android.R.id.content,subjectList,SubjectListFragment.TAG)
        .commit();

    }

    @Override
    public void showHelp() {
        aboutMeFragment = AboutMeFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,aboutMeFragment,AboutMeFragment.TAG);
        fragmentTransaction.addToBackStack("WelcomeToAboutMe");
        fragmentTransaction.commit();
    }

    @Override
    public void checkUser(String user, String password) {
        if(UserRepository.getInstance().UserLogin(user,password)) {
            dashboardFragment = DashboardFragment.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(android.R.id.content,dashboardFragment,DashboardFragment.TAG);
            fragmentTransaction.commit();
        }
        else
            Toast.makeText(this,"No existe el usuario",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showRegister() {
        registerFragment = RegisterFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,registerFragment,RegisterFragment.TAG);
        fragmentTransaction.addToBackStack("LoginToRegister");
        fragmentTransaction.commit();
    }
}
