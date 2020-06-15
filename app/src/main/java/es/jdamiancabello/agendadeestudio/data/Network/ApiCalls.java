package es.jdamiancabello.agendadeestudio.data.Network;

import es.jdamiancabello.agendadeestudio.data.model.api_model.user.LoginResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.user.RegisterResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.email.EmailResendResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.email.EmailVerifyCodeResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.event.DayEventsListResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.event.EventAddResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.event.EventDeleteResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.event.EventUpdateResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectAddResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectDeletedResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectListResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectModifyResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicAddResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicDeletedResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicListResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicModifyResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.user.UserUpdatedPassword;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiCalls {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("register/")
    Call<RegisterResponse> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password);

    @GET("subject/")
    Call<SubjectListResponse> getSubjectList();

    @FormUrlEncoded
    @POST("subject/")
    Call<SubjectAddResponse> addSubject(
            @Field("subject_name") String subject_name,
            @Field("date") String date,
            @Field("color") int color,
            @Field("iconId") int iconId,
            @Field("makeEvent") boolean makeEvent);

    @DELETE("subject/{idSubject}")
    Call<SubjectDeletedResponse> deleteSubject(@Path("idSubject") int id);

    @FormUrlEncoded
    @PUT("subject/{idSubject}")
    Call<SubjectModifyResponse> modifySubject(
            @Path("idSubject") int id,
            @Field("subject_name") String subject_name,
            @Field("date") String date,
            @Field("color") int color,
            @Field("iconId") int iconId,
            @Field("makeEvent") boolean makeEvent);

    @FormUrlEncoded
    @POST("topic/{idSubject}")
    Call<TopicAddResponse> addTopic(
            @Path("idSubject") int subjectId,
            @Field("name") String name,
            @Field("isTask") boolean isTask,
            @Field("state") int state,
            @Field("priority") int priority);

    @DELETE("topic/{idTopic}")
    Call<TopicDeletedResponse> deleteTopic(@Path("idTopic") int id);

    @FormUrlEncoded
    @PUT("topic/{idTopic}")
    Call<TopicModifyResponse> modifyTopic(
            @Path("idTopic") int idTopic,
            @Field("name") String name,
            @Field("isTask") boolean isTask,
            @Field("state") int state,
            @Field("priority") int priority,
            @Field("notes") String notes);

    @GET("topic/{idTopic}")
    Call<TopicListResponse> getTopicList(@Path("idTopic") int id);

    @GET("resend")
    Call<EmailResendResponse> resendEmail();

    @FormUrlEncoded
    @POST("verify")
    Call<EmailVerifyCodeResponse> validateCode(
            @Field("verification_code") String validationCode);


    @GET("forgotpassword/{email}")
    Call<UserUpdatedPassword> resetPass(
            @Path("email") String email);

    @FormUrlEncoded
    @POST("forgotpassword/")
    Call<UserUpdatedPassword> updatePass(
            @Field("verification_code") String verifyCode,
            @Field("password") String password);

    @GET("event/{date}")
    Call<DayEventsListResponse> getDateEvents(@Path("date") String date);

    @GET("event/")
    Call<DayEventsListResponse> getAllUserEvents();

    @GET("event/today/notifications")
    Call<DayEventsListResponse> getNotifications();

    @FormUrlEncoded
    @POST("event")
    Call<EventAddResponse> addEvent(
            @Field("event_name") String event_name,
            @Field("event_resume") String event_resume,
            @Field("event_date") String event_date,
            @Field("idSubject") int idSubject,
            @Field("event_color") int event_color,
            @Field("event_iconId") int event_iconId,
            @Field("appnotification") boolean appnotification,
            @Field("event_notes") String event_notes);

    @FormUrlEncoded
    @PUT("event/{eventId}")
    Call<EventUpdateResponse> updateEvent(
            @Path("eventId") int eventId,
            @Field("event_name") String event_name,
            @Field("event_resume") String event_resume,
            @Field("event_date") String event_date,
            @Field("idSubject") int idSubject,
            @Field("event_color") int event_color,
            @Field("event_iconId") int event_iconId,
            @Field("appnotification") boolean appnotification,
            @Field("event_notes") String event_notes);


    @DELETE("event/{eventId}")
    Call<EventDeleteResponse> deleteEvent(
            @Path("eventId") int eventId);
}


