package ca.uqam.info.mgl7460.boutique.domain;

import netscape.javascript.JSObject;

/**
 * cette classe/interface a pour rôle d'appeler
 * les bons constructeurs pour les objets pour que
 * les classes d'implantation ne dépendent pas les unes des 
 * autres.
 * 
 * Donc, toutes les méthodes ont pour liste d'argument
 * le même liste d'arguments qu'un constructeur public de
 * la classe d'implantation.
 * 
 * Les méthodes de création plus complexes/"intelligentes" seront
 * implementées par les classes d'implémentation elles-mêmes
 * qui appeleront les méthodes de la fabrique. 
 * 
 * En particulier, les méthodes de la fabrique ne chercheront pas
 * à initialiser les attributs qui peuvent être modifiés par des méthodes
 * publiques
 * 
 * Par exemple, pour créer une facture à partir d'une commande, on peut
 */
public interface FabriqueBoutique {

    /**
     * Creer un client, avec <code>prenom</code>, <code>nom</code>, et
     * la salutation <code>sal</code>
     * @param prenom
     * @param nom
     * @param sal
     * @return
     */
    public Client creerClient(String prenom, String nom, Salutation sal);

    /**
     * Créer une adresse à partir de données fournies sous la forme d'un
     * objet JSON avec les clés: "numeroPorte", "numeroRue", "nomRue", "ville",
     * "codePostal", et "province"
     * @param jsonObject
     * @return
     */
    public Adresse creerAdresse(JSObject jsonObject);

    /**
     * créer un panier vide pour le client <code>cl</code>
     * @param cl
     * @return
     */
    public Panier creerPanierPourClient(Client cl);

    /**
     * Créer une commande vide pour un client
     * @param client
     * @return
     */
    public Commande creerCommande(Client client);

    /**
     * creer un produit ayant code produit <code>code</code>, la
     * <code>description</code>, et la quantité <code>inventaire</code>
     * en inventaire
     * @param code
     * @param description
     * @param inventaire
     * @return
     */
    public Produit creerProduit(String code, String description, int inventaire, float prixUnitaire);

    /**
     * cette méthode crée un paiement pour <code>facture</code>, effectué par
     * <code>payeur</code>, pour un <code>montant</code>
     * @param facture
     * @param payeur
     * @param montant
     * @return
     */
    public Paiement creerPaiement(Facture facture, Client payeur, float montant);

    /**
     * créer un object livraison vide pour une commande <code>com</code>
     * à destination du client <code>destinataire</code>
     * @param com
     * @param destinataire
     * @return
     */
    public Livraison creerLivraison(Commande com, Client destinataire);

    /**
     * Creer une facture vide pour une commande <code>com</code>. 
     * Les détails de l'objet facture seront calculés par les 
     * méthodes publiques de facture
     * @param com
     * @return
     */
    public Facture creerFacture(Commande com);

    /**
     * Cette méthode crée un item inventaire à partir d'un <code>produit</code> et d'un 
     * <code>numeroInventaire</code>. Chaque création d'item inventaire diminue l'inventaire du 
     * produit en question de 1. Si l'inventaire du produit est
     * épuisé, la méthode lance l'exception InventaireEpuise sans créer l'item
     * @param produit
     * @param numeroInventaire
     * @return
     */
    public ItemInventaire creerItemInventaire(Produit produit, String numeroInventaire) throws InventaireEpuise;

    /**
     * Cette méthode crée une LigneCommande à partir d'un <code>produit</code> et
     * d'un <code>numeroInventaire</code>. Elle est appelée par des méthodes de
     * <code>Panier</code> et de <code>Commande</code>
     * @param produit
     * @param quantite
     * @return
     */
    public LigneCommande creerLigneCommande(Produit produit, int quantite);

}
