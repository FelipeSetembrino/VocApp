package app.gramatic.gramaticapp.Controle;

/**
 * Created by felip on 11/10/2018.
 */

/**
 * @author Felipe Carvalho
 */
public class MetodosUtilidades {

    public static boolean checkNull(String ... valVerifica){
        for (String aux:valVerifica) {
            if (aux == null || aux.trim().equals("") || aux.trim().equals("null")){
                return true;
            }
        }
        return false;
    }

}
