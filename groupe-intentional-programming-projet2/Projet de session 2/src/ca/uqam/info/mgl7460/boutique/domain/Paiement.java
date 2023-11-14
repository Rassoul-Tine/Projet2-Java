package ca.uqam.info.mgl7460.boutique.domain;

import java.util.Calendar;

public interface Paiement {

    /**
     * cette methode retourne le client
     * qui a payé la facture. Ce client peut être
     * différent du client qui a passé la commande
     * correspondante
     * @return
     */
    public Client getPayeur();

    /**
     * cette méthode retourne la facture correspondant à ce
     * paiement
     * @return
     */
    public Facture getFacture();

    /**
     * cette méthode retourne le montant du paiement
     * @return
     */
    public float getMontant();

    /**
     * cette méthode retourne la date du paiement.
     * Cette date correspond à la date de création du paiement
     * @return
     */
    public Calendar getDatePaiement();
}