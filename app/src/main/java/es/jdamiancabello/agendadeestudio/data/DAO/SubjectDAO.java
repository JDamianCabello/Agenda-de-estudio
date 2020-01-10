package es.jdamiancabello.agendadeestudio.data.DAO;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
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
                    responseSubject.onSucess(response.body().getSubjects());
                }
            }

            @Override
            public void onFailure(Call<SubjectListResponse> call, Throwable t) {

            }
        });
    }

    public interface ResponseSubject{
        void onSucess(List<Subject> subjectList);
    }
}

