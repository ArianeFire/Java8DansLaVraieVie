package coderdanslavraievie.methodepardefaut.making;

import java.math.BigDecimal;

public class MethodeParDefautDansLaVraieVie {

    public static void main(String... args){
        MoyenPaiementService.preleverSurLeMoyenDePaiement(BigDecimal.valueOf(1000), new MoyenPaiementPaypal());

        MoyenPaiementService.preleverSurLeMoyenDePaiement(BigDecimal.valueOf(1000), new PaiementPaiementStripe());
    }



    /**
     * Client 1
     */

    static class MoyenPaiementPaypal implements MoyenPaiement {

        @Override
        public String cle() {
            return "CLE_API_PAYAL";
        }

        @Override
        public String fournisseur() {
            return "PAYPAL";
        }

        @Override
        public int version() {
            return 2;
        }

        @Override
        public boolean estDifferer() {
            return true;
        }
    }

    /**
     * Client 2
     */
    static class PaiementPaiementStripe implements MoyenPaiement {

        @Override
        public String cle() {
            return "CLE_API_STRIPE";
        }

        @Override
        public String fournisseur() {
            return "STRIPE";
        }
    }


    /**
     * Librairie (V4)
     */
    static class MoyenPaiementService {

        public static void preleverSurLeMoyenDePaiement(BigDecimal montant, MoyenPaiement moyenPaiement){
            System.out.println(String.format(
                    "Paiement de %s avec %s ayant la clé de connextion : %s avec version  : %s",
                            montant, moyenPaiement.fournisseur(), moyenPaiement.cle(), moyenPaiement.version())
            );
        }

        public static void preleverEnDiffererSurLeMoyenDePaiement(BigDecimal montant, MoyenPaiement moyenPaiement){
            if(!moyenPaiement.estDifferer()) throw new UnsupportedOperationException();
            System.out.println(String.format(
                    "Paiement de %s avec %s ayant la clé de connextion : %s avec version  : %s",
                    montant, moyenPaiement.fournisseur(), moyenPaiement.cle(), moyenPaiement.version())
            );
        }
    }

    interface MoyenPaiementDifferer extends MoyenPaiement{
        // Methode
    }

    interface MoyenPaiement { // MoyenPaiement_V2
        String cle();
        String fournisseur();

        /**
         * Retro-Compatibilite
         */
        default int version(){
            return 1;
        }

        /**
         * Optionnelle
         */
        default boolean estDifferer(){
            throw new UnsupportedOperationException();
        }

        /**
         * Commode
         */
        default String info(){
            return String.format("Info [ Cle : %s, Forunisseur  : %s] ", cle(), fournisseur());
        }
    }

}
