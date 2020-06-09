package es.jdamiancabello.agendadeestudio.ui.calendar;


import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;

public class CalendarPresenter implements CalendarContract.Presenter, SubjectRepository.RepositorySubject{
    private CalendarContract.View view;

    public CalendarPresenter(CalendarContract.View view) {
        this.view = view;
    }

    @Override
    public void load() {
        SubjectRepository.getInstance().getSubjectList(this);
    }

    @Override
    public void onLoaded(List<Subject> subjectList) {
        view.putEvents(subjectList);
    }
}
