package es.jdamiancabello.agendadeestudio.utils;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.jdamiancabello.agendadeestudio.ui.FocusApplication;

/**
 * Estas clases no se pueden heredar por eso se indica que son final
 */
public final class CommonUtils {

    /**
     * Método que comprueba que el password tiene entre 8 y 12 dígitos, una mayúscula y un número.
     */
    public static boolean patterPassword(String psw){
        final String PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,12}$";

        return psw.matches(PATTERN);
    }

    public static boolean patternEmail(String email){
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    public static boolean patterUser(String user){
        final String PATTERN = ".{6,20}$";

        return user.matches(PATTERN);
    }
}