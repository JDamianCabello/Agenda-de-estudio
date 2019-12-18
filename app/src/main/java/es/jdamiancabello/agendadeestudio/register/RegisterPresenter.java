package es.jdamiancabello.agendadeestudio.register;

import es.jdamiancabello.agendadeestudio.data.repository.UserRepository;

public class RegisterPresenter implements RegisterContract.Presenter{
    private RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void login(String email, String username, String psw, String confirmPsw) {
        if(UserRepository.getInstance().userAdd(username,email,psw))
            view.onSucess();
    }

    @Override
    public void checkRegisterUser(String email) {

    }
}
