package es.jdamiancabello.agendadeestudio.data.DAOroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Topic;

@Dao
public interface TopicDao_room {
    @Insert
    void insert(Topic topic);

    @Delete
    void delete(Topic topic);

    @Update
    void update(Topic topic);

    @Query("SELECT * FROM topic ORDER by subject_name ASC")
    public List<Topic> getAll();

    @Query("SELECT * FROM topic WHERE subject_name =:subjectName")
    public List<Topic> getAllFromSubject(String subjectName);
}
