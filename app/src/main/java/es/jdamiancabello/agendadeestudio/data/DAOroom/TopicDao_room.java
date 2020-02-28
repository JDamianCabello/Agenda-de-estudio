package es.jdamiancabello.agendadeestudio.data.DAOroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Topic;

@Dao
public interface TopicDao_room {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Topic topic);

    @Insert
    void insert(List<Topic> topics);

    @Delete
    void delete(Topic topic);

    @Update
    void update(Topic topic);

    @Query("SELECT * FROM topic")
    List<Topic> getAll();

    @Query("SELECT * FROM topic WHERE subject_name =:subjectName")
    List<Topic> getAllFromSubject(String subjectName);
}
