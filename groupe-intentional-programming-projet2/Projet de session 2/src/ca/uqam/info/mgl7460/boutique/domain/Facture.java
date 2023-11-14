package ca.uqam.info.mgl7460.boutique.domain;

import java.util.Iterator;

public interface Facture {

    public static record LigneFacture(Produit produit, float prixUnitaire, float reduction, int quantite, float prixTotal) {};

    /**
     * retourne la commande dont c'est la facture
     * @return
     */
    public Commande getCommande();

    /**
     * le montant de la facture.
     * @return
     */
    public float getMontant();

    /**
     * retourne la balance consistant en le montant de la facture,
     * moins le total des paiements faits sur la facture.
     * @return
     */
    public float getBalance();

    /**
     * inscrire un paiement effectué par <code>payeur</code> pour le montant
     * <code>amount</code>
     * @param amount
     * @param payeur
     * @return
     */
    public Paiement ajouterPaiement(float amount, Client payeur);

    /**
     * Cette méthode retourne les détails de la facture
     * @return
     */
    public Iterator<LigneFacture> getDetailsFacture();


    /**
     * retourne la liste de paiements faits pour cette
     * facture
     * @return
     */
    public Iterator<Paiement> getPaiements();

    public void setReductionGlobale(float reductionGlobale);

    public float getReductionGlobale();

    public void setReductionSurProduit(Produit produit, float reductionProduit);

    public float getReductionSurProduit(Produit produit);

    public float getTotalPaiements();
}