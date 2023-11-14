package ca.uqam.info.mgl7460.boutique.domain;

import ca.uqam.info.mgl7460.boutique.implementation.BoutiqueImpl;

public interface Boutique {

    public static Boutique getBoutique() {return BoutiqueImpl.getBoutiqueSingleton();}

    /**
     * Cette méthode retourne une instance de <code>FabriqueBoutique</code>
     * qui sert à créer les objets
     * @return
     */
    public FabriqueBoutique getFabriqueBoutique();

    /**
     * Cette méthode est appelé pour inscrire un client. Elle prend comme arguments
     * les différents champs pour l'identification et l'adresse du client
     * @param prenom
     * @param nom
     * @param salutation
     * @param numeroPorte
     * @param numeroRue
     * @param nomRue
     * @param ville
     * @param codePostal
     * @param province
     * @return
     */
    public Client inscrireClient(String prenom, String nom, Salutation salutation, String numeroPorte, String numeroRue, String nomRue, String ville, String codePostal, Province province);
    
    /**
     * cette méthode simule le démarrage d'une session de 
     * magasinage par un client. Concrêtement, elle crée un panier
     * vide pour le client et le retourne.
     * @param client
     */
    public Panier demarrerSessionClient(Client client);

    /**
     * cette méthode ajoute un produit au panier de magasinage
     * présentement ouvert. Le produit est ajouté au panier avec
     * une quantité initiale de 1.
     * @param produit
     * @param panier
     */
    public void ajouterProduit(Produit produit, Panier panier);

    /**
     * cette méthode retire <code>produit</code> du <code>panier</code>
     * de magasinage présentement ouvert. 
     * @param produit
     * @param panier
     */
    public void retirerProduit(Produit produit, Panier panier);

    /**
     * cette méthode ajoute 1 à la quantité du produit 
     * <code>produit</code> comprise dans le panier. La nouvelle
     * quantité du produit est retournée.
     * @param produit
     * @param panier
     * @return
     */
    public int incrementerQuantiteProduit(Produit produit, Panier panier);

    /**
     * cette méthode retire 1 de la quantité du produit 
     * <code>produit</code> comprise dans le panier. La nouvelle
     * quantité du produit est retournée.
     * @param produit
     * @param panier
     * @return
     */
    public int decrementerQuantiteProduit(Produit produit, Panier panier);

    /**
     * Cette méthode prend le contenu actuel du panier, et crée une commande 
     * correspondante pour les produits, et les quantités correspondantes, que
     * l'on trouve dans le panier.
     * 
     * Elle correspond plus ou moins à ce que l'on fait lorsqu'on fait un
     * checkout dans un site de magasinage en ligne.
     * @param panier
     * @return
     */
    public Commande commander(Panier panier);

    /**
     * cette méthode crée une facture à partir de la commande et 
     * la retourne.
     * @param commande
     * @return
     */
    public Facture creerFacturePourCommande(Commande commande);

    /**
     * cette méthode accorde une <code>reduction</code> globale pour
     * la <code>facture</code>. Elle retourne le nouveau montant de la facture
     * @param facture
     * @param reduction
     * @return
     */
    public float accorderReductionPourFacture(Facture facture, float reduction);

    /**
     * cette méthode accorde une <code>reduction</code> pour <code>produit</code>
     * dans la <code>facture</code>. Elle retourne le nouveau montant de la facture
     * mise à jour
     * @param facture
     * @param produit
     * @param reduction
     * @return
     */
    public float accorderReductionPourProduitDansFacture(Facture facture, Produit produit, float reduction);

    /**
     * cette méthode effectue un paiement pour <code>montant</code>
     * @param facture
     * @param client
     * @return
     */
    public Paiement payerFacture(Facture facture, Client client, float montant);

}