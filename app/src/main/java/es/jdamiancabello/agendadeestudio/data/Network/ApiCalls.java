package es.jdamiancabello.agendadeestudio.data.Network;

import es.jdamiancabello.agendadeestudio.data.model.api_model.LoginResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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



}
