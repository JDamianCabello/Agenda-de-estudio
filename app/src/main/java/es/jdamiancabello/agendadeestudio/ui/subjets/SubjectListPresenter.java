package es.jdamiancabello.agendadeestudio.ui.subjets;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;

public class SubjectListPresenter implements SubjectListContract.Presenter {
    private SubjectListContract.View view;

    public SubjectListPresenter(SubjectListContract.View vista) {
        this.view = vista;
    }

    @Override
    public void delete(Subject subject) {
        //1. Realizar la operacion en el repo y comprobar el resultado
        if(SubjectRepository.getInstance().delete(subject)) {
            //1.2 Comprobar si no hay datos
            if(SubjectRepository.getInstance().getSubjectList().isEmpty())
                view.noSubjets();
            else
            {
                //Aqui se muestra el toask de si hay datos (o la imagen)
            }
            view.onSuccessDeleted();
        }
    }

    @Override
    public void load() {
        new AsyncTask<Void,Void, List<Subject>>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                view.showProgress();
            }

            @Override
            protected void onPostExecute(List<Subject> subjectList) {
                super.onPostExecute(subjectList);
                if(subjectList.isEmpty()){
                    view.noSubjets();
                }
                else{
                    view.refresh((ArrayList<Subject>) subjectList);
                }
                view.hideProgress();
            }

            @Override
            protected List<Subject> doInBackground(Void... voids) {
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                return SubjectRepository.getInstance().getSubjectList();
            }
        }.execute();
    }

    @Override
    public void undo(Subject subject) {
        if (SubjectRepository.getInstance().addSubject(subject)) {
            view.onSucessUndo(subject);
        }
    }
}
