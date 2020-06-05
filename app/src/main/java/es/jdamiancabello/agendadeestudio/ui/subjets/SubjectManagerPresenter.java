package es.jdamiancabello.agendadeestudio.ui.subjets;


import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;

public class SubjectManagerPresenter implements SubjectManagerContract.Presenter, SubjectRepository.ManageSubject{
    private SubjectManagerContract.View view;

    public SubjectManagerPresenter(SubjectManagerContract.View view) {
        this.view = view;
    }

    @Override
    public void onSaved() {
        view.onSucess();
    }

    //TODO: implementar el add y modify hacia la api
    @Override
    public void addSubject(Subject newSubject) {
        if(!notNullString(newSubject.getSubject_name()) && !notNullString(newSubject.getExam_date())) {
            SubjectRepository.getInstance().addSubject(this,newSubject);
        }
        else{
            view.showGenericError("Uno de los campos está vacío.");
        }
    }

    @Override
    public void modifySubject(Subject updatedSubject) {
        if(!notNullString(updatedSubject.getSubject_name()) && !notNullString(updatedSubject.getExam_date())) {
            SubjectRepository.getInstance().modifySubject(this,updatedSubject);
        }
        else{
            view.showGenericError("Uno de los campos está vacío.");
        }
    }

    private boolean notNullString(String s){
        return s.trim().isEmpty();
    }
}
