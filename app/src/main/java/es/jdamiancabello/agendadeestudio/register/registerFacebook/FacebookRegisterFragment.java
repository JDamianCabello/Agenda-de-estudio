package es.jdamiancabello.agendadeestudio.register.registerFacebook;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;
import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.register.RegisterContract;


public class FacebookRegisterFragment extends Fragment implements RegisterContract.View {
    public static final String TAG = "FacebookRegisterFragment";

    private RegisterContract.Presenter presenter;

    private CircleImageView circleImageView;
    private TextInputEditText edUsername, edEmail, edPsw, edConfirmPsw;
    private Button loginButton;

    private OnFragmentInteractionListener mListener;


    public static FacebookRegisterFragment newInstance(Bundle bundle) {
        FacebookRegisterFragment fragment = new FacebookRegisterFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    //https://www.youtube.com/watch?v=q0jAFmB-wkU

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_facebook_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edEmail = view.findViewById(R.id.fbLogin_edtEmail);
        edUsername = view.findViewById(R.id.fbLogin_edtUsername);
        edPsw = view.findViewById(R.id.fbLogin_edtpsw);
        edConfirmPsw = view.findViewById(R.id.fbLogin_edtConfirmPsw);

        circleImageView = view.findViewById(R.id.fbLogin_profile_image);
        loginButton = view.findViewById(R.id.fbLogin_registerBt);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(edEmail.getText().toString(),edUsername.getText().toString(),edPsw.getText().toString(),edConfirmPsw.getText().toString());
            }
        });


        Bundle bundle = getArguments();
        edUsername.setText(bundle.getString("username"));
        edEmail.setEnabled(false);
        edEmail.setText(bundle.getString("email"));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setDuplicateEmailError() {

    }

    @Override
    public void setNotValidEmailError() {

    }

    @Override
    public void setVoidEmailError() {

    }

    @Override
    public void setVoidUsernameError() {

    }

    @Override
    public void setNotValidUsernameError() {

    }

    @Override
    public void setVoidPasswordError() {

    }

    @Override
    public void setNotValidPasswordError() {

    }

    @Override
    public void setVoidConfirmPasswordError() {

    }

    @Override
    public void clearDuplicateEmailError() {

    }

    @Override
    public void clearNotValidEmailError() {

    }

    @Override
    public void clearVoidEmailError() {

    }

    @Override
    public void clearVoidUsernameError() {

    }

    @Override
    public void clearNotValidUsernameError() {

    }

    @Override
    public void clearVoidPasswordError() {

    }

    @Override
    public void clearNotValidPasswordError() {

    }

    @Override
    public void clearVoidConfirmPasswordError() {

    }

    @Override
    public void onSucess() {
        mListener.onDoneFbRegister();
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {

    }


    public interface OnFragmentInteractionListener {
        void onDoneFbRegister();
    }
}
