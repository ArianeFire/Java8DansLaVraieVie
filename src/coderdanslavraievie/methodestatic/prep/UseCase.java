package coderdanslavraievie.methodestatic.prep;

import java.util.Arrays;
import java.util.stream.Stream;

public class UseCase {

    public static void main(String... args){
        NombreControlleurFactory.LISTE_DE_PAIRE.controllerEtAfficherLesMessage(1, 2, 3);
        NombreControlleurFactory.LISTE_DE_PAIRE.controllerEtAfficherLesMessage(2, 4, 6);
        NombreControlleurFactory.LISTE_IMPAIRE.controllerEtAfficherLesMessage(3, 5, 7, 40);
        NombreControlleurFactory.LISTE_IMPAIRE.controllerEtAfficherLesMessage(3, 5, 7, 9);
    }


    enum NombreControlleurFactory implements Controlleur<Number>{

        LISTE_DE_PAIRE {
            @Override
            public boolean controller(Number... nombres) {
                return Stream.of(nombres).allMatch(unNombre -> unNombre.doubleValue() % 2 == 0);
            }

            @Override
            public String message(Number... nombres) {
                return Controlleur.formatterPourResultatKO("LISTE_DE_PAIRE", nombres);
            }
        },

        LISTE_IMPAIRE {
            @Override
            public boolean controller(Number... args) {
                return Stream.of(args).noneMatch(unNombre -> unNombre.doubleValue() % 2 == 0);
            }

            @Override
            public String message(Number... args) {
                return Controlleur.formatterPourResultatKO("LISTE_IMPAIRE", args);
            }
        }
    }

    /**
     * Libraire :
     */
    static interface Controlleur<T> {


        static String formatterPourResultatKO(String nomDuControle, Number... entrees){
            return String.format("Resultat du controle '%s' sur les entrées (%s) est K.O",
                    nomDuControle, Arrays.toString(entrees)
            );
        }

        boolean controller(T... args);
        String message(T... args);

        /**
         * Une methode commode
         */
        default void controllerEtAfficherLesMessage(T... args){
            if(!controller(args)){
                System.out.println(message(args));
            }
        }
    }
}
