package es.jdamiancabello.agendadeestudio.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import java.util.Comparator;
@Entity
public class Subject implements Parcelable {
    @Ignore
    public static final String SUBJECT_KEY = "subject";

    @NonNull
    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(@NonNull String subject_name) {
        this.subject_name = subject_name;
    }

    @NonNull
    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(@NonNull String exam_date) {
        this.exam_date = exam_date;
    }

    @PrimaryKey
    @NonNull
    private String subject_name;

    @NonNull
    private String exam_date;

    @NonNull
    private int color;

    @Ignore
    protected Subject(Parcel in) {
        subject_name = in.readString();
        color = in.readInt();
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

    public Subject(String subject_name, String exam_date, int color) {
        this.subject_name = subject_name;
        this.exam_date = exam_date;
        this.color = color;
    }

    public String getName() {
        return subject_name;
    }

    public void setName(String name) {
        this.subject_name = name;
    }

    public String getDate() {
        return exam_date;
    }

    public void setDate(String date) {
        this.exam_date = date;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(subject_name);
        dest.writeString(exam_date);
        dest.writeInt(color);
    }

    @NonNull
    @Override
    public String toString() {
        return this.subject_name;
    }

    public static class SortByName implements Comparator<Subject> {
        @Override
        public int compare(Subject o1, Subject o2) {
            return o1.getSubject_name().compareToIgnoreCase(o2.getSubject_name());
        }
    }

    public static class SortByColor implements Comparator<Subject> {
        @Override
        public int compare(Subject o1, Subject o2) {
            return o1.getColor() - o2.getColor();
        }
    }

    public static class SortByExamdate implements Comparator<Subject> {
        @Override
        public int compare(Subject o1, Subject o2) {
            return o1.getExam_date().compareToIgnoreCase(o2.getExam_date());
        }
    }
}