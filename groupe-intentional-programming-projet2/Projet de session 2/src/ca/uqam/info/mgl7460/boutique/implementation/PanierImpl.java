package ca.uqam.info.mgl7460.boutique.implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ca.uqam.info.mgl7460.boutique.domain.Client;
import ca.uqam.info.mgl7460.boutique.domain.Commande;
import ca.uqam.info.mgl7460.boutique.domain.LigneCommande;
import ca.uqam.info.mgl7460.boutique.domain.Panier;
import ca.uqam.info.mgl7460.boutique.domain.Produit;

public class PanierImpl implements Panier {

    private final Client client;
    private final Calendar dateCreation;
    private final Map<Produit, Integer> produits;

    public PanierImpl(Client client) {
        this.client = client;
        this.dateCreation = Calendar.getInstance();
        this.produits = new HashMap<>();
    }

    @Override
    public Client getClient() {
        return this.client;
    }

    @Override
    public Calendar getDate() {
        return this.dateCreation;
    }

    @Override
    public void ajouterProduit(Produit prod) {
        produits.put(prod, produits.getOrDefault(prod, 0) + 1);
    }

    @Override
    public void enleverProduit(Produit prod) {
        produits.remove(prod);
    }

    @Override
    public void modifierQuantiteProduit(Produit prod, int quantite) {
        if (produits.containsKey(prod)) {
            if (quantite > 0) {
                produits.put(prod, quantite);
            } else {
                enleverProduit(prod);
            }
        }
    }

    @Override
    public int incrementerQuantiteProduit(Produit prod) {
        return produits.merge(prod, 1, Integer::sum);
    }

    @Override
    public int decrementerQuantiteProduit(Produit prod) {
        return produits.computeIfPresent(prod, (k, v) -> (v > 1) ? v - 1 : null);
    }

    @Override
    public int getQuantiteProduit(Produit prod) {
        return produits.getOrDefault(prod, -1);
    }

    @Override
    public float getValeurPanier() {
        float total = 0;
        for (Map.Entry<Produit, Integer> entry : produits.entrySet()) {
            total += entry.getKey().getPrixUnitaire() * entry.getValue();
        }
        return total;
    }

    @Override
    public Commande creerCommande() {
        Commande commande = new CommandeImpl();
        return commande;
    }

    @Override
    public void reinitialiser() {
        produits.clear();
    }

    @Override
    public Iterator<LigneCommande> getLignesCommande() {
        List<LigneCommande> lignes = new ArrayList<>();
        for (Map.Entry<Produit, Integer> entry : produits.entrySet()) {
            lignes.add(new LigneCommandeImpl(entry.getKey(), entry.getValue()));
        }
        return lignes.iterator();
    }
}
