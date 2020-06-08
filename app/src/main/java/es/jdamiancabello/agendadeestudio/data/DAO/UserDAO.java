package es.jdamiancabello.agendadeestudio.data.DAO;

import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClient;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.data.model.api_model.LoginResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDAO {

    public static void UserLogin(LoginUser loginUser, String user, String pass, boolean persistLogin) {
        Call<LoginResponse> call = ApiRestClient
                .getInstance()
                .login(user,pass);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {

                    loginUser.onSucessLogin(response.body().getUser(),pass,persistLogin);
                }
                else{
                    loginUser.onFailedLogin(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }

    public static void getUser(GetSavedUserData getSavedUserData, String username, String password) {
        if(username == "" || password == "")
            return;

        Call<LoginResponse> call = ApiRestClient
                .getInstance()
                .login(username,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()) {
                    getSavedUserData.savedUserData(response.body().getUser());
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }

    public static void addUser(RegisterUser registerUser, String username, String email, String psw) {
        Call<RegisterResponse> call = ApiRestClient
                .getInstance()
                .register(username,email,psw);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful())
                    if(!response.body().getMessage().equals("duplicate email"))
                        registerUser.onDoneRegister(response.body().getUser());
                    else
                        registerUser.onDuplicateEmail();
            }
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });

    }


    public interface LoginUser{
        void onSucessLogin(User user,String password, boolean persistLogin);
        void onFailedLogin(String mesage);
    }

    public interface GetSavedUserData{
        void savedUserData(User user);
    }

    public interface RegisterUser{
        void onDoneRegister(User user);
        void onDuplicateEmail();
    }
}
