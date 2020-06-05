package es.jdamiancabello.agendadeestudio.data.DAO;

import android.util.Log;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectAddResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectDeletedResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectListResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectModifyResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectDAO {

    public static void getSubjectList(ResponseSubject responseSubject) {

        Call<SubjectListResponse> call = ApiRestClientToken
                .getInstance()
                .getSubjectList();

        call.enqueue(new Callback<SubjectListResponse>() {
            @Override
            public void onResponse(Call<SubjectListResponse> call, Response<SubjectListResponse> response) {
                if(response.isSuccessful()) {
                    Log.d("getSubjectList","ESTA OK");
                    responseSubject.onSucess(response.body().getSubjects());

                }
            }

            @Override
            public void onFailure(Call<SubjectListResponse> call, Throwable t) {
                Log.d("getSubjectList","ESTA MAL");
            }
        });
    }

    public static void addNewSubject(ResponseSubjectAddOrModify responseSubjectAddOrModify,Subject newSubject) {
        Call<SubjectAddResponse> call = ApiRestClientToken
                .getInstance()
                .addSubject(newSubject.getSubject_name(), newSubject.getExam_date(), newSubject.getColor(), newSubject.getIconId());

        call.enqueue(new Callback<SubjectAddResponse>() {
            @Override
            public void onResponse(Call<SubjectAddResponse> call, Response<SubjectAddResponse> response) {
                if(response.isSuccessful()) {
                    responseSubjectAddOrModify.onSave();
                }
            }

            @Override
            public void onFailure(Call<SubjectAddResponse> call, Throwable t) {

            }
        });
    }

    public static void restoreSubject(ResponseSubjectRestore responseSubjectRestore,Subject newSubject) {
        Call<SubjectAddResponse> call = ApiRestClientToken
                .getInstance()
                .addSubject(newSubject.getSubject_name(), newSubject.getExam_date(), newSubject.getColor(), newSubject.getIconId());

        call.enqueue(new Callback<SubjectAddResponse>() {
            @Override
            public void onResponse(Call<SubjectAddResponse> call, Response<SubjectAddResponse> response) {
                if(response.isSuccessful()) {
                    responseSubjectRestore.onRestored(response.body().getSubject());
                }
            }

            @Override
            public void onFailure(Call<SubjectAddResponse> call, Throwable t) {

            }
        });
    }


    public static void deleteSubject(ResponseDeleteSubject responseDeleteSubject, Subject subject) {
        Call<SubjectDeletedResponse> call = ApiRestClientToken
                .getInstance()
                .deleteSubject(subject.getId());

        call.enqueue(new Callback<SubjectDeletedResponse>() {
            @Override
            public void onResponse(Call<SubjectDeletedResponse> call, Response<SubjectDeletedResponse> response) {
                if(response.isSuccessful()) {
                    responseDeleteSubject.onDeleted(subject,response.body().getTopicsDeleted());
                }
            }

            @Override
            public void onFailure(Call<SubjectDeletedResponse> call, Throwable t) {

            }
        });
    }

    public static void modifySubject(ResponseSubjectAddOrModify responseSubjectAddOrModify, Subject newSubject) {
        Call<SubjectModifyResponse> call = ApiRestClientToken
                .getInstance()
                .modifySubject(newSubject.getId(), newSubject.getSubject_name(), newSubject.getExam_date(), newSubject.getColor(), newSubject.getIconId());

        call.enqueue(new Callback<SubjectModifyResponse>() {
            @Override
            public void onResponse(Call<SubjectModifyResponse> call, Response<SubjectModifyResponse> response) {
                if(response.isSuccessful()) {
                    responseSubjectAddOrModify.onSave();
                }
            }

            @Override
            public void onFailure(Call<SubjectModifyResponse> call, Throwable t) {

            }
        });
    }

    public interface ResponseSubject{
        void onSucess(List<Subject> subjectList);
    }

    public interface ResponseSubjectAddOrModify{
        void onSave();
    }

    public interface ResponseSubjectRestore{
        void onRestored(Subject subject);
    }

    public interface ResponseDeleteSubject{
        void onDeleted(Subject deletedSubject, List<Topic> deletedTopicsList);
    }
}

