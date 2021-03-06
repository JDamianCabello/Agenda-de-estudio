package es.jdamiancabello.agendadeestudio.data.DAO;


import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.api_model.email.EmailResendResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.email.EmailVerifyCodeResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.user.UserUpdatedPassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailDAO {

    public static void resendEmail(ResendEmail resendEmail) {
        Call<EmailResendResponse> call = ApiRestClientToken
                .getInstance()
                .resendEmail();

        call.enqueue(new Callback<EmailResendResponse>() {
            @Override
            public void onResponse(Call<EmailResendResponse> call, Response<EmailResendResponse> response) {
                if(response.isSuccessful()) {
                    if(!response.body().isError());
                        resendEmail.onSucessResend();
                }
            }

            @Override
            public void onFailure(Call<EmailResendResponse> call, Throwable t) {
            }
        });
    }

    public static void validateCode(ValidateEmail validateEmail, String verificationCode) {
        Call<EmailVerifyCodeResponse> call = ApiRestClientToken
                .getInstance()
                .validateCode(verificationCode);

        call.enqueue(new Callback<EmailVerifyCodeResponse>() {
            @Override
            public void onResponse(Call<EmailVerifyCodeResponse> call, Response<EmailVerifyCodeResponse> response) {
                if(response.isSuccessful()) {
                    if(!response.body().isError())
                        validateEmail.onSuccesValidate();
                    else
                        validateEmail.onFailureValidate();
                }
            }

            @Override
            public void onFailure(Call<EmailVerifyCodeResponse> call, Throwable t) {
            }
        });
    }

    public static void sendResetPassEmail(ResetPassEmail resetPassEmail, String email) {
        Call<UserUpdatedPassword> call = ApiRestClientToken
                .getInstance()
                .resetPass(email);

        call.enqueue(new Callback<UserUpdatedPassword>() {
            @Override
            public void onResponse(Call<UserUpdatedPassword> call, Response<UserUpdatedPassword> response) {
                if(response.isSuccessful()) {
                    if(!response.body().isError())
                        resetPassEmail.onSendResetCode();
                    else
                        resetPassEmail.onFailSendResetCode();
                }
            }

            @Override
            public void onFailure(Call<UserUpdatedPassword> call, Throwable t) {
            }
        });
    }

    public static void updateUserPass(ResetPassEmail resetPassEmail, String password, String verifyCode) {
        Call<UserUpdatedPassword> call = ApiRestClientToken
                .getInstance()
                .updatePass(verifyCode,password);

        call.enqueue(new Callback<UserUpdatedPassword>() {
            @Override
            public void onResponse(Call<UserUpdatedPassword> call, Response<UserUpdatedPassword> response) {
                if(response.isSuccessful()) {
                    if(!response.body().isError())
                        resetPassEmail.onUpdatedPass();
                    else
                        resetPassEmail.onFailUpdatePass();
                }
            }

            @Override
            public void onFailure(Call<UserUpdatedPassword> call, Throwable t) {
            }
        });
    }

    public interface ResendEmail{
        void onSucessResend();
    }

    public interface ResetPassEmail{
        void onSendResetCode();
        void onFailSendResetCode();
        void onUpdatedPass();
        void onFailUpdatePass();
    }

    public interface ValidateEmail{
        void onSuccesValidate();
        void onFailureValidate();
    }
}
