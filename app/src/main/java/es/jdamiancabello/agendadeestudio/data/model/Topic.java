package es.jdamiancabello.agendadeestudio.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Comparator;



public class Topic implements Parcelable {
    public static final String TOPICTAG = "topic";
    private int id;
    private String name;
    private boolean isTask;
    private int state;
    private int priority;
    private String notes;

    public Topic(String name, boolean isTask, int state, int priority, String notes) {
        this.name = name;
        this.isTask = isTask;
        this.state = state;
        this.priority = priority;
        this.notes = notes;
    }

    protected Topic(Parcel in) {
        id = in.readInt();
        name = in.readString();
        isTask = in.readByte() != 0;
        state = in.readInt();
        priority = in.readInt();
        notes = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeByte((byte) (isTask ? 1 : 0));
        dest.writeInt(state);
        dest.writeInt(priority);
        dest.writeString(notes);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTask() {
        return isTask;
    }

    public void setTask(boolean task) {
        isTask = task;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        if(notes==null)
            this.notes="";
        else
            this.notes = notes;
    }

    public static class SortByName implements Comparator<Topic>{
        @Override
        public int compare(Topic o1, Topic o2) {
            //return o1.getName().compareToIgnoreCase(o2.getName());
            return 0;
        }
    }

    public static class SortByTopictName implements Comparator<Topic>{
        @Override
        public int compare(Topic o1, Topic o2) {
            //return o1.getSubject_name().compareToIgnoreCase(o2.getSubject_name());
            return 0;
        }
    }

    public static class SortByState implements Comparator<Topic>{
        @Override
        public int compare(Topic o1, Topic o2) {
            //return o1.getState() - o2.getState();
            return 0;
        }
    }
}
