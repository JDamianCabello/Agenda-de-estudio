package es.jdamiancabello.agendadeestudio.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.Note;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.ui.calendar.CalendarPresenter;
import es.jdamiancabello.agendadeestudio.ui.register.RegisterFragment;
import es.jdamiancabello.agendadeestudio.ui.register.RegisterPresenter;
import es.jdamiancabello.agendadeestudio.ui.aboutme.AboutMeFragment;
import es.jdamiancabello.agendadeestudio.ui.calendar.CalendarFragment;
import es.jdamiancabello.agendadeestudio.ui.login.LoginContract;
import es.jdamiancabello.agendadeestudio.ui.login.LoginFragment;
import es.jdamiancabello.agendadeestudio.ui.login.LoginPresenter;
import es.jdamiancabello.agendadeestudio.ui.notes.NoteListPresenter;
import es.jdamiancabello.agendadeestudio.ui.notes.NotesListFragment;
import es.jdamiancabello.agendadeestudio.ui.subjectinfo.SubjectInfoFragment;
import es.jdamiancabello.agendadeestudio.ui.subjectinfo.SubjectInfoPresenter;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectListContract;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectListFragment;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectListPresenter;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectManagerFragment;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectManagerPresenter;
import es.jdamiancabello.agendadeestudio.ui.topicinfo.TopicInfoFragment;
import es.jdamiancabello.agendadeestudio.ui.utils.stopwatch.StopWatchFragment;
import es.jdamiancabello.agendadeestudio.ui.welcome.WelcomeActivity;
import es.jdamiancabello.agendadeestudio.ui.welcome.WelcomeFragment;

public class DashboardActivity extends AppCompatActivity implements
        SubjectListFragment.onSubjectListListener,
        SubjectInfoFragment.OnFragmentInteractionListener,
        LoginFragment.onLoginListener,
        WelcomeFragment.OnWelcomeListener,
        NotesListFragment.OnFragmentInteractionListener,
        RegisterFragment.OnFragmentInteractionListener,
        SubjectManagerFragment.OnFragmentInteractionListener,
        DashborardFragmentV2.OnFragmentInteractionListener,
        CalendarFragment.OnFragmentInteractionListener,
        AboutMeFragment.OnFragmentInteractionListener,
        TopicInfoFragment.OnfragmentIntercationsListener
        {
    private Toolbar toolbar;


    private Fragment subjectList;
    private SubjectListPresenter subjectListPresenter;
    private SubjectManagerFragment subjectManagerFragment;
    private SubjectManagerPresenter subjectManagerPresenter;


    private Fragment aboutMeFragment;
    private Fragment loginFragment;
    private LoginPresenter loginPresenter;

    private RegisterFragment registerFragment;
    private RegisterPresenter registerPresenter;

    private Fragment dashboardFragmentv2;

    private NotesListFragment notesListFragment;
    private NoteListPresenter noteListPresenter;

    private CalendarFragment calendarFragment;
    private CalendarPresenter calendarPresenter;

    private TopicInfoFragment topicInfoFragment;

    private SubjectInfoFragment subjectInfoFragment;
    private SubjectInfoPresenter subjectInfoPresenter;

    private StopWatchFragment stopWatchFragment;

    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent().getBooleanExtra("NOTIFICATION", false)){
            addOrEdditSubject(getIntent().getExtras().getParcelable(Subject.SUBJECT_KEY));
        }
    }

            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        onSuccesLogin();
    }

    private void showWelcome() {
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }


    //TODO: implementar esto mejor
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
        fragmentTransaction.add(R.id.content,dashboardFragmentv2,DashborardFragmentV2.TAG);
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
    public void addOrEdditSubject(Subject subject) {

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
    public void showSubjectInfo(Subject subject) {
        subjectInfoFragment = (SubjectInfoFragment) getSupportFragmentManager().findFragmentByTag(SubjectInfoFragment.TAG);

        Bundle subjectInfoBundle = new Bundle();
        subjectInfoBundle.putParcelable(Subject.SUBJECT_KEY,subject);

        subjectInfoFragment = SubjectInfoFragment.newInstance(subjectInfoBundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dashboard_container,subjectInfoFragment,SubjectInfoFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        subjectInfoPresenter = new SubjectInfoPresenter(subjectInfoFragment);
        subjectInfoFragment.setPresenter(subjectInfoPresenter);
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
        registerFragment = es.jdamiancabello.agendadeestudio.ui.register.RegisterFragment.newInstance(null);
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
    public void dashboardv2FirstLoad(int containerID) {
        calendarFragment = (CalendarFragment) getSupportFragmentManager().findFragmentByTag(CalendarFragment.TAG);

        if(calendarFragment == null) {
            calendarFragment = CalendarFragment.newInstance();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerID, calendarFragment, CalendarFragment.TAG);

        fragmentTransaction.commit();

        calendarPresenter = new CalendarPresenter(calendarFragment);
        calendarFragment.setPresenter(calendarPresenter);
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

        calendarPresenter = new CalendarPresenter(calendarFragment);
        calendarFragment.setPresenter(calendarPresenter);
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
        stopWatchFragment = StopWatchFragment.newInstance();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.dashboard_container,stopWatchFragment,StopWatchFragment.TAG)
                .commit();
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
    public void onTopicInfo(Topic topic) {
        topicInfoFragment = (TopicInfoFragment) getSupportFragmentManager().findFragmentByTag(TopicInfoFragment.TAG);

            Bundle bundle = null;
            if(topic != null) {
                bundle = new Bundle();
                bundle.putParcelable(Topic.TOPICTAG, topic);
            }
            topicInfoFragment = TopicInfoFragment.newInstance(bundle);



        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dashboard_container,topicInfoFragment,TopicInfoFragment.TAG);
        fragmentTransaction.addToBackStack(SubjectInfoFragment.TAG);
        fragmentTransaction.commit();
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

    @Override
    public void onSubjectInfoBack() {
        onBackPressed();
    }

            @Override
            public void onBack() {
                onBackPressed();
            }
        }


