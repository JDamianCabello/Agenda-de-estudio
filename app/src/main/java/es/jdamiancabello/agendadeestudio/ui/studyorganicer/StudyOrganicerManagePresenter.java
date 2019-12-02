package es.jdamiancabello.agendadeestudio.ui.studyorganicer;

import java.util.ArrayList;

import es.jdamiancabello.agendadeestudio.data.model.StudyOrganicer;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.repository.StudyOrganicerRepository;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;

public class StudyOrganicerManagePresenter implements StudyOrganicerManageContract.Presenter {
    StudyOrganicerManageContract.View view;

    public StudyOrganicerManagePresenter(StudyOrganicerManageContract.View view){
        this.view = view;
    }


    @Override
    public void onViewCreated() {
        view.setupContentList((ArrayList<Subject>) SubjectRepository.getInstance().getSubjectList());
    }



    private boolean validateStudyOrganicer(StudyOrganicer studyOrganicer) {
        if(validateDateTimeNotEmpty(studyOrganicer.getDateTime()) & validateDurationNotEmpty(Integer.toString(studyOrganicer.getDuration()))){
            return true;
        }
        return false;
    }

    public boolean validateDateTimeNotEmpty(String s){
        if(s.isEmpty()) {
            view.onTimeEmpty("Debe seleccionar un dia y hora (no debe ser nulo)");
            return false;
        }
        view.onClearErroronTimeEmpty();
        return true;
    }


    public boolean validateDurationNotEmpty(String s){
        if(s.isEmpty() || s.equals("0")) {
            view.onDurationEmpty("La duración no puede ser nula");
            try {
                int aux = Integer.parseInt(s);
                view.onClearErrorDurationNumber();
            } catch (NumberFormatException nfe) {
                view.onDurationNumber("Solo se admiten números en la duración");
            }
            return false;
        }
        view.onClearErrorDurationEmpty();
        return true;
    }


    @Override
    public void onAddStudyOrganicer(StudyOrganicer studyOrganicer) {
        if(validateStudyOrganicer(studyOrganicer) ){
            if(StudyOrganicerRepository.getInstance().addDependency(studyOrganicer))
                view.onSucess();
        }

    }

    @Override
    public void onModifyStudyOrganicer(StudyOrganicer studyOrganicer) {
        if(validateStudyOrganicer(studyOrganicer)) {
            if (StudyOrganicerRepository.getInstance().modifyStudyOrganicer(studyOrganicer)) {
                view.onSucess();
            }
            else
                view.showGenericError("No se ha podido modificar");
        }
    }

    @Override
    public int getPosition(Subject subject) {
        return SubjectRepository.getInstance().getPosition(subject);
    }

    @Override
    public void numberFormatException() {
        view.onDurationEmpty("La duración no puede ser nula");
    }

    @Override
    public void nullDateTime() {
        view.onTimeEmpty("Debe seleccionar un dia y hora (no debe ser nulo)");
    }

}
