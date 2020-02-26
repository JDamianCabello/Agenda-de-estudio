package es.jdamiancabello.agendadeestudio.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Comparator;


@Entity(primaryKeys = {"subject_name","name"},foreignKeys = @ForeignKey(entity = Subject.class,
        parentColumns = "subject_name",
        childColumns = "subject_name",
        onDelete = ForeignKey.CASCADE))
public class Topic implements Parcelable {
    @Ignore
    public static final String TOPICTAG = "topic";
    @NonNull
    private String subject_name;
    @NonNull
    private String name;
    @NonNull
    private int state;

    public Topic(String subject_name, String name, int state) {
        this.subject_name = subject_name;
        this.name = name;
        this.state = state;
    }

    @Ignore
    protected Topic(Parcel in) {
        subject_name = in.readString();
        name = in.readString();
        state = in.readInt();
    }

    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
        @Override
        public Topic createFromParcel(Parcel in) {
            return new Topic(in);
        }

        @Override
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(subject_name);
        dest.writeString(name);
        dest.writeInt(state);
    }

    public static class SortByName implements Comparator<Topic>{
        @Override
        public int compare(Topic o1, Topic o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }

    public static class SortBySubjectName implements Comparator<Topic>{
        @Override
        public int compare(Topic o1, Topic o2) {
            return o1.getSubject_name().compareToIgnoreCase(o2.getSubject_name());
        }
    }

    public static class SortByState implements Comparator<Topic>{
        @Override
        public int compare(Topic o1, Topic o2) {
            return o1.getState() - o2.getState();
        }
    }
}
