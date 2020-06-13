package es.jdamiancabello.agendadeestudio.utils;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    /**
     * Devuelve un long desde una fecha en formato dd-MM-yyyy
     *
     * @param dateString Es el string que contiene la fecha
     * @return
     */
    public static long dateStringToLong(String dateString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }

    /**
     * Devuelve un long desde una fecha especificando el formato
     *
     * @param dateString Es el string que contiene la fecha
     * @param dateFormat Es el formato de fecha que quiere que devolvamos
     * @return
     */
    public static long dateStringToLong(String dateString, String dateFormat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }

    /**
     * Devuelve un string con el formato fecha dd-MM-yyyy desde un long
     *
     * @param dateLong Es el long que contiene la fecha
     * @return
     */
    public static String dateLongToString(Long dateLong){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateLong);

        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * Devuelve un long desde una fecha especificando el formato
     *
     * @param dateLong Es el long que contiene la fecha
     * @param dateFormat Es el formato de fecha que quiere que devolvamos
     * @return Un string en el formato de dateformat
     */
    public static String dateLongToString(Long dateLong, String dateFormat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateLong);

        return simpleDateFormat.format(calendar.getTime());
    }

    public static Long toFormatDate(Date fecha){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String result = simpleDateFormat.format(fecha);
        try {
            return simpleDateFormat.parse(result).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}