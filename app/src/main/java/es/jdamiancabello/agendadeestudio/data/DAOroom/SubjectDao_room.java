package es.jdamiancabello.agendadeestudio.data.DAOroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;

@Dao
public interface SubjectDao_room {
    @Insert
    void insert(Subject subject);

    @Delete
    void delete(Subject subject);

    @Update
    void update(Subject subject);

    @Query("SELECT * FROM subject ORDER by subject_name ASC")
    public List<Subject> getAll();
}
