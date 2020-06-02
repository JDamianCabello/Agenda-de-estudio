package es.jdamiancabello.agendadeestudio.ui.subjectinfo;

import es.jdamiancabello.agendadeestudio.data.repository.TopicRepository_room;

public class SubjectInfoPresenter implements SubjectInfoContract.Presenter {
    private SubjectInfoContract.View view;

    public SubjectInfoPresenter(SubjectInfoContract.View view) {
        this.view = view;
    }


    @Override
    public void loadData() {
        if(TopicRepository_room.getInstance().getList().size() == 0)
            view.noTopics();
        else {
            view.refresh(TopicRepository_room.getInstance().getList());
        }
    }
}
