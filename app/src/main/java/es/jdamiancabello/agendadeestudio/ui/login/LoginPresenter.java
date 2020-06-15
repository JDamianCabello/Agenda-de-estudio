package es.jdamiancabello.agendadeestudio.ui.login;

import android.content.Context;
import android.content.SharedPreferences;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.data.repository.EmailRepository;
import es.jdamiancabello.agendadeestudio.data.repository.UserRepository;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;

public class LoginPresenter implements LoginContract.Presenter, UserRepository.UserRepositoryListener, EmailRepository.ResetEmailPass{
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void loginUser(String user, String pass, boolean persistLogin) {
        UserRepository.getInstance().userLogin(this, user,pass, persistLogin);
    }

    @Override
    public void sendRecoverPassMail(String email) {
        EmailRepository.getInstance().sendRecoverPassEmail(this, email);
    }

    @Override
    public void updatePass(String password, String verifyCode) {
        EmailRepository.getInstance().updatePassword(this, password,verifyCode);
    }

    @Override
    public void onSucessLogin(User user,String password, boolean persistLogin) {
        if(persistLogin)
            view.saveUSerData(user,password);
        else
            view.onSucess();
    }

    @Override
    public void onWrongUserPass() {
        view.showWrongUserPassMessage();
    }

    @Override
    public void onDontExistEmail() {
        view.showDontExistEmail();
    }

    @Override
    public void onUnknowError() {
        view.showUnknowError();
    }

    @Override
    public void onSend() {
        view.onDoneRecoverPassEmailSend();
    }

    @Override
    public void onDontSend() {
        view.onFailRecoverPassEmailSend();
    }

    @Override
    public void onUpdated() {
        view.onSuccesPassUpdated();
    }

    @Override
    public void onFailUpdate() {
        view.onFailedPassupdated();
    }
}
