package ca.uqam.info.mgl7460.boutique.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ca.uqam.info.mgl7460.boutique.domain.Adresse;
import ca.uqam.info.mgl7460.boutique.domain.Client;
import ca.uqam.info.mgl7460.boutique.domain.Commande;
import ca.uqam.info.mgl7460.boutique.domain.Facture;
import ca.uqam.info.mgl7460.boutique.domain.Paiement;
import ca.uqam.info.mgl7460.boutique.domain.Panier;
import ca.uqam.info.mgl7460.boutique.domain.Province;
import ca.uqam.info.mgl7460.boutique.domain.Salutation;

public class ClientImpl implements Client {

    private Identification identification;
    private Adresse adresse;
    private Panier panier;
    private List<Commande> commandes;
    private List<Paiement> paiements;
    

    public ClientImpl(String prenom, String nom, Salutation salutation) {
        this.identification = new Identification(prenom, nom, salutation);
        this.panier = null;
        this.commandes = new ArrayList<>();
        this.paiements = new ArrayList<>();
    }
    public ClientImpl(String prenom, String nom, Salutation salutation, String numeroPorte, String numeroRue, String nomRue, String ville, String codePostal, Province province) {
        this.identification = new Identification(prenom, nom, salutation);
        this.adresse = new Adresse(numeroPorte, numeroRue, nomRue, ville, codePostal, province);
        this.panier = null;
        this.commandes = new ArrayList<>();
        this.paiements = new ArrayList<>();
    
    
    }
    @Override
    public Identification getIdentification() {
        return identification;
    }

    @Override
    public Adresse getAdresse() {
        return adresse;
    }
    

    @Override
    public void setAdresse(Adresse nouvelle) {
        this.adresse = nouvelle;
    }
    
    @Override
public String toString() {
    return identification.salutation() + " " +
           identification.prenom() + " " +
           identification.nom() + "\n" +
           (adresse != null ? adresse.numeroPorte() + ", " : "") +
           (adresse != null ? adresse.numeroRue() + " " : "") +
           (adresse != null ? adresse.nomRue() + "\n" : "") +
           (adresse != null ? adresse.ville() + ", " : "") +
           (adresse != null ? adresse.codePostal() + " " : "") +
           (adresse != null ? adresse.province() : "");
}

    @Override
    public Panier creerPanier() {
        panier = new PanierImpl(this);
        return panier;
    }

    @Override
    public Panier getPanier() {
        return panier;
    }

    @Override
    public void ajouterCommande(Commande commande) {
        commandes.add(commande);
    }

    @Override
    public void retirerCommande(Commande commande) {
        commandes.remove(commande);
    }

    @Override
    public Iterator<Commande> getCommandes() {
        return commandes.iterator();
    }

    @Override
    public Iterator<Paiement> getPaiements() {
        return paiements.iterator();
    }

    @Override
    public void ajouterPaiement(Paiement paiement) {
        paiements.add(paiement);
    }

    @Override
    public Iterator<Paiement> getPaiementsPourCommande(Commande commande) {
        List<Paiement> paiementsPourCommande = new ArrayList<>();
        Facture factureDeLaCommande = commande.getFacture();

        for (Paiement paiement : paiements) {
            if (paiement.getFacture().equals(factureDeLaCommande)) {
                paiementsPourCommande.add(paiement);
            }
        }

        return paiementsPourCommande.iterator();
    }
    
}
