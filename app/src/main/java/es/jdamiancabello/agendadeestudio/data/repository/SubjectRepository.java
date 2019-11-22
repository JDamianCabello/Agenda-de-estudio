package es.jdamiancabello.agendadeestudio.data.repository;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;

public class SubjectRepository {
    private List<Subject> subjectList;
    private static SubjectRepository subjectRepository;

    static {
        subjectRepository = new SubjectRepository();
    }

    public static SubjectRepository getInstance(){
        return  subjectRepository;
    }

    private SubjectRepository() {
        subjectList = new ArrayList<>();
        Initialize();
    }

    private void Initialize() {
        subjectList.add(new Subject("DEINT",Subject.state.A_repasar));
        subjectList.add(new Subject("Unity",Subject.state.A_repasar));
        subjectList.add(new Subject("C#",Subject.state.Dominado));
        subjectList.add(new Subject("Mysql",Subject.state.Dominado));
        subjectList.add(new Subject("Sistemas",Subject.state.Ignorado));
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }
}
