package es.jdamiancabello.agendadeestudio.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;
import java.util.Objects;

import es.jdamiancabello.agendadeestudio.utils.CommonUtils;

public class Subject implements Parcelable{
    public static final String SUBJECT_KEY = "subject";

    private int id;
    private String subject_name;
    private String exam_date;
    private int color;
    private int iconId;
    private int percent;
    private boolean haveEvent;

    public Subject(){}

    public Subject(String subject_name, String exam_date, int color, int iconId, boolean haveEvent) {
        this.subject_name = subject_name;
        this.exam_date = exam_date;
        this.color = color;
        this.iconId = iconId;
        this.haveEvent = haveEvent;
    }

    protected Subject(Parcel in) {
        id = in.readInt();
        subject_name = in.readString();
        exam_date = in.readString();
        color = in.readInt();
        iconId = in.readInt();
        percent = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(subject_name);
        dest.writeString(exam_date);
        dest.writeInt(color);
        dest.writeInt(iconId);
        dest.writeInt(percent);
        dest.writeByte((byte) (haveEvent ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public boolean isHaveEvent() {
        return haveEvent;
    }

    public void setHaveEvent(boolean haveEvent) {
        this.haveEvent = haveEvent;
    }

    public static class SortByName implements Comparator<Subject> {
        @Override
        public int compare(Subject o1, Subject o2) {
            return o1.getSubject_name().compareToIgnoreCase(o2.getSubject_name());
        }
    }

    public static class SortByPercent implements Comparator<Subject> {
        @Override
        public int compare(Subject o1, Subject o2) {
            return  Integer.compare(o1.getPercent(),o2.getPercent());
        }
    }

    public static class SortByExamdate implements Comparator<Subject> {
        @Override
        public int compare(Subject o1, Subject o2) {
            return (int) (CommonUtils.dateStringToLong(o1.getExam_date()) - CommonUtils.dateStringToLong(o2.getExam_date()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}