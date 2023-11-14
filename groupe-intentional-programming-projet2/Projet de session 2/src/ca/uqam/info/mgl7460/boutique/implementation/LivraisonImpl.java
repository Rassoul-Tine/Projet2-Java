package ca.uqam.info.mgl7460.boutique.implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import ca.uqam.info.mgl7460.boutique.domain.Adresse;
import ca.uqam.info.mgl7460.boutique.domain.Client;
import ca.uqam.info.mgl7460.boutique.domain.Commande;
import ca.uqam.info.mgl7460.boutique.domain.ItemInventaire;
import ca.uqam.info.mgl7460.boutique.domain.Livraison;

public class LivraisonImpl implements Livraison {

    private Calendar dateCreation;
    private Commande commande;
    private Client client;
    private Adresse adresseDeLivraison;
    private List<ItemInventaire> contenu;
    private StatutLivraison statut;
    private Calendar dateLivraison;

    public LivraisonImpl(Commande commande, Client client) {
        this.dateCreation = Calendar.getInstance();
        this.commande = commande;
        this.client = client;
        this.contenu = new ArrayList<>();
        this.statut = StatutLivraison.EN_PREPARATION;
    }

    @Override
    public Calendar getDateCreation() {
        return this.dateCreation;
    }

    @Override
    public Commande getCommande() {
        return this.commande;
    }

    @Override
    public Client getClient() {
        return this.client;
    }

    @Override
    public void setClient(Client destinataire) {
        this.client = destinataire;
    }

    @Override
    public void ajouterItemInventaire(ItemInventaire item) {
        this.contenu.add(item);
    }

    @Override
    public void retirerItemInventaire(ItemInventaire item) {
        this.contenu.remove(item);
    }

    @Override
    public Iterator<ItemInventaire> getContenuLivraison() {
        return this.contenu.iterator();
    }

    @Override
    public void setAdresseDeLivraison(Adresse adresse) {
        this.adresseDeLivraison = adresse;
    }

    @Override
    public Adresse getAdresseDeLivraison() {
        return this.adresseDeLivraison;
    }

    @Override
    public StatutLivraison getStatutLivraison() {
        return this.statut;
    }

    @Override
    public void modifierStatutLivraison(StatutLivraison nouveauStatut) {
        this.statut = nouveauStatut;
    }

    @Override
    public Calendar getDateLivraison() {
        return this.dateLivraison;
    }

    @Override
    public void setDateLivraison(Calendar date) {
        this.dateLivraison = date;
    }
}
