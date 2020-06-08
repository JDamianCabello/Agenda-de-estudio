package es.jdamiancabello.agendadeestudio.data.repository;

import es.jdamiancabello.agendadeestudio.data.DAO.EmailDAO;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;

public class EmailRepository implements EmailDAO.ResendEmail, EmailDAO.ValidateEmail{
    public static EmailRepository emailRepository;
    private static ValidateEmailsInterface validateEmailsInterface;

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


    public interface ValidateEmailsInterface{
        void onResend();
        void onValidateCode();
        void onFailureValidateCode();
    }
}
