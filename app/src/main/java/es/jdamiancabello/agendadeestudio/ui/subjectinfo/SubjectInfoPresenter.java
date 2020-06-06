package es.jdamiancabello.agendadeestudio.ui.subjectinfo;


import es.jdamiancabello.agendadeestudio.data.repository.TopicRepository;

public class SubjectInfoPresenter implements SubjectInfoContract.Presenter, TopicRepository.GetTopicListListener {
    private SubjectInfoContract.View view;

    public SubjectInfoPresenter(SubjectInfoContract.View view) {
        this.view = view;
    }


    @Override
    public void loadData(int idSubject) {
        TopicRepository.getInstance().startLoadData(this,idSubject);
    }

    @Override
    public void onDataLoaded() {
        view.refresh(TopicRepository.getInstance().getDataList());
    }
}
