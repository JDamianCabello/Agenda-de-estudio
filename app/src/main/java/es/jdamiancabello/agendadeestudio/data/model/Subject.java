package es.jdamiancabello.agendadeestudio.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class Subject implements Parcelable {
    public static final String SUBJECT_KEY = "subject";

    private String subject_name;
    private int id;
    private int estate_priority;

    protected Subject(Parcel in) {
        subject_name = in.readString();
        id = in.readInt();
    }

    public static final Creator<Subject> CREATOR = new Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel in) {
            return new Subject(in);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(subject_name);
        parcel.writeInt(id);
    }

    public enum  state{
        Dominado,A_repasar,Resumido,Ignorado
    }


    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getId() {
        return id;
    }

    public int getEstate_priority() {
        return estate_priority;
    }

    public void setEstate_priority(int estate_priority) {
        this.estate_priority = estate_priority;
    }

    public Subject(String subject_name, int estate_priority) {
        this.subject_name = subject_name;
        this.id = 1;
        this.estate_priority = estate_priority;
    }

    @NonNull
    @Override
    public String toString() {
        return subject_name;
    }

    public static class SortByName implements Comparator<Subject> {

        @Override
        public int compare(Subject o1, Subject o2) {
            return o1.getSubject_name().compareToIgnoreCase(o2.getSubject_name());
        }
    }
}
