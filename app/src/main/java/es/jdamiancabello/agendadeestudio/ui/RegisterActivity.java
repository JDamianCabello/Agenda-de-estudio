package es.jdamiancabello.agendadeestudio.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

public class RegisterActivity extends AppCompatActivity {

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

    public RegisterActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_tilUser = findViewById(R.id.register_tilUser);
        register_tilPassword = findViewById(R.id.register_tilPassword);
        register_tilEmail = findViewById(R.id.register_tilEmail);
        register_tilConfirmPassword = findViewById(R.id.register_tilRepeatPassword);


        register_tiedUser = findViewById(R.id.register_tiedUser);
        register_tiedPassword = findViewById(R.id.register_tiedPassword);
        register_tiedEmail = findViewById(R.id.register_tiedEmail);
        register_tiedRepeatPassword = findViewById(R.id.register_tiedConfirmPassword);


        register_tiedUser.addTextChangedListener(new SignUpWatcher(register_tiedUser));
        register_tiedPassword.addTextChangedListener(new SignUpWatcher(register_tiedPassword));
        register_tiedEmail.addTextChangedListener(new SignUpWatcher(register_tiedEmail));
        register_tiedRepeatPassword.addTextChangedListener(new SignUpWatcher(register_tiedRepeatPassword));


        ivRegisterBackArrow = findViewById(R.id.ivRegisterBackArrow);
        ivRegisterBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, WelcomeFragment.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                Animatoo.animateSlideUp(RegisterActivity.this);
            }
        });


        btnRegitsterUser = findViewById(R.id.btnRegitsterUser);
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
                Toast.makeText(RegisterActivity.this, "Ese email ya está en uso", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(RegisterActivity.this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
                //-Se pasa a RegisterActivity
                startActivity(new Intent(RegisterActivity.this, LoginFragment.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                Animatoo.animateFade(RegisterActivity.this);
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
            displaySoftKeyboard(register_tilUser);
            return false;
        }
    }

    /**
     * Este método abre el teclado en caso de que una vista (TextInputEditText) tenga el foco
     */
    private void displaySoftKeyboard(View view){
        if(view.requestFocus()){
            //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view,0);
        }
    }

    private void requestFocus(TextInputLayout textInputLayout) {
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
            displaySoftKeyboard(register_tilPassword);
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
            displaySoftKeyboard(register_tilEmail);
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
