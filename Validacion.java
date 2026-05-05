import java.util.regex.Pattern;

public class Validacion {
    
    private static final Pattern DNI_PATTERN = Pattern.compile("[0-9]{8}[A-Za-z]");


    public static boolean ValidandoDNI(String dni) {
        if (dni == null || dni.length() == 0)
            return false;
        if (!DNI_PATTERN.matcher(dni).matches())
            return false;
        return true;
    }

    public static boolean ValidandoEdad(int edad) {
        if (edad >= 0 && edad <= 120)
            return true;
        return false;
    }

}
