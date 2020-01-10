package es.jdamiancabello.agendadeestudio.ui.subjets;


import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;

public class SubjectManagerPresenter implements SubjectManagerContract.Presenter, SubjectRepository.ManageSubject{
    private SubjectManagerContract.View view;

    public SubjectManagerPresenter(SubjectManagerContract.View view) {
        this.view = view;
    }

    @Override
    public void addSubject(String name, int state) {
        SubjectRepository.getInstance().addSubject(this, name,state);
    }

    @Override
    public void modifySubject(int id, String name, int state) {

    }

    @Override
    public void onSaved() {
        view.onSucess();
    }
}
