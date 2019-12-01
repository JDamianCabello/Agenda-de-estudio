package es.jdamiancabello.agendadeestudio.data.repository;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.StudyOrganicer;
import es.jdamiancabello.agendadeestudio.data.model.Subject;

public class StudyOrganicerRepository {
    private List<StudyOrganicer> studyOrganicerList;
    private static StudyOrganicerRepository studyOrganicerRepository;

    private StudyOrganicerRepository() {
        this.studyOrganicerList = new ArrayList<>();
        initialice();
    }

    private void initialice() {

        studyOrganicerList.add(new StudyOrganicer("01 - Diciembre - 2019   15:00",5,"Horas",(new Subject("DEINT",Subject.state.A_repasar))));
        studyOrganicerList.add(new StudyOrganicer("02 - Diciembre - 2019   08:00",50,"Minutos",(new Subject("Unity",Subject.state.A_repasar))));
    }

    static {
        studyOrganicerRepository = new StudyOrganicerRepository();
    }

    public static StudyOrganicerRepository getInstance(){
        return studyOrganicerRepository;
    }

    public List<StudyOrganicer> getSectorList(){
        return studyOrganicerList;
    }

    public boolean deleteSector(StudyOrganicer studyOrganicer){
        return studyOrganicerList.remove(studyOrganicer);
    }

    public boolean addDependency(StudyOrganicer sector) {
        return studyOrganicerList.add(sector);
    }

    public boolean modifyStudyOrganicer(StudyOrganicer newStudyOrganicer) {
        for (StudyOrganicer it : studyOrganicerList) {
            if (it.getDateTime().equals(newStudyOrganicer.getDateTime()) & it.getSubject().equals(newStudyOrganicer.getSubject())) {
                it.setDateTime(newStudyOrganicer.getDateTime());
                it.setDuration(newStudyOrganicer.getDuration());
                it.setSubject(newStudyOrganicer.getSubject());
                it.setEventTitle(newStudyOrganicer.getSubject().getStateEnum().toString());
                it.setDurationQuantifier(newStudyOrganicer.getDurationQuantifier());
                return true;
            }
        }
        return false;
    }
}