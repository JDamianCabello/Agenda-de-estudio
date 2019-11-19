package es.jdamiancabello.agendadeestudio.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import es.jdamiancabello.agendadeestudio.data.repository.UserRepository;
import es.jdamiancabello.agendadeestudio.utils.CommonUtils;
import es.jdamiancabello.agendadeestudio.R;

public class RegisterFragment extends Fragment {

    private ImageView ivRegisterBackArrow;

    private Button btnRegitsterUser;

    private TextInputEditText register_tiedUser;
    private TextInputEditText register_tiedPassword;
    private TextInputEditText register_tiedRepeatPassword;
    private TextInputEditText register_tiedEmail;

    private TextInputLayout register_tilUser;
    private TextInputLayout register_tilPassword;
    private TextInputLayout register_tilConfirmPassword;
    private TextInputLayout register_tilEmail;

    public final static String TAG = "REGISTERFRAGMENT";

    private Fragment loginFragment;

    public static Fragment newInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_register,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        register_tilUser = view.findViewById(R.id.register_tilUser);
        register_tilPassword = view.findViewById(R.id.register_tilPassword);
        register_tilEmail = view.findViewById(R.id.register_tilEmail);
        register_tilConfirmPassword = view.findViewById(R.id.register_tilRepeatPassword);


        register_tiedUser = view.findViewById(R.id.register_tiedUser);
        register_tiedPassword = view.findViewById(R.id.register_tiedPassword);
        register_tiedEmail = view.findViewById(R.id.register_tiedEmail);
        register_tiedRepeatPassword = view.findViewById(R.id.register_tiedConfirmPassword);


        register_tiedUser.addTextChangedListener(new SignUpWatcher(register_tiedUser));
        register_tiedPassword.addTextChangedListener(new SignUpWatcher(register_tiedPassword));
        register_tiedEmail.addTextChangedListener(new SignUpWatcher(register_tiedEmail));
        register_tiedRepeatPassword.addTextChangedListener(new SignUpWatcher(register_tiedRepeatPassword));


        ivRegisterBackArrow = view.findViewById(R.id.ivRegisterBackArrow);
        ivRegisterBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFragment = LoginFragment.newInstance();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(LoginFragment.TAG);
                fragmentTransaction.commit();
            }
        });


        btnRegitsterUser = view.findViewById(R.id.btnRegitsterUser);
        btnRegitsterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }


    /*
        Método que comprueba la valided de todos los campos de TextImputLayout
         */
    private void validate() {
        if (validateUser(register_tiedUser.getText().toString()) & validatePassword(register_tiedPassword.getText().toString()) & validateEmail(register_tiedEmail.getText().toString())) {
            //1.-Se guarda el usuario en la base de datos
            if(!UserRepository.getInstance().userAdd(register_tiedUser.getText().toString(),register_tiedEmail.getText().toString(),register_tiedPassword.getText().toString())) {
                Toast.makeText(getContext(), "Ese email ya está en uso", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(), "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
                //-Se pasa a RegisterFragment
                loginFragment = LoginFragment.newInstance();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(LoginFragment.TAG);
                fragmentTransaction.commit();
            }
        }
    }


    /**
     * Valida un usuario
     *
     * @param user
     * @return
     */
    private boolean validateUser(String user) {
        if (CommonUtils.patterUser(user)) {
            register_tilUser.setError(null);
            return true;
        }
        else {
            register_tilUser.setError(getString(R.string.errUserEmpty));
            showSoftKeyboard(register_tilUser);
            return false;
        }
    }

    /**
     * Este método abre el teclado en caso de que una vista (TextInputEditText) tenga el foco
     */
    private void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(FragmentActivity.INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    /**
     * Comprueba la contraseña:
     *      -tenga entre 8-12 de longitud
     *      -tenga un número
     *      -tenga una mayúscula
     *      -las contraseñas son iguales
     *      -no puede ser nulo (arreglado don el primer control)
     * @return
     * @param psw la contraseña a validar
     */
    private boolean validatePassword(String psw) {

        if( !CommonUtils.patterPassword(psw)) {
            register_tilPassword.setError(getString(R.string.errPassword));
            showSoftKeyboard(register_tilPassword);
            return false;
        }
        else{
            register_tilPassword.setError(null);
            return true;
        }
    }

    /**
     * Comprueba que lo introducido sea un correo válido además de:
     *      -no esté vacío
     * @return
     * @param s El string que contiene el e-mail
     */
    private boolean validateEmail(String s) {
        if( CommonUtils.patternEmail(s)) {
            register_tilEmail.setError(null);
            return true;
        }
        else{
            register_tilEmail.setError(getString(R.string.errEmail));
            showSoftKeyboard(register_tilEmail);
            return false;
        }
    }

    class SignUpWatcher implements TextWatcher {
        private View view;

        private SignUpWatcher(View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.register_tiedUser:
                    validateUser(((TextInputEditText)view).getText().toString());
                    break;
                case R.id.register_tiedPassword:
                    validatePassword(((TextInputEditText)view).getText().toString());
                    break;
                case R.id.register_tiedEmail:
                    validateEmail(((TextInputEditText)view).getText().toString());
                    break;
            }
        }
    }
}
