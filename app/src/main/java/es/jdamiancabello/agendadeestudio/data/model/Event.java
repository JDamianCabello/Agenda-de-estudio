package es.jdamiancabello.agendadeestudio.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Event implements Parcelable {
    private int id;
    private String event_name;
    private String event_resume;
    private String event_date;
    private int idSubject;
    private int event_color;
    private int event_iconId;
    private boolean appnotification;
    private String event_notes;

    public Event(){}

    public Event(String event_name, String event_resume, String event_date, int idSubject, int event_color, int event_iconId, boolean appnotification, String event_notes) {
        this.event_name = event_name;
        this.event_resume = event_resume;
        this.event_date = event_date;
        this.idSubject = idSubject;
        this.event_color = event_color;
        this.event_iconId = event_iconId;
        this.appnotification = appnotification;
        this.event_notes = event_notes;
    }

    protected Event(Parcel in) {
        id = in.readInt();
        event_name = in.readString();
        event_resume = in.readString();
        event_date = in.readString();
        idSubject = in.readInt();
        event_color = in.readInt();
        event_iconId = in.readInt();
        appnotification = in.readByte() != 0;
        event_notes = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(event_name);
        dest.writeString(event_resume);
        dest.writeString(event_date);
        dest.writeInt(idSubject);
        dest.writeInt(event_color);
        dest.writeInt(event_iconId);
        dest.writeByte((byte) (appnotification ? 1 : 0));
        dest.writeString(event_notes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_resume() {
        return event_resume;
    }

    public void setEvent_resume(String event_resume) {
        this.event_resume = event_resume;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public int getEvent_color() {
        return event_color;
    }

    public void setEvent_color(int event_color) {
        this.event_color = event_color;
    }

    public int getEvent_iconId() {
        return event_iconId;
    }

    public void setEvent_iconId(int event_iconId) {
        this.event_iconId = event_iconId;
    }

    public boolean isAppnotification() {
        return appnotification;
    }

    public void setAppnotification(boolean appnotification) {
        this.appnotification = appnotification;
    }

    public String getEvent_notes() {
        return event_notes;
    }

    public void setEvent_notes(String event_notes) {
        this.event_notes = event_notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
