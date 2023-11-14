package ca.uqam.info.mgl7460.boutique.domain;

import java.util.Iterator;

public interface Client {

    /**
     * type simple interne pour représenter les identifiants d'un.e client.e
     */
    public record Identification (String prenom, String nom, Salutation salutation) {};

    /**
     * retourne l'identifiant
     * @return
     */
    public Identification getIdentification();

    /**
     * retourne l'adresse
     * @return
     */
    public Adresse getAdresse();

    /**
     * modifier l'adresse
     * @param nouvelle
     */
    public void setAdresse(Adresse nouvelle);

    /**
     * crée un panier pour le client.
     * @return
     */
    public Panier creerPanier();

    /**
     * retourne le panier du client, s'il y en a un de crée
     * @return
     */
    public Panier getPanier();

    /**
     * ajoute une commande à la liste de commande du client
     * @param commande
     */
    public void ajouterCommande(Commande commande);

    /**
     * retire <code>commande</code> de la liste de commandes
     * du client
     * @param commande
     */
    public void retirerCommande(Commande commande);

    /**
     * retourne la liste de commandes du client
     * @return
     */
    public Iterator<Commande> getCommandes();

    /**
     * retourne la liste de paiements effectués par le client
     * @return
     */
    public Iterator<Paiement> getPaiements();

    /**
     * Ajoute <code>paiement</code> à la liste de paiements du
     * client
     * @param paiement
     */
    public void ajouterPaiement(Paiement paiement);

    /**
     * retourne la liste de paiements effectués pour la commande
     * passé en paramètre. Les paiements peuvent ne pas avoir été faits
     * par le client lui-même, mais concernent la commande du client
     * (<code>commande</code>) passée en paramètre
     * @param commande
     * @return
     */
    public Iterator<Paiement> getPaiementsPourCommande(Commande commande);

}