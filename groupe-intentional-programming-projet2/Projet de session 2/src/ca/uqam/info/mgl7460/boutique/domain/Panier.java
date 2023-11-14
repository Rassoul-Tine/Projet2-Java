package ca.uqam.info.mgl7460.boutique.domain;

import java.util.Calendar;
import java.util.Iterator;

public interface Panier {

    /**
     * retourne le client propriétaire de ce panier
     * @return
     */
    public Client getClient();

    /**
     * retourne la date de création du panier
     */
    public Calendar getDate();

    /**
     * Ajouter produit <code>prod</code> au panier. Si le 
     * produit était déjà dans le panier, on incrémente la quantité
     * commandée
     * @param prod
     */
    public void ajouterProduit(Produit prod);

    /**
     * cette méthode enlève produit <code>prod</code> du panier
     * @param prod
     */
    public void enleverProduit(Produit prod);

    /**
     * La méthode permet de modifier la <code>quantite</code> du produit
     * <code>prod</code> qui est dans le panier. Je suppose que le 
     * produit est déjà inclus dans le panier, car autrement, le champ
     * pour la quantité n'apparaitrait même pas.
     * @param prod
     * @param quantite
     */
    public void modifierQuantiteProduit(Produit prod, int quantite);

    /**
     * la méthode incrémente la quantité du produit dans le panier
     * de 1. Je suppose que le produit a déjà été ajouter au panier, car
     * autrement, le bouton pour incrémenter ou décrémenter n'apparaitrait
     * même pas
     * @param prod
     * @return
     */
    public int incrementerQuantiteProduit(Produit prod);

    /**
     * la méthode décrémente la quantité du produit dans le panier
     * de 1. Je suppose que le produit a déjà été ajouter au panier, car
     * autrement, le bouton pour incrémenter ou décrémenter n'apparaitrait
     * même pas
     * @param prod
     * @return
     */
    public int decrementerQuantiteProduit(Produit prod);

    /**
     * Retourner la quantité du produit <code>prod</code> dans
     * le panier. Si <code>prod</code> n'est même pas dans le panier,
     * retourner -1.
     * 
     * Noter que je peux avoir un produit dans le panier avec quantité
     * zéro. Je peux décider de modifier la quantité plus tard pour la remonter
     * @param prod
     * @return
     */
    public int getQuantiteProduit(Produit prod);

    /**
     * calcule la valeur du panier, hors taxes, selon les
     * produits inclus, et les prix de liste des produits
     * en question.
     * @return
     */
    public float getValeurPanier();

    /**
     * cette méthode crée une commande à partir du contenu du 
     * panier, et retourne la commande en question. Cette méthode
     * doit s'occuper de lier la commande au client, et 
     * réciproquement.
     * @return
     */
    public Commande creerCommande();

    /**
     * ré-initialiser le panier à un panier vide
     * 
     */
    public void reinitialiser();

    /**
     * retourne le contenu du panier sous forme d'un itérateur
     * de <code>LigneCommande</code>
     * @return
     */
    public Iterator<LigneCommande> getLignesCommande();

}