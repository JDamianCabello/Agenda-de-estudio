package es.jdamiancabello.agendadeestudio.data.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import es.jdamiancabello.agendadeestudio.BuildConfig;
import es.jdamiancabello.agendadeestudio.data.Network.ApiCalls;
import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectListResponse;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationDAO {

    public static void getSubjectList(ResponseNotification responseNotification, String token) {

        NotificationApiRestClientToken notificationApiRestClientToken = new NotificationApiRestClientToken(token);

        Call<SubjectListResponse> call = notificationApiRestClientToken
                .getInstance()
                .getSubjectList();

        call.enqueue(new Callback<SubjectListResponse>() {
            @Override
            public void onResponse(Call<SubjectListResponse> call, Response<SubjectListResponse> response) {
                if(response.isSuccessful()) {
                    responseNotification.onSucess(response.body().getSubjects());
                }
            }

            @Override
            public void onFailure(Call<SubjectListResponse> call, Throwable t) {
            }
        });
    }

    public interface ResponseNotification{
        void onSucess(List<Subject> subjectList);
    }


    private static class NotificationApiRestClientToken {

        public NotificationApiRestClientToken(String APITOKEN) {
            this.APITOKEN = APITOKEN;
        }

        private ApiCalls API_SERVICE;
        private static final String BASE_URL = "https://focus.edufdez.es/";
        private String APITOKEN = "";

        public synchronized ApiCalls getInstance() {

            if (API_SERVICE == null) {

                OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(5, TimeUnit.SECONDS);

                okHttpBuilder.addInterceptor(new LoggingInterceptor.Builder()
                        .loggable(BuildConfig.DEBUG)
                        .setLevel(Level.BASIC)
                        .addHeader("Api-Token", APITOKEN)
                        .log(Platform.INFO).build());

                Gson gson = new GsonBuilder()
                        .setDateFormat("dd-MM-yyyy")
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(okHttpBuilder.build())
                        .build();

                API_SERVICE = retrofit.create(ApiCalls.class);
            }
            return API_SERVICE;
        }
    }




}
