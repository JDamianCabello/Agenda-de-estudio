package es.jdamiancabello.agendadeestudio.data.repository;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.DAO.SubjectDAO;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectListPresenter;

public class SubjectRepository implements SubjectDAO.ResponseSubject, SubjectDAO.ResponseSubjectAddOrModify, SubjectDAO.ResponseDeleteSubject{
    public RepositorySubject repositorySubject;
    private static SubjectRepository subjectRepository;
    private static ManageSubject manageSubject;
    private static DeleteSubject deleteSubject;

    static {
        subjectRepository = new SubjectRepository();
    }

    public static SubjectRepository getInstance(){
        return  subjectRepository;
    }


    public void getSubjectList(RepositorySubject repositorySubject) {
        this.repositorySubject = repositorySubject;
        SubjectDAO.getSubjectList(this);
    }

    public void addSubject(ManageSubject manageSubject, Subject newSubject, boolean makeEvent) {
        this.manageSubject = manageSubject;
        SubjectDAO.addNewSubject(this,newSubject, makeEvent);
    }

    public void deleteSubject(SubjectRepository.DeleteSubject deleteSubject, Subject subject) {
        this.deleteSubject = deleteSubject;
        SubjectDAO.deleteSubject(this,subject);
    }

    public void modifySubject(ManageSubject manageSubject, Subject subject, boolean makeUpdateEvent) {
        this.manageSubject = manageSubject;
        SubjectDAO.modifySubject(this,subject, makeUpdateEvent);
    }


    @Override
    public void onSucess(List<Subject> subjectList) {
        repositorySubject.onLoaded(subjectList);
    }

    @Override
    public void onSave() {
        manageSubject.onSaved();
    }

    @Override
    public void onDeleted() {
        deleteSubject.onDeleted();
    }
    public interface RepositorySubject{
        void onLoaded(List<Subject> subjectList);
    }

    public interface ManageSubject{
        void onSaved();
    }

    public interface DeleteSubject{
        void onDeleted();
    }

    //TODO: con esto se separa el listener del calendario del de la lista de asignaturas (vale de poco pero es separar código)
    public interface CalendarSubjectEvents{
        void onLoaded(List<Subject> subjectList);
    }
}
