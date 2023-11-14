package ca.uqam.info.mgl7460.boutique.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ca.uqam.info.mgl7460.boutique.domain.Client;
import ca.uqam.info.mgl7460.boutique.domain.Commande;
import ca.uqam.info.mgl7460.boutique.domain.Facture;
import ca.uqam.info.mgl7460.boutique.domain.Paiement;
import ca.uqam.info.mgl7460.boutique.domain.Produit;

public class FactureImpl implements Facture {

    private Commande commande;
    private List<LigneFacture> lignesFacture;
    private List<Paiement> paiements;
    private float reductionGlobale;
    private Map<Produit, Float> reductionsSurProduits;

    public FactureImpl(Commande commande) {
        this.commande = commande;
        this.lignesFacture = new ArrayList<>();
        this.paiements = new ArrayList<>();
        this.reductionGlobale = 0.0f;
        this.reductionsSurProduits = new HashMap<>();
    }

    @Override
    public Commande getCommande() {
        return commande;
    }

    @Override
    public float getMontant() {
        float montantTotal = 0.0f;
        for (LigneFacture ligne : lignesFacture) {
            float prixReduit = ligne.prixUnitaire() * (1 - ligne.reduction()) * ligne.quantite();
            montantTotal += prixReduit;
        }
        montantTotal *= (1 - reductionGlobale);
        return montantTotal;
    }

    @Override
    public float getBalance() {
        return getMontant() - getTotalPaiements();
    }

    @Override
    public Paiement ajouterPaiement(float amount, Client payeur) {
        Paiement paiement = new PaiementImpl(payeur, this, amount);
        paiements.add(paiement);
        return paiement;
    }

    @Override
    public Iterator<LigneFacture> getDetailsFacture() {
        return lignesFacture.iterator();
    }

    @Override
    public Iterator<Paiement> getPaiements() {
        return paiements.iterator();
    }

    @Override
    public void setReductionGlobale(float reductionGlobale) {
        this.reductionGlobale = reductionGlobale;
    }

    @Override
    public float getReductionGlobale() {
        return reductionGlobale;
    }

    @Override
    public void setReductionSurProduit(Produit produit, float reductionProduit) {
        reductionsSurProduits.put(produit, reductionProduit);
        for (LigneFacture ligne : lignesFacture) {
            if (ligne.produit().equals(produit)) {
                float nouveauPrixTotal = ligne.prixUnitaire() * (1 - reductionProduit) * ligne.quantite();
                lignesFacture.set(lignesFacture.indexOf(ligne), new LigneFacture(
                        produit, ligne.prixUnitaire(), reductionProduit, ligne.quantite(), nouveauPrixTotal));
            }
        }
    }

    @Override
    public float getReductionSurProduit(Produit produit) {
        return reductionsSurProduits.getOrDefault(produit, 0.0f);
    }

    @Override
    public float getTotalPaiements() {
        float totalPaiements = 0.0f;
        for (Paiement paiement : paiements) {
            totalPaiements += paiement.getMontant();
        }
        return totalPaiements;
    }
}