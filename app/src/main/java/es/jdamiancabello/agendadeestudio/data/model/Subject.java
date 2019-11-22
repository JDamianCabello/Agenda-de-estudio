package es.jdamiancabello.agendadeestudio.data.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Subject {


    private static final AtomicInteger count = new AtomicInteger(0);
    private final int idSubject;
    private String name;
    private int idUser;
    private Enum<state> stateEnum;

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
