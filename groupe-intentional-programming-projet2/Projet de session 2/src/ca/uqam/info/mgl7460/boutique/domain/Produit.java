package ca.uqam.info.mgl7460.boutique.domain;

public interface Produit {

    /**
     * retourne le code produit
     * @return
     */
    public String getCodeProduit();

    /**
     * retourne la description
     * @return
     */
    public String getDescription();

    /**
     * retourne la quantité en inventaire du produit
     * @return
     */
    public int getInventaire();

    /**
     * retourne le prix unitaire "de liste"
     * @return
     */
    public float getPrixUnitaire();

    /**
     * set le prix unitaire du produit à <code>prix</code>
     * @param prix
     */
    public void setPrixUnitaire(float prix);

    /**
     * Ajoute <code>increment</code> à l'inventaire.
     * La fonction retourne la nouvelle valeur de 
     * l'inventraire
     * @param increment
     * @return
     */
    public int incrementeInventaire(int increment);

    /**
     * Decremente l'inventaire de <code>decrement</code>, et 
     * retourne le nouvel etat de l'inventaire.
     * S'il n'y a pas une quantité suffisante dans l'inventaire, elle
     * lève l'exception InventaireEpuise
     * @param decrement
     * @return
     * @throws InventaireEpuise
     */
    public int decrementeInventaire(int decrement) throws InventaireEpuise;
}