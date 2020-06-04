package es.jdamiancabello.agendadeestudio.ui.topic;

import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class TopicListPresenter implements TopicListContract.Presenter {
    private TopicListContract.View view;

    public TopicListPresenter(TopicListContract.View view) {
        this.view = view;
    }

    @Override
    public void delete(Topic topic) {
//        TopicRepository_room.getInstance().delete(topic);
        view.onUndo(topic);
    }

    @Override
    public void load() {
//        view.showProgress();
//        if(TopicRepository_room.getInstance().getList().size() == 0)
//            view.noTopics();
//        else {
//            view.refresh(TopicRepository_room.getInstance().getList());
//        }
//        view.hideProgress();
    }

    @Override
    public void undo(Topic topic) {
        view.onUndo(topic);
    }

    @Override
    public void onSucessUndo(Topic topic) {
//        TopicRepository_room.getInstance().insert(topic);
//        view.onSucessUndo(topic);
    }
}
