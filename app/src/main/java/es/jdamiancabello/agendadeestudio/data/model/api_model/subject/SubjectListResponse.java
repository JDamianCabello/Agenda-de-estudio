package es.jdamiancabello.agendadeestudio.data.model.api_model.subject;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;

public class SubjectListResponse {
    private boolean error;
    private int count;
    private List<Subject> subjects;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
