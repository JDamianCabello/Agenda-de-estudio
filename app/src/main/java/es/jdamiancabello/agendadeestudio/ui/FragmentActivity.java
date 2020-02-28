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
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.register.RegisterFragment;
import es.jdamiancabello.agendadeestudio.register.RegisterPresenter;
import es.jdamiancabello.agendadeestudio.ui.aboutme.AboutMeFragment;
import es.jdamiancabello.agendadeestudio.ui.calendar.CalendarFragment;
import es.jdamiancabello.agendadeestudio.ui.dashboard.DashborardFragmentV2;
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
import es.jdamiancabello.agendadeestudio.ui.topic.TopicListContract;
import es.jdamiancabello.agendadeestudio.ui.topic.TopicListFragment;
import es.jdamiancabello.agendadeestudio.ui.topic.TopicListPresenter;
import es.jdamiancabello.agendadeestudio.ui.topic.TopicManagerFragment;
import es.jdamiancabello.agendadeestudio.ui.topic.TopicManagerPresenter;
import es.jdamiancabello.agendadeestudio.ui.welcome.WelcomeContract;
import es.jdamiancabello.agendadeestudio.ui.welcome.WelcomeFragment;
import es.jdamiancabello.agendadeestudio.ui.welcome.WelcomePresenter;

