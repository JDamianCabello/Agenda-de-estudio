package es.jdamiancabello.agendadeestudio.data.Network;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import es.jdamiancabello.agendadeestudio.BuildConfig;
import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRestClientToken {
    private static ApiCalls API_SERVICE;

    private static final String BASE_URL = "https://focus.edufdez.es/";
    public static String APITOKEN = "";

    public static synchronized ApiCalls getInstance() {

        if (API_SERVICE == null) {

            OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS);

            okHttpBuilder.addInterceptor(new LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .addHeader("Api-Token", getPreferencesApiToken())
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

    private static String getPreferencesApiToken() {
        SharedPreferences sharedPreferences = FocusApplication.getUserContext().getSharedPreferences(
                FocusApplication.getUserContext().getString(R.string.sharedUserDataLogin), Context.MODE_PRIVATE);

        return sharedPreferences.getString(User.userToken,"");
    }

    public static void loggout(){
        API_SERVICE = null;
    }

}
