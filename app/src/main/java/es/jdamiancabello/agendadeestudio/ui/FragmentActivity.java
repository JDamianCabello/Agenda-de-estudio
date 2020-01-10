package es.jdamiancabello.agendadeestudio.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;


import com.facebook.AccessToken;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.Note;
import es.jdamiancabello.agendadeestudio.data.model.StudyOrganicer;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.register.RegisterFragment;
import es.jdamiancabello.agendadeestudio.register.RegisterPresenter;
import es.jdamiancabello.agendadeestudio.ui.aboutme.AboutMeFragment;
import es.jdamiancabello.agendadeestudio.ui.login.LoginContract;
import es.jdamiancabello.agendadeestudio.ui.login.LoginFragment;
import es.jdamiancabello.agendadeestudio.ui.login.LoginPresenter;
import es.jdamiancabello.agendadeestudio.ui.notes.NoteListPresenter;
import es.jdamiancabello.agendadeestudio.ui.notes.NotesListFragment;
import es.jdamiancabello.agendadeestudio.ui.studyorganicer.StudyOrganicerListContract;
import es.jdamiancabello.agendadeestudio.ui.studyorganicer.StudyOrganicerManageContract;
import es.jdamiancabello.agendadeestudio.ui.studyorganicer.StudyOrganicerManagePresenter;
import es.jdamiancabello.agendadeestudio.ui.studyorganicer.StudyOrganicerManageView;
import es.jdamiancabello.agendadeestudio.ui.studyorganicer.StudyOrganicerPresenter;
import es.jdamiancabello.agendadeestudio.ui.studyorganicer.StudyOrganicerView;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectListContract;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectListFragment;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectListPresenter;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectManagerFragment;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectManagerPresenter;
import es.jdamiancabello.agendadeestudio.ui.welcome.WelcomeContract;
import es.jdamiancabello.agendadeestudio.ui.welcome.WelcomeFragment;
import es.jdamiancabello.agendadeestudio.ui.welcome.WelcomePresenter;

