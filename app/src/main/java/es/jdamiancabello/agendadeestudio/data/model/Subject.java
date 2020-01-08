package es.jdamiancabello.agendadeestudio.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class Subject implements Parcelable {



    private String name;
    private int idUser;
    private Enum<state> stateEnum;

    protected Subject(Parcel in) {
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
        parcel.writeString(name);
        parcel.writeInt(idUser);
    }

    public enum  state{
        Dominado,A_repasar,Ignorado
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
        this.name = name;
        this.idUser = 1;
        this.stateEnum = stateEnum;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public static class SortByName implements Comparator<Subject> {

        @Override
        public int compare(Subject o1, Subject o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }
}
