package es.jdamiancabello.agendadeestudio.ui.verifyemail;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;


public class VerifyEmailFragment extends Fragment implements VerifyEmailContract.View{

    public static final String TAG = "VerifyEmailFragment";
    private OnFragmentInteractions mListener;
    private VerifyEmailContract.Presenter presenter;
    private TextInputEditText tiedVerifyCode;
    private Button btnResendMail, btnVerifyCode, btnGoLogin;
    private TextInputLayout tilVerifyCode;
    private TextView tvHeader;

    public static VerifyEmailFragment newInstance() {
        return new VerifyEmailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_verify_email, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tiedVerifyCode = view.findViewById(R.id.verifyEmail_tied_code);
        btnGoLogin = view.findViewById(R.id.verifyEmail_btn_returnLogin);
        btnResendMail = view.findViewById(R.id.verifyEmail_btn_resendEmail);
        btnVerifyCode = view.findViewById(R.id.verifyEmail_btn_verify);
        tilVerifyCode = view.findViewById(R.id.verifyEmail_til_code);

        btnVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tiedVerifyCode.getText().length() != 8)
                    tilVerifyCode.setError(getResources().getString(R.string.err_verifyCode_notLenght));
                else
                    presenter.onCheckVerification(tiedVerifyCode.getText().toString());

            }
        });

        btnResendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onResendEmail();
            }
        });

        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onReturnLoginView();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnFragmentInteractions) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setPresenter(VerifyEmailContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void onFailVerify() {
        Toast.makeText(getContext(), "Wrong validate code", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSendEmailToast() {
        Toast.makeText(getContext(), "Email was send to " + FocusApplication.getUser().getEmail()+" email", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucessValidate() {
        mListener.onVerified();
    }

    @Override
    public void showChangePassFragment() {
        mListener.onChangePass();
    }

    public interface OnFragmentInteractions{
        void onVerified();
        void onReturnLoginView();
        void onChangePass();
    }
}