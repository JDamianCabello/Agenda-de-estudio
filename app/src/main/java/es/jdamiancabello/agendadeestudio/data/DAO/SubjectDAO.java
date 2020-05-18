package es.jdamiancabello.agendadeestudio.data.DAO;

import android.util.Log;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.api_model.SubjectAddResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.SubjectListResponse;
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

    public static void addNewSubject(ResponseSubjectAddOrModify responseSubjectAddOrModify,String name, int state) {
        Call<SubjectAddResponse> call = ApiRestClientToken
                .getInstance()
                .addSubject(name,state);

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

    public interface ResponseSubject{
        void onSucess(List<Subject> subjectList);
    }

    public interface ResponseSubjectAddOrModify{
        void onSave();
    }
}

