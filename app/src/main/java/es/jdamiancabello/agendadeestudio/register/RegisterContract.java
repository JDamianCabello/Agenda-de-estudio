package es.jdamiancabello.agendadeestudio.register;

import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class RegisterContract {
    public interface View extends BaseView<Presenter> {
        void setDuplicateEmailError();
        void setNotValidEmailError();
        void setVoidEmailError();

        void setVoidUsernameError();
        void setNotValidUsernameError();

        void setVoidPasswordError();
        void setNotValidPasswordError();

        void setVoidConfirmPasswordError();
        void setNoMachPasswordError();



        void clearDuplicateEmailError();
        void clearNotValidEmailError();
        void clearVoidEmailError();

        void clearVoidUsernameError();
        void clearNotValidUsernameError();

        void clearVoidPasswordError();
        void clearNotValidPasswordError();

        void clearVoidConfirmPasswordError();
        void clearNoMachPasswordError();


    }

    public interface Presenter{
        void login(String email, String username, String psw, String confirmPsw);
        boolean checkRegisterUser(String email);
    }
}
