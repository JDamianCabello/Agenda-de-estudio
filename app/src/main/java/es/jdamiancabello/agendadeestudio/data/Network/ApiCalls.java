package es.jdamiancabello.agendadeestudio.data.Network;

import es.jdamiancabello.agendadeestudio.data.model.api_model.LoginResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.RegisterResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.email.EmailResendResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.email.EmailVerifyCodeResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectAddResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectDeletedResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectListResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.subject.SubjectModifyResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicAddResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicDeletedResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicListResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.topic.TopicModifyResponse;
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
            @Field("password") String password
            );

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
            @Field("iconId") int iconId);

    @DELETE("subject/{idSubject}")
    Call<SubjectDeletedResponse> deleteSubject(@Path("idSubject") int id);

    @FormUrlEncoded
    @PUT("subject/{idSubject}")
    Call<SubjectModifyResponse> modifySubject(
            @Path("idSubject") int id,
            @Field("subject_name") String subject_name,
            @Field("date") String date,
            @Field("color") int color,
            @Field("iconId") int iconId);

    @FormUrlEncoded
    @POST("topic/")
    Call<TopicAddResponse> addTopic(
            @Field("subject_name") String subject_name,
            @Field("date") String date,
            @Field("color") int color,
            @Field("iconId") int iconId);

    @DELETE("topic/{idTopic}")
    Call<TopicDeletedResponse> deleteTopic(@Path("idTopic") int id);

    @FormUrlEncoded
    @PUT("topic/{idTopic}")
    Call<TopicModifyResponse> modifyTopic(
            @Path("idTopic") int id,
            @Field("subject_name") String subject_name,
            @Field("date") String date,
            @Field("color") int color,
            @Field("iconId") int iconId);

    @GET("topic/{idTopic}")
    Call<TopicListResponse> getTopicList(@Path("idTopic") int id);

    @GET("resend")
    Call<EmailResendResponse> resendEmail();

    @FormUrlEncoded
    @POST("verify")
    Call<EmailVerifyCodeResponse> validateCode(
            @Field("verification_code") String validationCode);
}


