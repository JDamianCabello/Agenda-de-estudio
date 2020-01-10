package es.jdamiancabello.agendadeestudio.data.repository;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.DAO.SubjectDAO;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.ui.subjets.SubjectListPresenter;

public class SubjectRepository implements SubjectDAO.ResponseSubject, SubjectDAO.ResponseSubjectAddOrModify{
    public RepositorySubject repositorySubject;
    private List<Subject> subjectList;
    private static SubjectRepository subjectRepository;
    private static ManageSubject manageSubject;

    static {
        subjectRepository = new SubjectRepository();
    }

    public static SubjectRepository getInstance(){
        return  subjectRepository;
    }

    private SubjectRepository() {
        subjectList = new ArrayList<>();
        //Initialize();
    }

    private void Initialize() {
        subjectList.add(new Subject("DEINT",0));
        subjectList.add(new Subject("Unity",1));
        subjectList.add(new Subject("C#",3));
        subjectList.add(new Subject("Mysql",0));
        subjectList.add(new Subject("Sistemas",0));
        subjectList.add(new Subject("Procesos",2));
        subjectList.add(new Subject("Fol",2));
        subjectList.add(new Subject("Java",3));
        subjectList.add(new Subject("HTML",3));
        subjectList.add(new Subject("CSS",0));
    }

//    public List<Subject> getSubjects() {
//        return subjectList;
//    }

    public void getSubjectList(SubjectRepository.RepositorySubject repositorySubject) {
        this.repositorySubject = repositorySubject;
        SubjectDAO.getSubjectList(this);
    }

    public boolean remove(Subject subject){
        return subjectList.remove(subject);
    }

    public int getPosition(Subject subject) {

        for (int j = 0; j < subjectList.size(); j++) {
            if (subjectList.get(j).getSubject_name().equals(subject.getSubject_name()))
                return j;
        }
        return -1;
    }

    public void addSubject(SubjectRepository.ManageSubject manageSubject, String name, int state) {
        this.manageSubject = manageSubject;
        SubjectDAO.addNewSubject(this,name,state);
    }

    public boolean delete(Subject subject) {
        return subjectList.remove(subject);
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

    public interface RepositorySubject{
        void onLoaded();
    }

    public interface ManageSubject{
        void onSaved();
    }
}
