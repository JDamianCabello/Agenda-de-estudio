package es.jdamiancabello.agendadeestudio.ui.welcome;

import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class WelcomeContract {
    public interface view extends BaseView<Presenter> {
        void existUserLogged();
    }

    interface Presenter{
        void checkUserLogged();
    }
}
