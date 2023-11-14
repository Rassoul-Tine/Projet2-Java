package ca.uqam.info.mgl7460.boutique.implementation;

import ca.uqam.info.mgl7460.boutique.domain.LigneCommande;
import ca.uqam.info.mgl7460.boutique.domain.Produit;

public class LigneCommandeImpl implements LigneCommande {

    private Produit produit;
    private int quantite;
    private float prixUnitaire;

    public LigneCommandeImpl(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    @Override
    public Produit getProduit() {
        return this.produit;
    }

    @Override
    public int getQuantite() {
        return this.quantite;
    }

    @Override
    public void modifierQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public float getPrixUnitaire() {
        return this.prixUnitaire;
    }

    @Override
    public void setPrixUnitaire(float prix) {
        this.prixUnitaire = prix;
    }
}
