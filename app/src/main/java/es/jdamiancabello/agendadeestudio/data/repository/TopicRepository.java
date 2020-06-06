package es.jdamiancabello.agendadeestudio.data.repository;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.DAO.TopicDAO;
import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class TopicRepository implements TopicDAO.ResponseGET_TopicList {
    public static TopicRepository topicRepository;
    private List<Topic> topicsList;
    public static TopicRepository getInstance(){
        return topicRepository;
    }
    public static GetTopicListListener getTopicListListener;


    static {
        topicRepository = new TopicRepository();
    }

    private TopicRepository(){ topicsList = new ArrayList<>(); }

    public List<Topic> getDataList(){
        return topicsList;
    }

    public void startLoadData(GetTopicListListener getTopicListListener, int idSubject){
        this.getTopicListListener = getTopicListListener;
        TopicDAO.getTopicList(this, idSubject);
    }

    @Override
    public void onSucess(List<Topic> list) {
        topicsList.clear();
        topicsList.addAll(list);
        getTopicListListener.onDataLoaded();
    }

    public interface GetTopicListListener{
        void onDataLoaded();
    }
}
