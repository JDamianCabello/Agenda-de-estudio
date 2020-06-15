package es.jdamiancabello.agendadeestudio.data.repository;

import es.jdamiancabello.agendadeestudio.data.DAO.EmailDAO;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;

public class EmailRepository implements EmailDAO.ResendEmail, EmailDAO.ValidateEmail, EmailDAO.ResetPassEmail{
    public static EmailRepository emailRepository;
    private static ValidateEmailsInterface validateEmailsInterface;
    private static ResetEmailPass resetEmailPass;

    static {
        emailRepository = new EmailRepository();
    }

    public static EmailRepository getInstance(){
        return emailRepository;
    }

    public void resendEmail(ValidateEmailsInterface validateEmailsInterface) {
        this.validateEmailsInterface = validateEmailsInterface;
        EmailDAO.resendEmail(this);
    }

    public void confirmCode(ValidateEmailsInterface validateEmailsInterface, String verificationCode) {
        this.validateEmailsInterface = validateEmailsInterface;
        EmailDAO.validateCode(this,verificationCode);
    }

    @Override
    public void onSucessResend() {
        validateEmailsInterface.onResend();
    }

    @Override
    public void onSuccesValidate() {
        FocusApplication.getUser().setVerified(true);
        validateEmailsInterface.onValidateCode();
    }

    @Override
    public void onFailureValidate() {
        validateEmailsInterface.onFailureValidateCode();
    }

    public void sendRecoverPassEmail(ResetEmailPass resetEmailPass, String email) {
        this.resetEmailPass = resetEmailPass;
        EmailDAO.sendResetPassEmail(this, email);
    }

    @Override
    public void onSendResetCode() {
        resetEmailPass.onSend();
    }

    @Override
    public void onFailSendResetCode() {
        resetEmailPass.onDontSend();
    }

    @Override
    public void onUpdatedPass() {
        resetEmailPass.onUpdated();
    }

    @Override
    public void onFailUpdatePass() {
        resetEmailPass.onFailUpdate();
    }

    public void updatePassword(ResetEmailPass resetEmailPass, String password, String verifyCode) {
        this.resetEmailPass = resetEmailPass;
        EmailDAO.updateUserPass(this, password, verifyCode);
    }


    public interface ValidateEmailsInterface{
        void onResend();
        void onValidateCode();
        void onFailureValidateCode();
    }

    public interface ResetEmailPass{
        void onSend();
        void onDontSend();
        void onUpdated();
        void onFailUpdate();
    }
}
