package es.jdamiancabello.agendadeestudio.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class StudyOrganicer implements Parcelable {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int idStudyOrganicer;
    private String dateTime;
    private int duration;
    private String durationQuantifier; //Esto muestra el tipo de duración : ejemplo in duration 4 y aqui iria horas, minutos o dias.
    private Subject subject;
    private int eventTitle;

    public StudyOrganicer() {

    }

    public StudyOrganicer(String dateTime, int duration, String durationQuantifier, Subject subject) {
        this.idStudyOrganicer = count.incrementAndGet();
        this.dateTime = dateTime;
        this.duration = duration;
        this.durationQuantifier = durationQuantifier;
        this.subject = subject;
        this.eventTitle = subject.getEstate_priority();
    }

    protected StudyOrganicer(Parcel in) {
        idStudyOrganicer = in.readInt();
        dateTime = in.readString();
        duration = in.readInt();
        durationQuantifier = in.readString();
        subject = in.readParcelable(Subject.class.getClassLoader());
        eventTitle = in.readInt();
    }

    public static final Creator<StudyOrganicer> CREATOR = new Creator<StudyOrganicer>() {
        @Override
        public StudyOrganicer createFromParcel(Parcel in) {
            return new StudyOrganicer(in);
        }

        @Override
        public StudyOrganicer[] newArray(int size) {
            return new StudyOrganicer[size];
        }
    };



    public int getIdStudyOrganicer() {
        return idStudyOrganicer;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDurationQuantifier() {
        return durationQuantifier;
    }

    public void setDurationQuantifier(String durationQuantifier) {
        this.durationQuantifier = durationQuantifier;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(int eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setIdStudyOrganicer(int idStudyOrganicer) {
        this.idStudyOrganicer = idStudyOrganicer;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this.getIdStudyOrganicer() == ((StudyOrganicer)obj).getIdStudyOrganicer())
            return true;
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idStudyOrganicer);
        dest.writeString(dateTime);
        dest.writeInt(duration);
        dest.writeString(durationQuantifier);
        dest.writeParcelable(subject, flags);
        dest.writeInt(eventTitle);
    }

    public static class IdSort implements Comparator<StudyOrganicer> {
        @Override
        public int compare(StudyOrganicer studyOrganicerA, StudyOrganicer studyOrganicerB) {
            int result = studyOrganicerA.getIdStudyOrganicer() > studyOrganicerB.getIdStudyOrganicer() ? 1 : 0;
            if (result == 0) {
                result = studyOrganicerA.getIdStudyOrganicer() - studyOrganicerB.getIdStudyOrganicer();
            }
            return result;
        }
    }

    public static class stateSort implements Comparator<StudyOrganicer> {

        @Override
        public int compare(StudyOrganicer o1, StudyOrganicer o2) {
            return 0;
        }
    }

}
