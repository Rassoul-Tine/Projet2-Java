package ca.uqam.info.mgl7460.boutique.implementation;

import java.util.Calendar;

import ca.uqam.info.mgl7460.boutique.domain.Client;
import ca.uqam.info.mgl7460.boutique.domain.Facture;
import ca.uqam.info.mgl7460.boutique.domain.Paiement;

public class PaiementImpl implements Paiement {

    private final Client payeur;
    private final Facture facture;
    private final float montant;
    private final Calendar datePaiement;

    public PaiementImpl(Client payeur, Facture facture, float montant) {
        this.payeur = payeur;
        this.facture = facture;
        this.montant = montant;
        this.datePaiement = Calendar.getInstance();
    }

    @Override
    public Client getPayeur() {
        return this.payeur;
    }

    @Override
    public Facture getFacture() {
        return this.facture;
    }

    @Override
    public float getMontant() {
        return this.montant;
    }

    @Override
    public Calendar getDatePaiement() {
        return this.datePaiement;
    }
}
