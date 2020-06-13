package es.jdamiancabello.agendadeestudio.data.DAO;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.Event;
import es.jdamiancabello.agendadeestudio.data.model.api_model.event.DayEventsListResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.event.EventAddResponse;
import es.jdamiancabello.agendadeestudio.data.repository.EventRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDAO {
    public static void getDateEvents(EventDaoListener eventDaoListener, String date) {

        Call<DayEventsListResponse> call = ApiRestClientToken
                .getInstance()
                .getDateEvents(date);

        call.enqueue(new Callback<DayEventsListResponse>() {
            @Override
            public void onResponse(Call<DayEventsListResponse> call, Response<DayEventsListResponse> response) {
                if(response.isSuccessful()) {
                    eventDaoListener.onGetData(response.body().getEvents());
                }
            }

            @Override
            public void onFailure(Call<DayEventsListResponse> call, Throwable t) {
            }
        });
    }

    public static void getNotificationEvents(EventDaoNotificationListener eventDaoNotificationListener) {

        Call<DayEventsListResponse> call = ApiRestClientToken
                .getInstance()
                .getNotifications();

        call.enqueue(new Callback<DayEventsListResponse>() {
            @Override
            public void onResponse(Call<DayEventsListResponse> call, Response<DayEventsListResponse> response) {
                if(response.isSuccessful()) {
                    eventDaoNotificationListener.onSuccess(response.body().getEvents());
                }
            }

            @Override
            public void onFailure(Call<DayEventsListResponse> call, Throwable t) {
            }
        });
    }

    public static void addEvent(EventRepository eventRepository, Event event) {
        Call<EventAddResponse> call = ApiRestClientToken
                .getInstance()
                .addEvent(event.getEvent_name(),event.getEvent_resume(),event.getEvent_date(),-1,event.getEvent_color(), event.getEvent_iconId(),event.isAppnotification(),event.getEvent_notes());

        call.enqueue(new Callback<EventAddResponse>() {
            @Override
            public void onResponse(Call<EventAddResponse> call, Response<EventAddResponse> response) {
                if(response.isSuccessful()) {
                    eventRepository.onAdded(response.body().getEvent());
                }
            }

            @Override
            public void onFailure(Call<EventAddResponse> call, Throwable t) {
            }
        });
    }

    public static void getAllEvents(EventRepository eventRepository) {
        Call<DayEventsListResponse> call = ApiRestClientToken
                .getInstance()
                .getAllUserEvents();

        call.enqueue(new Callback<DayEventsListResponse>() {
            @Override
            public void onResponse(Call<DayEventsListResponse> call, Response<DayEventsListResponse> response) {
                if(response.isSuccessful()) {
                    eventRepository.gettingAllEvents(response.body().getEvents());
                }
            }

            @Override
            public void onFailure(Call<DayEventsListResponse> call, Throwable t) {
            }
        });
    }


    public interface EventDaoListener{
        void onGetData(List<Event> eventList);
        void onAdded(Event event);
        void gettingAllEvents(List<Event> eventList);
    }

    public interface EventDaoNotificationListener{
        void onSuccess(List<Event> eventList);
    }
}
