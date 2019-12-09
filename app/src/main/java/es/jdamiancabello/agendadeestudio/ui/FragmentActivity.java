package es.jdamiancabello.agendadeestudio.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Note;
import es.jdamiancabello.agendadeestudio.data.model.StudyOrganicer;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
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
import es.jdamiancabello.agendadeestudio.ui.welcome.WelcomeFragment;

public class FragmentActivity extends AppCompatActivity implements
        SubjectListFragment.onSubjectListListener,
        DashboardFragment.onDashboardListener,
        LoginFragment.onLoginListener,
        StudyOrganicerView.SectorListViewListener,
        StudyOrganicerManageView.OnSaveStudyOrganicerManageView,
        WelcomeFragment.OnWelcomeListener,
        NotesListFragment.OnFragmentInteractionListener{

    private Fragment welcomeFragment;

    private Fragment subjectList;
    private SubjectListPresenter subjectListPresenter;


    private Fragment aboutMeFragment;
    private Fragment loginFragment;

    private Fragment registerFragment;
    private LoginPresenter loginPresenter;

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
        welcomeFragment = WelcomeFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(android.R.id.content,welcomeFragment,WelcomeFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void showSubjectsList() {
        subjectList = getSupportFragmentManager().findFragmentByTag(SubjectListFragment.TAG);
        if (subjectList==null) {
            subjectList = SubjectListFragment.newInstance();


            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(android.R.id.content, subjectList, SubjectListFragment.TAG);
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
            fragmentTransaction.replace(android.R.id.content, eventListFragment, StudyOrganicerView.TAG);
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
        Toast.makeText(this,"SettingsView is not implemented yet",Toast.LENGTH_SHORT).show();
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
                    .replace(android.R.id.content,notesListFragment,NotesListFragment.TAG)
                    .addToBackStack(null).commit();
        }

        noteListPresenter = new NoteListPresenter(notesListFragment);
        notesListFragment.setPresenter(noteListPresenter);

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
    public void onSuccesLogin() {
        dashboardFragment = DashboardFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,dashboardFragment,DashboardFragment.TAG);
        fragmentTransaction.commit();
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

    @Override
    public void addSubject(Subject subject) {

    }

    @Override
    public void onGoLogin() {
        loginFragment = LoginFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,loginFragment,LoginFragment.TAG);
        fragmentTransaction.addToBackStack("WelcomeToLogin");
        fragmentTransaction.commit();

        loginPresenter = new LoginPresenter((LoginContract.View) loginFragment);
        ((LoginContract.View) loginFragment).setPresenter(loginPresenter);
    }

    @Override
    public void onGoRegister() {
        registerFragment = RegisterFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,registerFragment,RegisterFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onAddorModifyNote(Note note) {

    }
}
