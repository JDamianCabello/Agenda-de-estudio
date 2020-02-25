package es.jdamiancabello.agendadeestudio.ui.topic;

import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.repository.TopicRepository_room;

public class TopicListPresenter implements TopicListContract.Presenter {
    private TopicListContract.View view;

    public TopicListPresenter(TopicListContract.View view) {
        this.view = view;
    }

    @Override
    public void delete(Topic topic) {

    }

    @Override
    public void load() {
        view.refresh(TopicRepository_room.getInstance().getList());
    }

    @Override
    public void undo(Topic topic) {

    }

    @Override
    public void onSucessUndo(Topic topic) {

    }
}
