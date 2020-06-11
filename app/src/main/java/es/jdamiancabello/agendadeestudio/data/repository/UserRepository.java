package es.jdamiancabello.agendadeestudio.data.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.DAO.UserDAO;
import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClient;
import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.api_model.LoginResponse;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.data.model.api_model.RegisterResponse;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository implements UserDAO.LoginUser, UserDAO.GetSavedUserData, UserDAO.RegisterUser {
    private static UserRepository userRepository;
    private static UserRepositoryListener userRepositoryListener;
    private static WelcomeListener welcomeListener;
    private static RegisterListener registerListener;

    static {
        userRepository = new UserRepository();
    }

    public static UserRepository getInstance(){
        return userRepository;
    }

    public void userLogin(UserRepositoryListener userRepositoryListener, String user, String pass, boolean persistLogin){
        this.userRepositoryListener = userRepositoryListener;
        UserDAO.UserLogin(this,user,pass,persistLogin);
    }

    public void userSavedData(WelcomeListener welcomeListener, String user, String pass){
        this.welcomeListener = welcomeListener;
        UserDAO.getUser(this,user,pass);
    }

    public void userAdd(RegisterListener registerListener, String username, String email, String psw) {
        this.registerListener = registerListener;
        UserDAO.addUser(this,username,email,psw);
    }

    @Override
    public void onSucessLogin(User user,String password, boolean persistLogin) {
        ApiRestClientToken.APITOKEN = user.getApi_token();
        FocusApplication.setUser(user);

        SharedPreferences sharedPreferences = FocusApplication.getUserContext().getSharedPreferences(FocusApplication.getUserContext().getString(R.string.sharedUserDataLogin), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(User.userToken,user.getApi_token());

        editor.apply();

        userRepositoryListener.onSucessLogin(user, password,persistLogin);
    }

    @Override
    public void onWrongUserPass() {
        userRepositoryListener.onWrongUserPass();
    }

    @Override
    public void onNoExistEmailFail() {
        userRepositoryListener.onDontExistEmail();
    }

    @Override
    public void unknowError() {
        userRepository.unknowError();
    }

    @Override
    public void savedUserData(User user) {
        if(!user.isVerified())
            return;
        ApiRestClientToken.APITOKEN = user.getApi_token();
        welcomeListener.onLoggedUser();
    }

    @Override
    public void onDoneRegister(User user) {
        ApiRestClientToken.APITOKEN = user.getApi_token();
        FocusApplication.setUser(user);
        registerListener.onSucessRegister();
    }

    @Override
    public void onDuplicateEmail() {
        registerListener.onDuplicateEmail();
    }

    public interface UserRepositoryListener{
        void onSucessLogin(User user,String password, boolean persistLogin);
        void onWrongUserPass();
        void onDontExistEmail();
        void onUnknowError();
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
