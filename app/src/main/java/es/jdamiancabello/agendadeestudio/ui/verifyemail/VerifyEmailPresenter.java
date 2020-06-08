package es.jdamiancabello.agendadeestudio.ui.verifyemail;

public class VerifyEmailPresenter implements VerifyEmailContract.Presenter {
    private VerifyEmailContract.View view;

    public VerifyEmailPresenter(VerifyEmailContract.View view) {
        this.view = view;
    }

    @Override
    public void onResendEmail() {

    }

    @Override
    public void onCheckVerification(String verificationCode) {

    }
}
