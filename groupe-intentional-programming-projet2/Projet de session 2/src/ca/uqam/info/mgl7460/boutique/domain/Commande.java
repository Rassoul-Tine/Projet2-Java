package ca.uqam.info.mgl7460.boutique.domain;

import java.util.Calendar;
import java.util.Iterator;

public interface Commande {

    /**
     * retourne le client qui a passé la commande
     * @return
     */
    public Client getClient();

    /**
     * retourne la date de création de la commande
     * @return
     */
    public Calendar getDateCommande();

    /**
     * retourne le numéro de la commande
     * @return
     */
    public String getNumeroCommande();

    /**
     * cette methode retourne l'adresse de liovraison de la
     * copmmande. Elle est initialement initialisé à l'adresse
     * du client, mais elle peut être modifiée par la suite
     * @return
     */
    public Adresse getAdresseDeLivraison();

    /**
     * affecte l'adresse de livraison de la commande.
     * @param adresse
     */
    public void setAdresseDeLivraison(Adresse adresse);

    /**
     * retourne les lignes commandes.
     * @return
     */
    public Iterator<LigneCommande> getLignesCommandes();

    /**
     * retourne la quantité commandée pour <code>produit</code>
     * @param produit
     * @return
     */
    public int getQuantiteCommandee(Produit produit);

    /**
     * ajoute une ligne commande pour <code>produit</code> et
     * <code>quantite</code>
     * @param produit
     * @param quantite
     */
    public void ajouteLigneCommande(Produit produit, int quantite);


    /**
     * retourne la liste de livraisons effectuées pour la commande
     * @return
     */
    public Iterator<Livraison> getLivraisons();

    /**
     * Créer une livraison pour la commande. L'objet livraison est
     * initialement vide, mais prêt à recevoir des items inventaires
     * des bons produits
     * @return
     */
    public Livraison creerLivraison();

    /**
     * cette méthode génère une facture pour la commande 
     * @return
     */
    public Facture creerFacture();

    /**
     * cette méthode retourne la facture de la commande. Si elle n'a pas
     * encore été créée, elle le sera.
     * @return
     */
    public Facture getFacture();

    /**
     * cette méthode initialise la commande avec le contenu d'un panier
     * @param panier
     */
    public void initialiserAvecPanier(Panier panier);
}