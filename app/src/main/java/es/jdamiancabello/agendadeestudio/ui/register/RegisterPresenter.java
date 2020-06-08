package es.jdamiancabello.agendadeestudio.ui.register;

import es.jdamiancabello.agendadeestudio.data.repository.UserRepository;
import es.jdamiancabello.agendadeestudio.utils.CommonUtils;

public class RegisterPresenter implements RegisterContract.Presenter, UserRepository.RegisterListener{
    private RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void login(String email, String username, String psw, String confirmPsw) {
        if(checkRegisterUser(email) && validateAll(email,username,psw,confirmPsw))
            UserRepository.getInstance().userAdd(this, username, email, psw);
    }

    @Override
    public boolean checkRegisterUser(String email) {
        return true;
    }

    private boolean validateAll(String email, String username, String psw, String confirmPsw){
        return (validateEmail(email) & validateUser(username) & validatePassword(psw) & validateMatchPassword(psw, confirmPsw));
    }





    //region Username_Control

    private boolean validateNotEmptyUser(String username) {
        if(username.isEmpty()){
            view.setVoidUsernameError();
            return false;
        }
        else{
            view.clearVoidUsernameError();
            return true;
        }
    }

    /**
     * Validate if user lenght are into 6 or 20 char
     *
     * @param user
     * @return
     */
    private boolean validateUser(String user) {

        if(validateNotEmptyUser(user)) {
            if (CommonUtils.patterUser(user)) {
                view.clearNotValidUsernameError();
                return true;
            } else {
                view.setNotValidUsernameError();
                return false;
            }
        }
        return false;
    }

    //endregion

    //region Email_Control

    private boolean validateNotEmptyEmail(String email) {
        if(email.isEmpty()){
            view.setVoidEmailError();
            return false;
        }
        else {
            view.clearVoidEmailError();
            return true;
        }
    }


    /**
     * Comprueba que lo introducido sea un correo válido además de:
     *      -no esté vacío
     * @return
     * @param s El string que contiene el e-mail
     */
    private boolean validateEmail(String s) {
        if(validateNotEmptyEmail(s)) {
            if (CommonUtils.patternEmail(s)) {
                view.clearNotValidEmailError();
                return true;
            } else {
                view.setNotValidEmailError();
                return false;
            }
        }
        return false;
    }

    //endregion

    //region Password_Control

    /**
     * Comprueba la contraseña:
     *      -tenga entre 8-12 de longitud
     *      -tenga un número
     *      -tenga una mayúscula
     *      -las contraseñas son iguales
     *      -no puede ser nulo (arreglado don el primer control)
     * @return
     * @param psw la contraseña a validar
     */
    private boolean validatePassword(String psw) {
        if(validateNotEmptyPassword(psw)) {
            if (!CommonUtils.patterPassword(psw)) {
                view.setNotValidPasswordError();
                return false;
            } else {
                view.clearNotValidPasswordError();
                return true;
            }
        }
        return false;
    }

    private boolean validateMatchPassword(String psw, String confirmPsw) {
        if(validateNotEmptyMatchPassword(confirmPsw)) {
            if (psw.equals(confirmPsw)) {
                view.clearNoMachPasswordError();
                return true;
            } else {
                view.setNoMachPasswordError();
                return false;
            }
        }
        return false;
    }

    private boolean validateNotEmptyMatchPassword(String confirmPsw) {
        if(confirmPsw.isEmpty()){
            view.setVoidConfirmPasswordError();
            return false;
        }
        else{
            view.clearVoidConfirmPasswordError();
            return true;
        }
    }

    private boolean validateNotEmptyPassword(String psw) {
        if(psw.isEmpty()){
            view.setVoidPasswordError();
            return false;
        }
        else{
            view.clearVoidPasswordError();
            return true;
        }
    }

    @Override
    public void onSucessRegister() {
        view.onSucess();
    }

    @Override
    public void onDuplicateEmail() {
        view.setDuplicateEmailError();
    }

    @Override
    public void onFailedRegister() {

    }

    //endregion

}
