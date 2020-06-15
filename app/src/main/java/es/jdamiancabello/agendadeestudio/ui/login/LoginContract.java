package es.jdamiancabello.agendadeestudio.ui.login;

import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class LoginContract {
    public interface View extends BaseView<Presenter> {
        void showWrongUserPassMessage();
        void showDontExistEmail();
        void showUnknowError();
        void saveUSerData(User user, String password);
        void onDoneRecoverPassEmailSend();
        void onFailRecoverPassEmailSend();
        void onSuccesPassUpdated();
        void onFailedPassupdated();
    }

    interface Presenter{
        void loginUser(String user, String pass, boolean persistLogin);
        void sendRecoverPassMail(String email);
        void updatePass(String password, String verifyCode);
    }
}
