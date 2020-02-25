package es.jdamiancabello.agendadeestudio.data.room_database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.jdamiancabello.agendadeestudio.data.DAOroom.SubjectDao_room;
import es.jdamiancabello.agendadeestudio.data.model.Subject;

@androidx.room.Database(entities = {Subject.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract SubjectDao_room subjectDAO();
    private static volatile Database INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static void create(final Context context){
        if(INSTANCE == null){
            synchronized (Database.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class,"FocusDatabase")
                            .build();
                }
            }
        }
    }

    public static Database getDatabase(){return INSTANCE;}
}
