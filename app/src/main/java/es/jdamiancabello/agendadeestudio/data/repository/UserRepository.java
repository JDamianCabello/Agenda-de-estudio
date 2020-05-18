package es.jdamiancabello.agendadeestudio.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClient;
import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.api_model.LoginResponse;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.data.model.api_model.RegisterResponse;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static UserRepository userRepository;

    static {
        userRepository = new UserRepository();
    }

    public static UserRepository getInstance(){
        return userRepository;
    }

    public void UserLogin(String user, String pass, UserRepositoryListener userRepositoryListener, boolean persistLogin) {
        Call<LoginResponse> call = ApiRestClient
                .getInstance()
                .login(user,pass);

       call.enqueue(new Callback<LoginResponse>() {
           @Override
           public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
               if(response.isSuccessful()) {

                   if(persistLogin)
                       saveUserData(user,pass);

                   ApiRestClientToken.APITOKEN = response.body().getApiToken();

                   saveToken(response.body().getApiToken());
                   userRepositoryListener.onSucessLogin();


               }
               else{
                   userRepositoryListener.onFailLogin("");
               }
           }

           @Override
           public void onFailure(Call<LoginResponse> call, Throwable t) {
               Log.d("TESTEO fail", t.getMessage());
               userRepositoryListener.onFailLogin(t.getMessage());
           }
       });
    }

    private void saveUserData(String user, String pass) {
        SharedPreferences sharedPreferences = FocusApplication.getUserContext().getSharedPreferences(FocusApplication.getUserContext().getString(R.string.sharedUserDataLogin), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(User.userKey,user);
        editor.putString(User.passwordKey,pass);

        editor.apply();
    }

    private void saveToken(String token) {
        SharedPreferences sharedPreferences = FocusApplication.getUserContext().getSharedPreferences(FocusApplication.getUserContext().getString(R.string.sharedUserDataLogin), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(User.userToken,token);

        editor.apply();
    }

    public void userAdd(String username, String email, String psw, RegisterListener registerListener) {
        Call<RegisterResponse> call = ApiRestClient
                .getInstance()
                .register(username,email,psw);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful())
                    if(!response.body().getMessage().equals("duplicate email"))
                        registerListener.onSucessRegister();
                    else
                        registerListener.onDuplicateEmail();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });

    }

    public boolean existUser(String email) {
        return false;
    }

    public void getUser(String username, String password, WelcomeListener welcomeListener) {

        if(username == "" || password == "")
            return;

        Call<LoginResponse> call = ApiRestClient
                .getInstance()
                .login(username,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()) {
                    ApiRestClientToken.APITOKEN = response.body().getApiToken();
                    welcomeListener.onLoggedUser();
                }
                            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }

    public interface UserRepositoryListener{
        void onSucessLogin();
        void onFailLogin(String message);
    }

    public  interface WelcomeListener{
        void onLoggedUser();
    }

    public interface RegisterListener{
        void onSucessRegister();
        void onDuplicateEmail();
        void onFailedRegister();
    }
}
