package ca.uqam.info.mgl7460.boutique.domain;

import java.util.Calendar;
import java.util.Iterator;

/**
 * 
 */
public interface Livraison {

    public enum StatutLivraison {EN_PREPARATION,EN_ATTENTE,EN_LIVRAISON,LIVREE};

    /**
     * la date de creation de la livraison
     * @return
     */
    public Calendar getDateCreation();

    /**
     * retourne la commande dont c'est la livraison
     * @return
     */
    public Commande getCommande();

    /**
     * get le/la récipiendaire
     * @return
     */
    public Client getClient();

    /**
     * cette methode modifie le client destinataire de 
     * la livraison
     * @param destinataire
     */
    public void setClient(Client destinataire);

    /**
     * ajouter un item inventaire dans la livraison
     * @param item
     */
    public void ajouterItemInventaire(ItemInventaire item);

    /**
     * retirer un item inventaire de la livraison
     * @param item
     */
    public void retirerItemInventaire(ItemInventaire item);

    /**
     * retourne la liste des elements (<code>intemInventaire</code>)
     * contenus dans la livraison
     * @return
     */
    public Iterator<ItemInventaire> getContenuLivraison();

    /**
     * identifie/affecte l'adresse de livraison
     * @param adresse
     */
    public void setAdresseDeLivraison(Adresse adresse);

    /**
     * retourne l'adresse de livraison
     * @return
     */
    public Adresse getAdresseDeLivraison();

    /**
     * vérifier le statut de la livraison
     * @return
     */
    public StatutLivraison getStatutLivraison();

    /**
     * modifier le statut de la livraison à <code>nouveauStatut</code>
     * @param nouveauStatut
     */
    public void modifierStatutLivraison(StatutLivraison nouveauStatut);

    /**
     * retourne la date de livraison de cette livraison
     * @return
     */
    public Calendar getDateLivraison();

    /**
     * set la date de livraison à <code>date</code>
     * @param date
     */
    public void setDateLivraison(Calendar date);
}