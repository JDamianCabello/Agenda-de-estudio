package es.jdamiancabello.agendadeestudio.data.Network;

import es.jdamiancabello.agendadeestudio.data.model.api_model.LoginResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.RegisterResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.SubjectAddResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.SubjectListResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
            @Field("estate_priority") int estate_priority
    );



}
