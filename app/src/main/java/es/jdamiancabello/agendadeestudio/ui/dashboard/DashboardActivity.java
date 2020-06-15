package es.jdamiancabello.agendadeestudio.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.naz013.smoothbottombar.SmoothBottomBar;
import com.github.naz013.smoothbottombar.Tab;

import java.util.ArrayList;
import java.util.List;

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
import es.jdamiancabello.agendadeestudio.ui.notes.NotesListFragment;
import es.jdamiancabello.agendadeestudio.ui.subjectinfo.SubjectInfoFragment;
import es.jdamiancabello.agendadeestudio.ui.subjectinfo.SubjectInfoPresenter;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectListContract;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectListFragment;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectListPresenter;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectManagerFragment;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectManagerPresenter;
import es.jdamiancabello.agendadeestudio.ui.topicinfo.TopicInfoFragment;
import es.jdamiancabello.agendadeestudio.ui.topicinfo.TopicInfoPresenter;
import es.jdamiancabello.agendadeestudio.ui.utils.ToolsSelector;
import es.jdamiancabello.agendadeestudio.ui.utils.countdown.CountDownFragment;
import es.jdamiancabello.agendadeestudio.ui.utils.timer.StopWatchFragment;
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
        AboutMeFragment.OnFragmentInteractionListener,
        TopicInfoFragment.OnfragmentIntercationsListener,
        ToolsSelector.OnFragmentInteractionListener
        {
    private Toolbar toolbar;
    private SmoothBottomBar menu;
    private LinearLayout snackBarViewToShow;


    private Fragment subjectList;
    private SubjectListPresenter subjectListPresenter;
    private SubjectManagerFragment subjectManagerFragment;
    private SubjectManagerPresenter subjectManagerPresenter;


    private Fragment aboutMeFragment;
    private Fragment loginFragment;
    private LoginPresenter loginPresenter;

    private RegisterFragment registerFragment;
    private RegisterPresenter registerPresenter;

    private CalendarFragment calendarFragment;
    private CalendarPresenter calendarPresenter;

    private TopicInfoFragment topicInfoFragment;
    private TopicInfoPresenter topicInfoPresenter;

    private SubjectInfoFragment subjectInfoFragment;
    private SubjectInfoPresenter subjectInfoPresenter;

    private ToolsSelector toolsSelector;
    private StopWatchFragment stopWatchFragment;
    private CountDownFragment countDowntFragment;

            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashborard_fragment_v2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        snackBarViewToShow = findViewById(R.id.mainSnackView);
        menu = findViewById(R.id.dashboard_menu);
        menu.setTabs(createBottonMenu());

        menu.setOnTabSelectedListener(new SmoothBottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int i) {
                changeFragment(i);
            }
        });

        onSuccesLogin();
    }

            public LinearLayout getSnackBarViewToShow() {
                return snackBarViewToShow;
            }

            private List<Tab> createBottonMenu() {
        return new ArrayList<Tab>() {{
            add(new Tab(R.drawable.ic_baseline_calendar_today, getString(R.string.dashboard_callendar)));
            add(new Tab(R.drawable.ic_baseline_library_books_24, getString(R.string.dashboard_subjects)));
            add(new Tab(R.drawable.ic_baseline_work_24, getString(R.string.dashboard_tools)));
            add(new Tab(R.drawable.ic_help, getString(R.string.help)));
        }};
    }

    private void changeFragment(int i) {
        switch (i){
            case 0:
                showCallendar();
                break;
            case 1:
                showSubjects();
                break;
            case 2:
                showTools();
                break;
            case 3:
                showHelpLogin();
                break;
        }
    }


            private void showWelcome() {
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
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
        calendarFragment = (CalendarFragment) getSupportFragmentManager().findFragmentByTag(CalendarFragment.TAG);

        if(calendarFragment == null) {
            calendarFragment = CalendarFragment.newInstance();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.dashboardcontent, calendarFragment, CalendarFragment.TAG);

        fragmentTransaction.commit();

        calendarPresenter = new CalendarPresenter(calendarFragment);
        calendarFragment.setPresenter(calendarPresenter);
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
            fragmentTransaction.replace(R.id.dashboardcontent,subjectManagerFragment,SubjectManagerFragment.TAG);

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
        fragmentTransaction.replace(R.id.dashboardcontent,subjectInfoFragment,SubjectInfoFragment.TAG);
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

    public void showCallendar() {
        calendarFragment = (CalendarFragment) getSupportFragmentManager().findFragmentByTag(CalendarFragment.TAG);

        if(calendarFragment == null) {
            calendarFragment = CalendarFragment.newInstance();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dashboardcontent, calendarFragment, CalendarFragment.TAG);

        fragmentTransaction.commit();

        calendarPresenter = new CalendarPresenter(calendarFragment);
        calendarFragment.setPresenter(calendarPresenter);
    }



    public void showSubjects() {
        subjectList = getSupportFragmentManager().findFragmentByTag(SubjectListFragment.TAG);
        if (subjectList==null) {
            subjectList = SubjectListFragment.newInstance();
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dashboardcontent, subjectList, SubjectListFragment.TAG);
        fragmentTransaction.commit();

        subjectListPresenter = new SubjectListPresenter((SubjectListContract.View) subjectList);
        ((SubjectListContract.View) subjectList).setPresenter(subjectListPresenter);
    }

    public void showTools() {
        toolsSelector = ToolsSelector.newInstance();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.dashboardcontent,toolsSelector,ToolsSelector.TAG)
                .commit();
    }


    public void showHelpLogin() {

        aboutMeFragment = getSupportFragmentManager().findFragmentByTag(AboutMeFragment.TAG);

        if(aboutMeFragment == null)
            aboutMeFragment = AboutMeFragment.newInstance(true);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dashboardcontent,aboutMeFragment,AboutMeFragment.TAG);
        fragmentTransaction.commit();
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
        fragmentTransaction.replace(R.id.dashboardcontent,topicInfoFragment,TopicInfoFragment.TAG);
        fragmentTransaction.addToBackStack(SubjectInfoFragment.TAG);
        fragmentTransaction.commit();

        topicInfoPresenter = new TopicInfoPresenter(topicInfoFragment);
        topicInfoFragment.setPresenter(topicInfoPresenter);
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

    @Override
    public void onGoTimerTool() {
        stopWatchFragment = StopWatchFragment.newInstance();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.dashboardcontent,stopWatchFragment,StopWatchFragment.TAG)
                .addToBackStack(ToolsSelector.TAG)
                .commit();
    }

    @Override
    public void onGoToDoListTool() {
                notImplementedToast();
    }

    @Override
    public void onGoCountDownTool() {
        countDowntFragment = CountDownFragment.newInstance();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.dashboardcontent,countDowntFragment,CountDownFragment.TAG)
                .addToBackStack(ToolsSelector.TAG)
                .commit();
    }

    @Override
    public void onGoNotesTool() {
        notImplementedToast();
    }

    @Override
    public void onGoFlashCardsTool() {
        notImplementedToast();
    }

    @Override
    public void onGoScheduleTool() {
        notImplementedToast();
    }

            private void notImplementedToast() {
                Toast toast = Toast.makeText(this, R.string.notImplemented, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM,0,300);
                toast.show();
            }
        }



