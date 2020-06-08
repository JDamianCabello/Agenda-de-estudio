package es.jdamiancabello.agendadeestudio.ui.login;

import android.content.Context;
import android.content.SharedPreferences;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.data.repository.UserRepository;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;

public class LoginPresenter implements LoginContract.Presenter, UserRepository.UserRepositoryListener{
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void loginUser(String user, String pass, boolean persistLogin) {
        UserRepository.getInstance().userLogin(this, user,pass, persistLogin);
    }

    @Override
    public void onSucessLogin(User user,String password, boolean persistLogin) {
        if(persistLogin)
            view.saveUSerData(user,password);
        else
            view.onSucess();
    }

    @Override
    public void onFailLogin(String message) {
        view.showWrongLogin(message);
    }
}
