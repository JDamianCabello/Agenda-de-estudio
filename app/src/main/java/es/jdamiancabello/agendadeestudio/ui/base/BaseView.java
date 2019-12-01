package es.jdamiancabello.agendadeestudio.ui.base;

public interface BaseView<T>{
    void onSucess();
    void setPresenter(T presenter);
    void showGenericError(String s);
}
