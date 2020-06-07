package es.jdamiancabello.agendadeestudio.data.DAO;

import android.util.Log;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicDAO {

    public static void getTopicList(ResponseGET_TopicList responseGETTopicList, int idSubject) {

        Call<TopicListResponse> call = ApiRestClientToken
                .getInstance()
                .getTopicList(idSubject);

        call.enqueue(new Callback<TopicListResponse>() {
            @Override
            public void onResponse(Call<TopicListResponse> call, Response<TopicListResponse> response) {
                if(response.isSuccessful()) {
                    responseGETTopicList.onSucess(response.body().getTopicList());
                    Log.d("APICALL","ok");
                }
            }

            @Override
            public void onFailure(Call<TopicListResponse> call, Throwable t) {
                Log.d("APICALL","mal 2");
            }
        });
    }



    public interface ResponseGET_TopicList{
        void onSucess(List<Topic> topicList);
    }
}
