package es.jdamiancabello.agendadeestudio.ui.verifyemail;

import es.jdamiancabello.agendadeestudio.data.repository.EmailRepository;

public class VerifyEmailPresenter implements VerifyEmailContract.Presenter, EmailRepository.ValidateEmailsInterface {
    private VerifyEmailContract.View view;

    public VerifyEmailPresenter(VerifyEmailContract.View view) {
        this.view = view;
    }

    @Override
    public void onResendEmail() {
        EmailRepository.getInstance().resendEmail(this);
    }

    @Override
    public void onCheckVerification(String verificationCode) {
        EmailRepository.getInstance().confirmCode(this, verificationCode);
    }

    @Override
    public void onResend() {
        view.showSendEmailToast();
    }

    @Override
    public void onValidateCode(){
        view.onSucessValidate();
    }

    @Override
    public void onFailureValidateCode() {
        view.onFailVerify();
    }
}
