package es.jdamiancabello.agendadeestudio.data.DAO;

import android.util.Log;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicAddResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicDeletedResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicListResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicModifyResponse;
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

    public static void updateTopic(ResponsePut_Topic responsePut_topic, Topic topic) {
        Call<TopicModifyResponse> call = ApiRestClientToken
                .getInstance()
                .modifyTopic(topic.getId(),topic.getName(),topic.isTask(),topic.getState(), topic.getPriority(), topic.getNotes());

        call.enqueue(new Callback<TopicModifyResponse>() {
            @Override
            public void onResponse(Call<TopicModifyResponse> call, Response<TopicModifyResponse> response) {
                if(response.isSuccessful()) {
                    if(!response.body().isError())
                        responsePut_topic.onSuccessUpdated();
                    else
                        responsePut_topic.onNotUpdated();
                }
            }

            @Override
            public void onFailure(Call<TopicModifyResponse> call, Throwable t) {
            }
        });
    }

    public static void deleteTopic(ResponseDelete_Topic responseDeleteTopic, int topicId) {
        Call<TopicDeletedResponse> call = ApiRestClientToken
                .getInstance()
                .deleteTopic(topicId);

        call.enqueue(new Callback<TopicDeletedResponse>() {
            @Override
            public void onResponse(Call<TopicDeletedResponse> call, Response<TopicDeletedResponse> response) {
                if(response.isSuccessful()) {
                    if(!response.body().isError())
                        responseDeleteTopic.onSuccessDelete(response.body().getTopicDeleted(),response.body().getNewPercent());
                }
            }

            @Override
            public void onFailure(Call<TopicDeletedResponse> call, Throwable t) {
            }
        });
    }


    public interface ResponseGET_TopicList{
        void onSucess(List<Topic> topicList);
    }

    public interface ResponsePost_Topic{
        void onSuccessAdd(Topic topic, int newPercent);
    }

    public interface ResponsePut_Topic{
        void onSuccessUpdated();
        void onNotUpdated();
    }

    public interface ResponseDelete_Topic{
        void onSuccessDelete(Topic topic, int newPercent);
    }
}
