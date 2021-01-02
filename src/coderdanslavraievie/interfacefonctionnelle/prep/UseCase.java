package coderdanslavraievie.interfacefonctionnelle.prep;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class UseCase {

    public static void main(String... args){

        /**
         * Reference de méthode
         */
        print("Nombre Invalide", UseCase::jsonFormat);

        /**
         * Reference de Constructeur
         */
        print("Hello world", String::new);

        /**
         * Version du Formatter avec les methodes predefinie sur Java.util.function
         */
        printWithFunction("Hello world from function", UseCase::jsonFormat);

        printWithUnaryFunction("Hello world from UnaryFunction", UseCase::jsonFormat);
    }

    /**
     * Json formatter : Respectant la signature du Formatter
     */
    private static String jsonFormat(String messageBrut){
        return String.format("{ 'message' : '%s' }", messageBrut);
    }


    /**
     *  Utilitaire Abstrait de formattage de message
     */
    static void print(String messageBrut, MessageFormater formater){
        System.out.println(String.format("Le Brut '%s' Formatter est = '%s'", messageBrut, formater.format(messageBrut)));
    }


    /**
     *  Note Interface fonctionnelle
     */
    interface MessageFormater {
        String format(String message);
    }


    /**
     * =================>>>> Cas d'utilisation avec usage Interface fonctionnelle Predefinie
     *
     * Quelle interface fonctionnelle repondrait bien à la signature de note IF "MessageFormater" ?
     *
     *      ==> Function<String, String> ou UnaryFunction
     */

    static void printWithFunction(String messageBrut, Function<String, String> formatter){
        System.out.println(String.format("Le Brut '%s' Formatter est = '%s'", messageBrut, formatter.apply(messageBrut)));
    }

    static void printWithUnaryFunction(String messageBrut,  UnaryOperator<String> formatter){
        System.out.println(String.format("Le Brut '%s' Formatter est = '%s'", messageBrut, formatter.apply(messageBrut)));
    }

}
