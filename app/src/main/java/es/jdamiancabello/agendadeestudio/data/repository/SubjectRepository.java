package es.jdamiancabello.agendadeestudio.data.repository;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.DAO.SubjectDAO;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectListPresenter;

public class SubjectRepository implements SubjectDAO.ResponseSubject, SubjectDAO.ResponseSubjectAddOrModify, SubjectDAO.ResponseDeleteSubject, SubjectDAO.ResponseSubjectRestore{
    public RepositorySubject repositorySubject;
    private List<Subject> subjectList;
    private static SubjectRepository subjectRepository;
    private static ManageSubject manageSubject;
    private static DeleteSubject deleteSubject;
    private static RestoreSubject restoreSubject;

    static {
        subjectRepository = new SubjectRepository();
    }

    public static SubjectRepository getInstance(){
        return  subjectRepository;
    }

    private SubjectRepository() {
        subjectList = new ArrayList<>();
    }


    public void getSubjectList(RepositorySubject repositorySubject) {
        this.repositorySubject = repositorySubject;
        SubjectDAO.getSubjectList(this);
    }

    public void addSubject(SubjectRepository.ManageSubject manageSubject, Subject newSubject) {
        this.manageSubject = manageSubject;
        SubjectDAO.addNewSubject(this,newSubject);
    }

    public void deleteSubject(SubjectRepository.DeleteSubject deleteSubject, Subject subject) {
        this.deleteSubject = deleteSubject;
        SubjectDAO.deleteSubject(this,subject);
    }

    public void modifySubject(SubjectRepository.ManageSubject manageSubject, Subject subject) {
        this.manageSubject = manageSubject;
        SubjectDAO.modifySubject(this,subject);
    }

    public void restoreSubject(SubjectRepository.RestoreSubject restoreSubject, Subject subject) {
        this.restoreSubject = restoreSubject;
        SubjectDAO.restoreSubject(this,subject);
    }

    public List<Subject> getList(){
        return this.subjectList;
    }

    @Override
    public void onSucess(List<Subject> subjectList) {
        this.subjectList.clear();
        this.subjectList.addAll(subjectList);
        repositorySubject.onLoaded();
    }

    @Override
    public void onSave() {
        manageSubject.onSaved();
    }

    @Override
    public void onDeleted(Subject deletedSubject, List<Topic> deletedTopicsList) {
        deleteSubject.onDeleted(deletedSubject,deletedTopicsList);
    }

    @Override
    public void onRestored(Subject subject) {
        restoreSubject.onRestored(subject);
    }

    public interface RepositorySubject{
        void onLoaded();
    }

    public interface ManageSubject{
        void onSaved();
    }

    public interface DeleteSubject{
        void onDeleted(Subject deletedSubject, List<Topic> deletedTopicsList);
    }

    public interface RestoreSubject{
        void onRestored(Subject subject);
    }

    //TODO: con esto se separa el listener del calendario del de la lista de asignaturas (vale de poco pero es separar código)
    public interface CalendarSubjectEvents{
        void onLoaded();
    }
}
