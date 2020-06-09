package es.jdamiancabello.agendadeestudio.data.DAO;

import android.util.Log;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicAddResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicListResponse;
import es.jdamiancabello.agendadeestudio.data.repository.TopicRepository;
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
                }
            }

            @Override
            public void onFailure(Call<TopicListResponse> call, Throwable t) {
            }
        });
    }

    public static void addTopic(ResponsePost_Topic responsePostTopic, int idSubject, Topic topic) {
        Call<TopicAddResponse> call = ApiRestClientToken
                .getInstance()
                .addTopic(idSubject,topic.getName(),topic.isTask(),topic.getState(), topic.getPriority());

        call.enqueue(new Callback<TopicAddResponse>() {
            @Override
            public void onResponse(Call<TopicAddResponse> call, Response<TopicAddResponse> response) {
                if(response.isSuccessful()) {
                    responsePostTopic.onSuccessAdd(response.body().getTopic(), response.body().getPercent());
                }
            }

            @Override
            public void onFailure(Call<TopicAddResponse> call, Throwable t) {
            }
        });
    }


    public interface ResponseGET_TopicList{
        void onSucess(List<Topic> topicList);
    }

    public interface ResponsePost_Topic{
        void onSuccessAdd(Topic topic, int newPercent);
    }
}
