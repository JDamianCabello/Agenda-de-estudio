package es.jdamiancabello.agendadeestudio.data.repository;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.DAO.TopicDAO;
import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class TopicRepository implements TopicDAO.ResponseGET_TopicList, TopicDAO.ResponsePost_Topic {
    public static TopicRepository topicRepository;
    public static TopicRepository getInstance(){
        return topicRepository;
    }
    public static TopicRepositoryListener getTopicListListener;


    static {
        topicRepository = new TopicRepository();
    }

    public void startLoadData(TopicRepositoryListener getTopicListListener, int idSubject){
        this.getTopicListListener = getTopicListListener;
        TopicDAO.getTopicList(this, idSubject);
    }

    public void addTopic(TopicRepositoryListener getTopicListListener, int idSubject, Topic topic){
        this.getTopicListListener = getTopicListListener;
        TopicDAO.addTopic(this, idSubject, topic);
    }

    @Override
    public void onSucess(List<Topic> list) {
        getTopicListListener.onDataLoaded(list);
    }

    @Override
    public void onSuccessAdd(Topic topic, int newPercent) {
        getTopicListListener.onTopicAdded(topic, newPercent);
    }

    public interface TopicRepositoryListener {
        void onDataLoaded(List<Topic> list);
        void onTopicAdded(Topic topic, int newPercent);
    }
}
