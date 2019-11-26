package es.jdamiancabello.agendadeestudio.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.concurrent.atomic.AtomicInteger;

public class Subject implements Parcelable {


    private static final AtomicInteger count = new AtomicInteger(0);
    private final int idSubject;
    private String name;
    private int idUser;
    private Enum<state> stateEnum;

    protected Subject(Parcel in) {
        idSubject = in.readInt();
        name = in.readString();
        idUser = in.readInt();
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
        parcel.writeInt(idSubject);
        parcel.writeString(name);
        parcel.writeInt(idUser);
    }

    public enum  state{
        Dominado,A_repasar,Ignorado
    }

    public int getIdSubject() {
        return idSubject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdUser() {
        return idUser;
    }

    public Enum<state> getStateEnum() {
        return stateEnum;
    }

    public void setStateEnum(Enum<state> stateEnum) {
        this.stateEnum = stateEnum;
    }

    public Subject(String name, Enum<state> stateEnum) {
        this.idSubject = count.incrementAndGet();
        this.name = name;
        this.idUser = 1;
        this.stateEnum = stateEnum;
    }

}
