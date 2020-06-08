package es.jdamiancabello.agendadeestudio.ui.login;

import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class LoginContract {
    public interface View extends BaseView<Presenter> {
        void showWrongLogin(String msg);
        void saveUSerData(User user, String password);
    }

    interface Presenter{
        void loginUser(String user, String pass, boolean persistLogin);
    }
}
