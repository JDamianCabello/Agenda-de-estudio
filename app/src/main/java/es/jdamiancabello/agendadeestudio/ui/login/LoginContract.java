package es.jdamiancabello.agendadeestudio.ui.login;

import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class LoginContract {
    public interface View extends BaseView<Presenter> {
        void showWrongLogin();
    }

    interface Presenter{
        void loginUser(String user, String pass);
    }
}