public class FragmentActivity extends AppCompatActivity implements
        SubjectListFragment.onSubjectListListener,
        DashboardFragment.onDashboardListener,
        LoginFragment.onLoginListener,
        StudyOrganicerView.SectorListViewListener,
        StudyOrganicerManageView.OnSaveStudyOrganicerManageView,
        WelcomeFragment.OnWelcomeListener,
        NotesListFragment.OnFragmentInteractionListener,
        RegisterFragment.OnFragmentInteractionListener,
        SubjectManagerFragment.OnFragmentInteractionListener
        {
    private Toolbar toolbar;

    private Fragment welcomeFragment;
    private WelcomePresenter welcomePresenter;

    private Fragment subjectList;
    private SubjectListPresenter subjectListPresenter;
    private SubjectManagerFragment subjectManagerFragment;
    private SubjectManagerPresenter subjectManagerPresenter;


    private Fragment aboutMeFragment;
    private Fragment loginFragment;
    private LoginPresenter loginPresenter;

    private RegisterFragment registerFragment;
    private RegisterPresenter registerPresenter;

    private Fragment dashboardFragment;
    private Fragment eventListFragment;
    private Fragment eventManageFragment;

    private StudyOrganicerPresenter studyOrganicerPresenter;
    private StudyOrganicerManagePresenter studyOrganicerManagePresenter;

    private NotesListFragment notesListFragment;
    private NoteListPresenter noteListPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showWelcome();
    }

    private void showWelcome() {
        welcomeFragment = WelcomeFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content,welcomeFragment,WelcomeFragment.TAG);
        fragmentTransaction.commit();

        welcomePresenter = new WelcomePresenter((WelcomeContract.view) welcomeFragment);
        ((WelcomeContract.view) welcomeFragment).setPresenter(welcomePresenter);
    }

    @Override
    public void showSubjectsList() {
        subjectList = getSupportFragmentManager().findFragmentByTag(SubjectListFragment.TAG);
        if (subjectList==null) {
            subjectList = SubjectListFragment.newInstance();


            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, subjectList, SubjectListFragment.TAG);
            fragmentTransaction.addToBackStack(DashboardFragment.TAG);
            fragmentTransaction.commit();
        }

        subjectListPresenter = new SubjectListPresenter((SubjectListContract.View) subjectList);
        ((SubjectListContract.View) subjectList).setPresenter(subjectListPresenter);
    }

    @Override
    public void showEventsList() {

        eventListFragment = getSupportFragmentManager().findFragmentByTag(StudyOrganicerView.TAG);

        if(eventListFragment == null) {
            eventListFragment = StudyOrganicerView.newInstance(null);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, eventListFragment, StudyOrganicerView.TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        studyOrganicerPresenter = new StudyOrganicerPresenter((StudyOrganicerListContract.View) eventListFragment);
        ((StudyOrganicerListContract.View) eventListFragment).setPresenter(studyOrganicerPresenter);
    }

    @Override
    public void ShowChronoView() {
        Toast.makeText(this,"ChronoView is not implemented yet",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowSettingsView() {
        Toast.makeText(this,"Settings is not implemented yet",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowScheduleView() {
        Toast.makeText(this,"ScheduleView is not implemented yet",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoteView() {
        notesListFragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(NotesListFragment.TAG);

        if(notesListFragment == null){
            notesListFragment = NotesListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content,notesListFragment,NotesListFragment.TAG)
                    .addToBackStack(null).commit();
        }

        noteListPresenter = new NoteListPresenter(notesListFragment);
        notesListFragment.setPresenter(noteListPresenter);

    }

            @Override
            public void loggout() {
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedUserDataLogin),MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();

                editor.apply();

                ApiRestClientToken.loggout();
                showWelcome();
            }

            @Override
    public void showHelp() {
        aboutMeFragment = AboutMeFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,aboutMeFragment,AboutMeFragment.TAG);
        fragmentTransaction.addToBackStack("WelcomeToAboutMe");
        fragmentTransaction.commit();
    }

    @Override
    public void onSuccesLogin() {
        dashboardFragment = DashboardFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,dashboardFragment,DashboardFragment.TAG);
        fragmentTransaction.commit();
    }


    @Override
    public void showRegister() {
        registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentByTag(RegisterFragment.TAG);

        if(registerFragment == null){
            registerFragment = RegisterFragment.newInstance(null);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content,registerFragment, RegisterFragment.TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        registerPresenter = new RegisterPresenter(registerFragment);
        registerFragment.setPresenter(registerPresenter);


    }

    @Override
    public void showFacebookRegister(AccessToken accessToken) {

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
            fragmentTransaction.replace(R.id.content,eventManageFragment,StudyOrganicerManageView.TAG);
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
        fragmentTransaction.replace(R.id.content,eventListFragment,StudyOrganicerView.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        studyOrganicerPresenter = new StudyOrganicerPresenter((StudyOrganicerListContract.View) eventListFragment);
        ((StudyOrganicerListContract.View) eventListFragment).setPresenter(studyOrganicerPresenter);
    }

    @Override
    public void addSubject(Subject subject) {
        subjectManagerFragment = (SubjectManagerFragment) getSupportFragmentManager().findFragmentByTag(SubjectManagerFragment.TAG);

        if(subjectManagerFragment == null){
            Bundle b = null;
            if(subject != null){
                b = new Bundle();
                b.putParcelable(Subject.SUBJECT_KEY,subject);
            }
            subjectManagerFragment = SubjectManagerFragment.newInstance(b);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content,subjectManagerFragment,SubjectManagerFragment.TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        subjectManagerPresenter = new SubjectManagerPresenter(subjectManagerFragment);
        subjectManagerFragment.setPresenter(subjectManagerPresenter);

    }

    @Override
    public void onGoLogin() {
        loginFragment = LoginFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,loginFragment,LoginFragment.TAG);
        fragmentTransaction.commit();

        loginPresenter = new LoginPresenter((LoginContract.View) loginFragment);
        ((LoginContract.View) loginFragment).setPresenter(loginPresenter);
    }

    @Override
    public void onGoRegister() {
        registerFragment = es.jdamiancabello.agendadeestudio.register.RegisterFragment.newInstance(null);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,registerFragment, RegisterFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        registerPresenter = new RegisterPresenter(registerFragment);
        registerFragment.setPresenter(registerPresenter);
    }

    @Override
    public void onGoDashboard() {
        onSuccesLogin();
    }

    @Override
    public void onAddorModifyNote(Note note) {

    }

    @Override
     public void onDoneRegister() {
        onSuccesLogin();
    }

    @Override
    public void onSavedSubject() {
        onBackPressed();
    }
}
