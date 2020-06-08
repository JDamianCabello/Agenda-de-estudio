package es.jdamiancabello.agendadeestudio.ui.verifyemail;

public class VerifyEmailContract {
    interface View{
        void setPresenter(Presenter presenter);
        void onFailVerify();
        void showSendEmailToast();
        void onSucessValidate();
    }

    interface Presenter{
        void onResendEmail();
        void onCheckVerification(String verificationCode);
    }
}
