package es.jdamiancabello.agendadeestudio.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import es.jdamiancabello.agendadeestudio.data.DAOroom.TopicDao_room;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.room_database.Database;

public class TopicRepository_room {
    private static TopicRepository_room topicRepository;
    private TopicDao_room topicDao;

    static {
        topicRepository = new TopicRepository_room();
    }

    public static TopicRepository_room getInstance(){
        return  topicRepository;
    }

    private TopicRepository_room() {
        topicDao = Database.getDatabase().TopicDAO();
    }

    public List<Topic> getList(){
        try{
            return Database.databaseWriterExecutor.submit(()-> topicDao.getAll()).get();
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Topic> getListFromSubject(String subjectName){
        try{
            return Database.databaseWriterExecutor.submit(()-> topicDao.getAllFromSubject(subjectName)).get();
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean insert(Topic topic){
        Database.databaseWriterExecutor.execute(()->topicDao.insert(topic));
        return true;
    }

    public boolean update(Topic topic){
        Database.databaseWriterExecutor.execute(()->topicDao.update(topic));
        return true;
    }

    public boolean delete(Topic topic){
        Database.databaseWriterExecutor.execute(()->topicDao.delete(topic));
        return true;
    }

}
