package ca.uqam.info.mgl7460.boutique.implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import ca.uqam.info.mgl7460.boutique.domain.Adresse;
import ca.uqam.info.mgl7460.boutique.domain.Client;
import ca.uqam.info.mgl7460.boutique.domain.Commande;
import ca.uqam.info.mgl7460.boutique.domain.Facture;
import ca.uqam.info.mgl7460.boutique.domain.LigneCommande;
import ca.uqam.info.mgl7460.boutique.domain.Livraison;
import ca.uqam.info.mgl7460.boutique.domain.Panier;
import ca.uqam.info.mgl7460.boutique.domain.Produit;


public class CommandeImpl implements Commande {

    private Client client;
    private Calendar dateCommande;
    private String numeroCommande;
    private Adresse adresseDeLivraison;
    private List<LigneCommande> lignesCommandes;
    private List<Livraison> livraisons;
    private Facture facture;

    public CommandeImpl() {
        this.lignesCommandes = new ArrayList<>();
        this.livraisons = new ArrayList<>();
        this.dateCommande = Calendar.getInstance();
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public Calendar getDateCommande() {
        return dateCommande;
    }

    @Override
    public String getNumeroCommande() {
        return numeroCommande;
    }

    @Override
    public Adresse getAdresseDeLivraison() {
        return adresseDeLivraison;
    }

    @Override
    public void setAdresseDeLivraison(Adresse adresse) {
        this.adresseDeLivraison = adresse;
    }

    @Override
    public Iterator<LigneCommande> getLignesCommandes() {
        return lignesCommandes.iterator();
    }

    @Override
    public int getQuantiteCommandee(Produit produit) {
        for (LigneCommande ligne : lignesCommandes) {
            if (ligne.getProduit().equals(produit)) {
                return ligne.getQuantite();
            }
        }
        return 0;
    }

    @Override
    public void ajouteLigneCommande(Produit produit, int quantite) {
        LigneCommande ligne = new LigneCommandeImpl(produit, quantite);
        this.lignesCommandes.add(ligne);
    }

    @Override
    public Iterator<Livraison> getLivraisons() {
        return livraisons.iterator();
    }

    @Override
    public Livraison creerLivraison() {
        Livraison nouvelleLivraison = new LivraisonImpl(this, this.client);
        livraisons.add(nouvelleLivraison);
        return nouvelleLivraison;
    }

    @Override
    public Facture creerFacture() {
        if (facture == null) {
            facture = new FactureImpl(this);
        }
        return facture;
    }

    @Override
    public Facture getFacture() {
        return creerFacture();
    }

    @Override
    public void initialiserAvecPanier(Panier panier) {
        Iterator<LigneCommande> panierItems = panier.getLignesCommande();
        while (panierItems.hasNext()) {
            LigneCommande item = panierItems.next();
            this.ajouteLigneCommande(item.getProduit(), item.getQuantite());
        }
    }
}
