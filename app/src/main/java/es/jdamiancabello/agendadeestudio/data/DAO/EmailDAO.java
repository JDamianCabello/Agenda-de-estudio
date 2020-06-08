package es.jdamiancabello.agendadeestudio.data.DAO;

import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClient;
import es.jdamiancabello.agendadeestudio.data.Network.ApiRestClientToken;
import es.jdamiancabello.agendadeestudio.data.model.api_model.email.EmailResendResponse;
import es.jdamiancabello.agendadeestudio.data.model.api_model.email.EmailVerifyCodeResponse;
import es.jdamiancabello.agendadeestudio.data.repository.EmailRepository;
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

    public static void validateCode(EmailRepository emailRepository, String verificationCode) {
        Call<EmailVerifyCodeResponse> call = ApiRestClientToken
                .getInstance()
                .validateCode(verificationCode);

        call.enqueue(new Callback<EmailVerifyCodeResponse>() {
            @Override
            public void onResponse(Call<EmailVerifyCodeResponse> call, Response<EmailVerifyCodeResponse> response) {
                if(response.isSuccessful()) {
                    if(!response.body().isError())
                        emailRepository.onSuccesValidate();
                    else
                        emailRepository.onFailureValidate();
                }
            }

            @Override
            public void onFailure(Call<EmailVerifyCodeResponse> call, Throwable t) {
            }
        });
    }

    public interface ResendEmail{
        void onSucessResend();
    }

    public interface ValidateEmail{
        void onSuccesValidate();
        void onFailureValidate();
    }
}
