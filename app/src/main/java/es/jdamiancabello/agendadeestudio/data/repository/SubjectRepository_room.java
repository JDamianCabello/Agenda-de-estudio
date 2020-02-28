package es.jdamiancabello.agendadeestudio.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import es.jdamiancabello.agendadeestudio.data.DAOroom.SubjectDao_room;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.room_database.Database;

public class SubjectRepository_room{
    private static SubjectRepository_room subjectRepository;
    private SubjectDao_room subjectDAO;

    static {
        subjectRepository = new SubjectRepository_room();
    }

    public static SubjectRepository_room getInstance(){
        return  subjectRepository;
    }

    private SubjectRepository_room() {
        subjectDAO = Database.getDatabase().subjectDAO();
    }

    public List<Subject> getList(){
        try{
            return Database.databaseWriterExecutor.submit(()-> subjectDAO.getAll()).get();
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public long insert(Subject subject){
        long rowID = -1;
        try {
            rowID = Database.databaseWriterExecutor.submit(()->subjectDAO.insert(subject)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return rowID;
    }

    public boolean update(Subject subject){
        Database.databaseWriterExecutor.execute(()->subjectDAO.update(subject));
        return true;
    }

    public boolean delete(Subject subject){
        Database.databaseWriterExecutor.execute(()->subjectDAO.delete(subject));
        return true;
    }

}
