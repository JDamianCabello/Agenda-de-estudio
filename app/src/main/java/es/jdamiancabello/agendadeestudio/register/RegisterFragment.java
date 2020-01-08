package es.jdamiancabello.agendadeestudio.register;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import es.jdamiancabello.agendadeestudio.R;


public class RegisterFragment extends Fragment implements RegisterContract.View {
    public static final String TAG = "RegisterFragment";

    private RegisterContract.Presenter presenter;

    private CircleImageView circleImageView;
    private TextInputEditText edUsername, edEmail, edPsw, edConfirmPsw;
    private TextInputLayout tilUsername, tilEmail,tilPsw, tilConfirmPsw;
    private Button loginButton;
    private ImageView backstack;

    private OnFragmentInteractionListener mListener;


    public static RegisterFragment newInstance(Bundle bundle) {
        RegisterFragment fragment = new RegisterFragment();
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
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edEmail = view.findViewById(R.id.login_edtEmail);
        edUsername = view.findViewById(R.id.login_edtUsername);
        edPsw = view.findViewById(R.id.login_edtpsw);
        edConfirmPsw = view.findViewById(R.id.login_edtConfirmPsw);

        tilUsername = view.findViewById(R.id.login_tilUsername);
        tilEmail = view.findViewById(R.id.login_tilEmail);
        tilPsw = view.findViewById(R.id.login_tilPsw);
        tilConfirmPsw = view.findViewById(R.id.login_tilConfirmPsw);

        circleImageView = view.findViewById(R.id.login_profile_image);
        loginButton = view.findViewById(R.id.login_registerBt);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(edEmail.getText().toString(),edUsername.getText().toString(),edPsw.getText().toString(),edConfirmPsw.getText().toString());
            }
        });

        backstack = view.findViewById(R.id.login_ivBack);
        backstack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });


        if(getArguments() != null) {
            Bundle bundle = getArguments();
            edUsername.setText(bundle.getString("username"));
            edEmail.setEnabled(false);
            edEmail.setText(bundle.getString("email"));
        }
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
        new AlertDialog.Builder(getContext())
                .setMessage(getString(R.string.errDuplicateEmail))
                .setIcon(R.drawable.ic_warning)
                .setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(getString(R.string.login_recoverPassword), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"NOT IMPLEMENTED NOW",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    @Override
    public void setNotValidEmailError() {
        tilEmail.setError(getString(R.string.errEmail));
    }

    @Override
    public void setVoidEmailError() {
        tilEmail.setError(getString(R.string.errVoidField));
    }

    @Override
    public void setVoidUsernameError() {
        tilUsername.setError(getString(R.string.errVoidField));
    }

    @Override
    public void setNotValidUsernameError() {
        tilUsername.setError(getString(R.string.errUserEmpty));
    }

    @Override
    public void setVoidPasswordError() {
        tilPsw.setError(getString(R.string.errVoidField));
    }

    @Override
    public void setNotValidPasswordError() {
        tilPsw.setError(getString(R.string.errPassword));
    }

    @Override
    public void setVoidConfirmPasswordError() {
        tilConfirmPsw.setError(getString(R.string.errVoidField));
    }

    @Override
    public void setNoMachPasswordError() {
        tilConfirmPsw.setError(getString(R.string.errNotMatchPass));
    }

    @Override
    public void clearDuplicateEmailError() {
    }

    @Override
    public void clearNotValidEmailError() {
        tilEmail.setError(null);
    }

    @Override
    public void clearVoidEmailError() {
        tilEmail.setError(null);
    }

    @Override
    public void clearVoidUsernameError() {
        tilUsername.setError(null);
    }

    @Override
    public void clearNotValidUsernameError() {
        tilUsername.setError(null);
    }

    @Override
    public void clearVoidPasswordError() {
        tilPsw.setError(null);
    }

    @Override
    public void clearNotValidPasswordError() {
        tilPsw.setError(null);
    }

    @Override
    public void clearVoidConfirmPasswordError() {
        tilConfirmPsw.setError(null);
    }

    @Override
    public void clearNoMachPasswordError() {
        tilConfirmPsw.setError(null);
    }

    @Override
    public void onSucess() {
        mListener.onDoneRegister();
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {

    }


    public interface OnFragmentInteractionListener {
        void onDoneRegister();
    }
}
