package es.jdamiancabello.agendadeestudio.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClient;
import es.jdamiancabello.agendadeestudio.data.model.api_model.LoginResponse;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.data.model.api_model.RegisterResponse;
import es.jdamiancabello.agendadeestudio.register.RegisterPresenter;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static UserRepository userRepository;
    private User userLogged;

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
                   userLogged = new User(response.body().getApiToken());

                   if(persistLogin)
                       saveUserData(user,pass);
                   FocusApplication.setUser(userLogged);

                   if(userLogged != null)
                       Log.d("TESTEO OK", userLogged.getApi_token());

                   userRepositoryListener.onSucessLogin();
               }


           }

           @Override
           public void onFailure(Call<LoginResponse> call, Throwable t) {
               Log.d("TESTEO fail", t.getMessage());
               userRepositoryListener.onFailLogin();
           }
       });
    }

    private void saveUserData(String user, String pass) {
        SharedPreferences sharedPreferences = FocusApplication.getUserContext().getSharedPreferences(FocusApplication.getUserContext().getString(R.string.sharedUserDataLogin), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(User.userKey,user);
        editor.putString(User.passwordKey,pass);

        editor.commit();
    }

    public void userAdd(String username, String email, String psw, RegisterListener registerListener) {
        Call<RegisterResponse> call = ApiRestClient
                .getInstance()
                .register(username,email,psw);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful())
                    registerListener.onSucessRegister();
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
        Call<LoginResponse> call = ApiRestClient
                .getInstance()
                .login(username,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    userLogged = new User(response.body().getApiToken());
                    FocusApplication.setUser(userLogged);
                    welcomeListener.onLoggedUser();
                }


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("TESTEO fail", t.getMessage());
            }
        });
    }

    public interface UserRepositoryListener{
        void onSucessLogin();
        void onFailLogin();
    }

    public  interface WelcomeListener{
        void onLoggedUser();
    }

    public interface RegisterListener{
        void onSucessRegister();
        void onDuplicateEmail();
        void onFailedRegister();
    }

//    private void Initialize(){
//        userList.add(new User("test","test@test.test","test"));
//    }
//
//    public boolean UserLogin(String email, String password){
//        for (int i = 0; i < userList.size(); i++) {
//            if(userList.get(i).getEmail().equals(email) && userList.get(i).getPassword().equals(password)
//                    || userList.get(i).getUserName().equals(email) && userList.get(i).getPassword().equals(password))
//                return true;
//        }
//        return false;
//    }
//
//    public boolean userAdd(String name, String email, String password){
//        for (int i = 0; i < userList.size(); i++) {
//            if(userList.get(i).getEmail().equals(email))
//                return false;
//        }
//        userList.add(new User(name,email,password));
//        return true;
//    }
//
//    public User getUser(String user, String pass) {
//        for(User u : userList){
//            if(u.getEmail().equals(user) && u.getPassword().equals(pass)
//            || u.getUserName().equals(user) && u.getPassword().equals(pass))
//                return u;
//        }
//        return null;
//    }
//
//    public boolean existUser(String email) {
//        for (User u : userList) {
//            if (u.getEmail().equals(email))
//                return true;
//        }
//        return false;
//    }
}
