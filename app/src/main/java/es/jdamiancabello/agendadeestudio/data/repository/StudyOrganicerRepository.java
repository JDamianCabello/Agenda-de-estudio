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
//        initialice();
    }

    private void initialice() {
//
//        studyOrganicerList.add(new StudyOrganicer("01 - Diciembre - 2019   15:00", 5, "Horas", (new Subject("DEINT", 0))));
//        studyOrganicerList.add(new StudyOrganicer("02 - Diciembre - 2019   08:00", 50, "Minutos", (new Subject("Unity", 0))));
    }

    static {
        studyOrganicerRepository = new StudyOrganicerRepository();
    }

    public static StudyOrganicerRepository getInstance() {
        return studyOrganicerRepository;
    }

    public List<StudyOrganicer> getStudyOrganicerList() {
        return studyOrganicerList;
    }

    public boolean deleteStudyOrganicer(StudyOrganicer studyOrganicer) {
        return studyOrganicerList.remove(studyOrganicer);
    }

    public boolean addDependency(StudyOrganicer studyOrganicer) {
        return studyOrganicerList.add(studyOrganicer);
    }

    public boolean modifyStudyOrganicer(StudyOrganicer newStudyOrganicer) {
        for (StudyOrganicer it : studyOrganicerList) {
            if (it.equals(newStudyOrganicer)) {
                it.setDateTime(newStudyOrganicer.getDateTime());
                it.setDuration(newStudyOrganicer.getDuration());
                it.setSubject(newStudyOrganicer.getSubject());
                //it.setEventTitle(newStudyOrganicer.getSubject().getEstate_priority());
                it.setDurationQuantifier(newStudyOrganicer.getDurationQuantifier());
                return true;
            }
        }
        return false;
    }
}
