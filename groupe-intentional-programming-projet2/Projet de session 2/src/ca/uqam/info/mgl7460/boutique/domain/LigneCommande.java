package ca.uqam.info.mgl7460.boutique.domain;

public interface LigneCommande {

    /**
     * retourne produit
    */
    public Produit getProduit();

    /**
     * retourne la quantité du produit
     * @return
     */
    public int getQuantite();

    /**
     * modifie la quantité du produit
     * @param quantite
     */
    public void modifierQuantite(int quantite);

    /**
     * retourne le prix unitaire du produit, quelle que
     * soit sa provenance (catalogue, réduction, etc.)
     * @return
     */
    public float getPrixUnitaire();

    /**
     * modifie le prix unitaire du produit
     * @param prix
     */
    public void setPrixUnitaire(float prix);
}