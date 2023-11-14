package ca.uqam.info.mgl7460.boutique.domain;

public interface ItemInventaire {

    /**
     * retourne le produit dont le receveur est un exemplaire
     * @return
     */
    public Produit getProduit();

    /**
     * retourne le numéro d'inventaire de l'item
     * @return
     */
    public String getNumeroInventaire();

    /**
     * retourne la condition de l'item
     * @return
     */
    public Condition getCondition();

    /**
     * Modifie la condition de l'item
     * @param cond
     */
    public void setCondition(Condition cond);
}