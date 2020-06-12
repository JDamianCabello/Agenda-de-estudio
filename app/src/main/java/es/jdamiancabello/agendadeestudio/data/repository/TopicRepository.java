package es.jdamiancabello.agendadeestudio.data.repository;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.DAO.TopicDAO;
import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class TopicRepository implements TopicDAO.ResponseGET_TopicList, TopicDAO.ResponsePost_Topic, TopicDAO.ResponsePut_Topic, TopicDAO.ResponseDelete_Topic {
    public static TopicRepository topicRepository;
    public static TopicRepositoryAddListener topicRepositoryAddListener;
    public static TopicRepository getInstance(){
        return topicRepository;
    }
    public static TopicRepositoryListener getTopicListListener;
    public static TopicRepositorDeleteListener topicRepositorDeleteListener;


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

    public void updateTopic(TopicRepositoryAddListener topicRepositoryAddListener, Topic topic){
        this.topicRepositoryAddListener = topicRepositoryAddListener;
        TopicDAO.updateTopic(this, topic);
    }

    public void deleteTopic(TopicRepositorDeleteListener topicRepositorDeleteListener, int topicId){
        this.topicRepositorDeleteListener = topicRepositorDeleteListener;
        TopicDAO.deleteTopic(this, topicId);
    }

    @Override
    public void onSucess(List<Topic> list) {
        getTopicListListener.onDataLoaded(list);
    }

    @Override
    public void onSuccessAdd(Topic topic, int newPercent) {
        getTopicListListener.onTopicAdded(topic, newPercent);
    }

    @Override
    public void onSuccessUpdated() {
        topicRepositoryAddListener.onSuccessUpdated();
    }

    @Override
    public void onNotUpdated() {
        topicRepositoryAddListener.onNotUpdated();
    }

    @Override
    public void onSuccessDelete(Topic topic, int newPercent) {
        topicRepositorDeleteListener.onSuccessDeleted(topic, newPercent);
    }

    public interface TopicRepositoryListener {
        void onDataLoaded(List<Topic> list);
        void onTopicAdded(Topic topic, int newPercent);
    }

    public interface TopicRepositoryAddListener{
        void onSuccessUpdated();
        void onNotUpdated();
    }

    public interface TopicRepositorDeleteListener{
        void onSuccessDeleted(Topic topic, int newPercent);
    }
}
