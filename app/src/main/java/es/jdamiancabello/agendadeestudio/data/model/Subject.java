package es.jdamiancabello.agendadeestudio.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Subject implements Parcelable {
    public static final String SUBJECT_KEY = "subject";
    private String subject_name;
    private Calendar exam_date;
    private int color;
    private List<Topic> topicList;

    protected Subject(Parcel in) {
        subject_name = in.readString();
        color = in.readInt();
        topicList = in.createTypedArrayList(Topic.CREATOR);
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

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setSubjectList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public Subject(String subject_name, Calendar exam_date, int color) {
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

    public Calendar getDate() {
        return exam_date;
    }

    public void setDate(Calendar date) {
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
        dest.writeInt(color);
        dest.writeTypedList(topicList);
    }

    @NonNull
    @Override
    public String toString() {
        return this.subject_name;
    }

    public static class SortByName implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            return o1.toString().compareToIgnoreCase(o2.toString());
        }
    }
}
