package es.jdamiancabello.agendadeestudio.ui.login;

import android.content.Context;
import android.content.SharedPreferences;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.data.repository.UserRepository;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void loginUser(String user, String pass) {
        if(UserRepository.getInstance().UserLogin(user,pass)) {
            saveUserData(user,pass);
            FocusApplication.setUser(UserRepository.getInstance().getUser(user,pass));
            view.onSucess();
        }
        else
            view.showWrongLogin();
    }

    private void saveUserData(String user, String pass) {
        SharedPreferences sharedPreferences = FocusApplication.getUserContext().getSharedPreferences(FocusApplication.getUserContext().getString(R.string.sharedUserDataLogin), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(User.userKey,user);
        editor.putString(User.passwordKey,pass);

        editor.commit();
    }
}
