package es.jdamiancabello.agendadeestudio.ui.login;

import es.jdamiancabello.agendadeestudio.data.repository.UserRepository;

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void loginUser(String user, String pass) {
        if(UserRepository.getInstance().UserLogin(user,pass))
            view.onSucess();
        else
            view.showWrongLogin();
    }
}
