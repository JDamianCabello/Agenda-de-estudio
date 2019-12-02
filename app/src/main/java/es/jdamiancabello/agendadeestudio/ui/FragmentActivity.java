package es.jdamiancabello.agendadeestudio.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.adapter.StudyOrganicerAdapter;
import es.jdamiancabello.agendadeestudio.data.model.StudyOrganicer;
import es.jdamiancabello.agendadeestudio.data.repository.UserRepository;
import es.jdamiancabello.agendadeestudio.ui.studyorganicer.StudyOrganicerListContract;
import es.jdamiancabello.agendadeestudio.ui.studyorganicer.StudyOrganicerManageContract;
import es.jdamiancabello.agendadeestudio.ui.studyorganicer.StudyOrganicerManagePresenter;
import es.jdamiancabello.agendadeestudio.ui.studyorganicer.StudyOrganicerManageView;
import es.jdamiancabello.agendadeestudio.ui.studyorganicer.StudyOrganicerPresenter;
import es.jdamiancabello.agendadeestudio.ui.studyorganicer.StudyOrganicerView;

public class FragmentActivity extends AppCompatActivity implements SubjectListFragment.onSubjectListListener, DashboardFragment.onDashboardListener,LoginFragment.onLoginListener, StudyOrganicerView.SectorListViewListener, StudyOrganicerManageView.OnSaveStudyOrganicerManageView {

    private Fragment welcomeFragment;
    private Fragment subjectList;
    private Fragment aboutMeFragment;
    private Fragment registerFragment;
    private Fragment dashboardFragment;
    private Fragment eventListFragment;
    private Fragment eventManageFragment;

    private StudyOrganicerPresenter studyOrganicerPresenter;
    private StudyOrganicerManagePresenter studyOrganicerManagePresenter;

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
        onBackPressed();
    }

    @Override
    public void showSubjectsList() {
        subjectList = getSupportFragmentManager().findFragmentByTag(SubjectListFragment.TAG);
        if (subjectList==null)
            subjectList= SubjectListFragment.newInstance();


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,subjectList,SubjectListFragment.TAG);
        fragmentTransaction.addToBackStack(DashboardFragment.TAG);
        fragmentTransaction.commit();

    }

    @Override
    public void showEventsList() {
        eventListFragment = StudyOrganicerView.newInstance(null);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,eventListFragment,StudyOrganicerView.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        studyOrganicerPresenter = new StudyOrganicerPresenter((StudyOrganicerListContract.View) eventListFragment);
        ((StudyOrganicerListContract.View) eventListFragment).setPresenter(studyOrganicerPresenter);
    }

    @Override
    public void ShowChronoView() {
        Toast.makeText(this,"ChronoView is not implemented yet",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowSettingsView() {
        Toast.makeText(this,"SettingsView is not implemented yet",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowScheduleView() {
        Toast.makeText(this,"ScheduleView is not implemented yet",Toast.LENGTH_SHORT).show();
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

    @Override
    public void sectorAddEditFragmentShow(StudyOrganicer studyOrganicer) {

        eventManageFragment = getSupportFragmentManager().findFragmentByTag(StudyOrganicerManageView.TAG);

        if(eventManageFragment == null){
            Bundle b = null;
            if(studyOrganicer != null){
                b = new Bundle();
                b.putParcelable("event",studyOrganicer);
            }

            eventManageFragment = StudyOrganicerManageView.newInstance(b);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(android.R.id.content,eventManageFragment,StudyOrganicerManageView.TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        studyOrganicerManagePresenter = new StudyOrganicerManagePresenter((StudyOrganicerManageContract.View) eventManageFragment);
        ((StudyOrganicerManageContract.View) eventManageFragment).setPresenter(studyOrganicerManagePresenter);

    }

    @Override
    public void onSaveStudyOrganicerManageView() {
        onBackPressed();
    }

    @Override
    public void onSnackBarActionCreateSubject() {
        eventListFragment = StudyOrganicerView.newInstance(null);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,eventListFragment,StudyOrganicerView.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        studyOrganicerPresenter = new StudyOrganicerPresenter((StudyOrganicerListContract.View) eventListFragment);
        ((StudyOrganicerListContract.View) eventListFragment).setPresenter(studyOrganicerPresenter);
    }
}
