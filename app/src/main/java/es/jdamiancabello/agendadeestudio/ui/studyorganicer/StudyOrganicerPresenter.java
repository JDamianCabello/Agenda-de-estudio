package es.jdamiancabello.agendadeestudio.ui.studyorganicer;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.StudyOrganicer;
import es.jdamiancabello.agendadeestudio.data.repository.StudyOrganicerRepository;

public class StudyOrganicerPresenter implements StudyOrganicerListContract.Presenter {
    public StudyOrganicerListContract.View viewSectorListContract;

    public StudyOrganicerPresenter(StudyOrganicerListContract.View viewSectorListContract) {
        this.viewSectorListContract = viewSectorListContract;
    }

    @Override
    public void delete(StudyOrganicer studyOrganicer) {
        //1. Realizar la operacion en el repo y comprobar el resultado
        if(StudyOrganicerRepository.getInstance().deleteStudyOrganicer(studyOrganicer)) {
            //1.2 Comprobar si no hay datos
            if(StudyOrganicerRepository.getInstance().getStudyOrganicerList().isEmpty())
                viewSectorListContract.noStudyOrganicers();
            else
            {
                //Aqui se muestra el toask de si hay datos (o la imagen)
            }
            viewSectorListContract.onSuccessDeleted();
        }
    }


    @Override
    public void load() {
        new AsyncTask<Void,Void, List<StudyOrganicer>>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                viewSectorListContract.showProgress();
            }

            @Override
            protected void onPostExecute(List<StudyOrganicer> studyOrganicerList) {
                super.onPostExecute(studyOrganicerList);
                if(studyOrganicerList.isEmpty()){
                    viewSectorListContract.noStudyOrganicers();
                }
                else{
                    viewSectorListContract.refresh((ArrayList<StudyOrganicer>) studyOrganicerList);
                }
                viewSectorListContract.hideProgress();
            }

            @Override
            protected List<StudyOrganicer> doInBackground(Void... voids) {
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                return StudyOrganicerRepository.getInstance().getStudyOrganicerList();
            }
        }.execute();
    }

    @Override
    public void undo(StudyOrganicer studyOrganicer) {
        if (StudyOrganicerRepository.getInstance().addDependency(studyOrganicer)) {
            viewSectorListContract.onSucessUndo(studyOrganicer);
        }
    }
}