public class FragmentActivity extends AppCompatActivity implements
        SubjectListFragment.onSubjectListListener,
        LoginFragment.onLoginListener,
        StudyOrganicerView.SectorListViewListener,
        StudyOrganicerManageView.OnSaveStudyOrganicerManageView,
        WelcomeFragment.OnWelcomeListener,
        NotesListFragment.OnFragmentInteractionListener,
        RegisterFragment.OnFragmentInteractionListener,
        SubjectManagerFragment.OnFragmentInteractionListener,
        DashborardFragmentV2.OnFragmentInteractionListener,
        CalendarFragment.OnFragmentInteractionListener,
        TopicListFragment.OnFragmentInteractionListener,
        TopicManagerFragment.OnFragmentInteractionListener,
        AboutMeFragment.OnFragmentInteractionListener
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
    private Fragment dashboardFragmentv2;


    private Fragment eventListFragment;
    private Fragment eventManageFragment;

    private StudyOrganicerPresenter studyOrganicerPresenter;
    private StudyOrganicerManagePresenter studyOrganicerManagePresenter;

    private NotesListFragment notesListFragment;
    private NoteListPresenter noteListPresenter;

    private CalendarFragment calendarFragment;

    private TopicListFragment topicListFragment;
    private TopicListPresenter topicListPresenter;

    private TopicManagerFragment topicManagerFragment;
    private TopicManagerPresenter topicManagerPresenter;

    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent().getBooleanExtra("NOTIFICATION", false)){
            addSubject(getIntent().getExtras().getParcelable(Subject.SUBJECT_KEY));
        }
    }

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


    private void loggout() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedUserDataLogin),MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();

        editor.apply();

        ApiRestClientToken.loggout();
        showWelcome();
    }

            @Override
    public void showHelp() {
        aboutMeFragment = AboutMeFragment.newInstance(false);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,aboutMeFragment,AboutMeFragment.TAG);
        fragmentTransaction.addToBackStack("WelcomeToAboutMe");
        fragmentTransaction.commit();
    }

    @Override
    public void onSuccesLogin() {
        dashboardFragmentv2 = DashborardFragmentV2.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,dashboardFragmentv2,DashborardFragmentV2.TAG);
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
            fragmentTransaction.commit();
        }

        studyOrganicerManagePresenter = new StudyOrganicerManagePresenter((StudyOrganicerManageContract.View) eventManageFragment);
        ((StudyOrganicerManageContract.View) eventManageFragment).setPresenter(studyOrganicerManagePresenter);

    }

    @Override
    public void onSaveStudyOrganicerManageView() {
        showSubjects(R.id.dashboard_container);
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

            Bundle b = null;
            if(subject != null){
                b = new Bundle();
                b.putParcelable(Subject.SUBJECT_KEY,subject);
            }
            subjectManagerFragment = SubjectManagerFragment.newInstance(b);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if(getIntent().getBooleanExtra("NOTIFICATION", false))
            fragmentTransaction.replace(R.id.content,subjectManagerFragment,SubjectManagerFragment.TAG);
        else
            fragmentTransaction.replace(R.id.dashboard_container,subjectManagerFragment,SubjectManagerFragment.TAG);

            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();


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
        if(!getIntent().getBooleanExtra("NOTIFICATION", false))
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

    @Override
    public void onCreateNewSubject() {
        addSubject(null);
    }

            @Override
    public void dashboardv2FirstLoad(int containerID) {
        calendarFragment = (CalendarFragment) getSupportFragmentManager().findFragmentByTag(CalendarFragment.TAG);

        if(calendarFragment == null) {
            calendarFragment = CalendarFragment.newInstance();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerID, calendarFragment, CalendarFragment.TAG);

        fragmentTransaction.commit();
    }

            @Override
    public void showCallendar(int containerID) {
        calendarFragment = (CalendarFragment) getSupportFragmentManager().findFragmentByTag(CalendarFragment.TAG);

        if(calendarFragment == null) {
            calendarFragment = CalendarFragment.newInstance();
        }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(containerID, calendarFragment, CalendarFragment.TAG);

                fragmentTransaction.commit();
//TODO: implementar el calendario
    }

    @Override
    public void showOrganicer(int containerID) {
        notesListFragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(NotesListFragment.TAG);

        if(notesListFragment == null){
            notesListFragment = NotesListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(containerID,notesListFragment,NotesListFragment.TAG)
                    .addToBackStack(null).commit();
        }

        noteListPresenter = new NoteListPresenter(notesListFragment);
        notesListFragment.setPresenter(noteListPresenter);
    }

    @Override
    public void showToday(int containerID) {
        dashboardFragment = getSupportFragmentManager().findFragmentByTag(DashboardFragment.TAG);

        if(dashboardFragment == null) {
            dashboardFragment = StudyOrganicerView.newInstance(null);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(containerID, dashboardFragment, DashboardFragment.TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void showTopics(int containerID) {

    }

            @Override
    public void showSubjects(int containerID) {
        subjectList = getSupportFragmentManager().findFragmentByTag(SubjectListFragment.TAG);
        if (subjectList==null) {
            subjectList = SubjectListFragment.newInstance();
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerID, subjectList, SubjectListFragment.TAG);
        fragmentTransaction.commit();

        subjectListPresenter = new SubjectListPresenter((SubjectListContract.View) subjectList);
        ((SubjectListContract.View) subjectList).setPresenter(subjectListPresenter);
    }

    @Override
    public void showTools(int containerID) {
        topicListFragment = (TopicListFragment) getSupportFragmentManager().findFragmentByTag(TopicListFragment.TAG);

        if(topicListFragment == null){
            topicListFragment = TopicListFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.dashboard_container,topicListFragment,TopicListFragment.TAG)
                .commit();

        topicListPresenter = new TopicListPresenter(topicListFragment);
        topicListFragment.setPresenter(topicListPresenter);
    }

    @Override
    public void showHelp(int dashboard_container) {

        aboutMeFragment = getSupportFragmentManager().findFragmentByTag(AboutMeFragment.TAG);

        if(aboutMeFragment == null)
            aboutMeFragment = AboutMeFragment.newInstance(true);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(dashboard_container,aboutMeFragment,AboutMeFragment.TAG);
        fragmentTransaction.commit();
    }

            @Override
    public void calendar_selected_date() {

    }

    @Override
    public void onAddOrModify(Topic topic) {
        topicManagerFragment = (TopicManagerFragment) getSupportFragmentManager().findFragmentByTag(TopicManagerFragment.TAG);

            Bundle bundle = null;
            if(topic != null) {
                bundle = new Bundle();
                bundle.putParcelable(Topic.TOPICTAG, topic);
            }
            topicManagerFragment = TopicManagerFragment.newInstance(bundle);



        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dashboard_container,topicManagerFragment,TopicManagerFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        topicManagerPresenter = new TopicManagerPresenter(topicManagerFragment);
        topicManagerFragment.setPresenter(topicManagerPresenter);
    }

    @Override
    public void onSaved() {
        topicListFragment = (TopicListFragment) getSupportFragmentManager().findFragmentByTag(TopicListFragment.TAG);

        if(topicListFragment == null){
            topicListFragment = TopicListFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.dashboard_container,topicListFragment,TopicListFragment.TAG)
                .commit();

        topicListPresenter = new TopicListPresenter(topicListFragment);
        topicListFragment.setPresenter(topicListPresenter);
    }

    @Override
    public void onLogout() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedUserDataLogin),MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();

        editor.apply();

        ApiRestClientToken.loggout();
        showWelcome();
    }
}


