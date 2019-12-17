package es.jdamiancabello.agendadeestudio.ui.welcome;

import android.content.Context;
import android.content.SharedPreferences;


import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.data.repository.UserRepository;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;

public class WelcomePresenter implements WelcomeContract.Presenter {
    WelcomeContract.view view;

    public WelcomePresenter(WelcomeContract.view view) {
        this.view = view;
    }

    @Override
    public void checkUserLogged() {
        SharedPreferences sharedPreferences = FocusApplication.getUserContext().getSharedPreferences(
                FocusApplication.getUserContext().getString(R.string.sharedUserDataLogin), Context.MODE_PRIVATE);

        String username = sharedPreferences.getString(User.userKey,"");
        String password = sharedPreferences.getString(User.passwordKey,"");

        if(UserRepository.getInstance().getUser(username,password) != null) {
            FocusApplication.setUser(UserRepository.getInstance().getUser(username, password));
            view.existUserLogged();
        }

    }
}
