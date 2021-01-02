package coderdanslavraievie.methodepardefaut.prep;

import java.math.BigDecimal;

public class UseCase1 {

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
        public boolean estDifferer() {
            return false;
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

        @Override
        public int version() {
            return 1;
        }
    }


    /**
     * ==================================================================
     */

    /**
     * Notre Librarie fournit une abstraction pour effectuer une opération sur un moyen de paiement
     */
    static class MoyenPaiementService {

        public static void preleverSurLeMoyenDePaiement(BigDecimal montant, MoyenPaiement moyenPaiement){
            System.out.println(String.format(
                    "Paiement de %s avec %s ayant la clé de connextion : %s via la version %s " +
                    montant, moyenPaiement.fournisseur(), moyenPaiement.cle(), moyenPaiement.version())
            );
        }

        public static void preleverSurLeMoyenDePaiementEnDifferer(BigDecimal montant, MoyenPaiement moyenPaiement){
            if(!moyenPaiement.estDifferer()) throw new UnsupportedOperationException();
            System.out.println(String.format(
                    "Paiement en differer de %s avec %s ayant la clé de connextion : %s via la version %s " +
                     montant, moyenPaiement.fournisseur(), moyenPaiement.cle(), moyenPaiement.version())
            );
        }
    }

    interface MoyenPaiement {
        String cle();
        String fournisseur();

        /**
         * Besoin 1 : Permettre de s'interfacer avec le fournisseur de ModePaiement via une verson de son API
         */
        default int version(){
            return 0;
        }

        /**
         * Besoin 2 : Faire des paiements differer
         */
        default boolean estDifferer(){
            throw new UnsupportedOperationException("Paiement differ non supporter");
        }

        /**
         * Besoin 3 : Retour d'information sur le mode de Paiement
         */
        default String info(){
            return String.format("InforModePaiement [%s / %s / %s / %s]", cle(), fournisseur(), version(), estDifferer());
        }
    }

}
