package ca.uqam.info.mgl7460.boutique.implementation;

import ca.uqam.info.mgl7460.boutique.domain.Boutique;
import ca.uqam.info.mgl7460.boutique.domain.Client;
import ca.uqam.info.mgl7460.boutique.domain.Commande;
import ca.uqam.info.mgl7460.boutique.domain.FabriqueBoutique;
import ca.uqam.info.mgl7460.boutique.domain.Facture;
import ca.uqam.info.mgl7460.boutique.domain.Paiement;
import ca.uqam.info.mgl7460.boutique.domain.Panier;
import ca.uqam.info.mgl7460.boutique.domain.Produit;
import ca.uqam.info.mgl7460.boutique.domain.Province;
import ca.uqam.info.mgl7460.boutique.domain.Salutation;

public class BoutiqueImpl implements Boutique {

    private static BoutiqueImpl boutiqueSingleton;

    private BoutiqueImpl() {
    
    }

    public static synchronized BoutiqueImpl getBoutiqueSingleton() {
        if (boutiqueSingleton == null) {
            boutiqueSingleton = new BoutiqueImpl();
        }
        return boutiqueSingleton;
    }

    @Override
    public FabriqueBoutique getFabriqueBoutique() {
        return new FabriqueBoutiqueImpl();
    }

    @Override
    public Client inscrireClient(String prenom, String nom, Salutation salutation, String numeroPorte, String numeroRue, String nomRue, String ville, String codePostal, Province province) {
        return new ClientImpl(prenom, nom, salutation, numeroPorte, numeroRue, nomRue, ville, codePostal, province);
    }

    @Override
    public Panier demarrerSessionClient(Client client) {
        return new PanierImpl(client);
    }

    @Override
    public void ajouterProduit(Produit produit, Panier panier) {
        panier.ajouterProduit(produit);
    }

    @Override
    public void retirerProduit(Produit produit, Panier panier) {
        panier.enleverProduit(produit);
    }

    @Override
    public int incrementerQuantiteProduit(Produit produit, Panier panier) {
        return panier.incrementerQuantiteProduit(produit);
    }

    @Override
    public int decrementerQuantiteProduit(Produit produit, Panier panier) {
        return panier.decrementerQuantiteProduit(produit);
    }

    @Override
    public Commande commander(Panier panier) {
        return new CommandeImpl();
    }

    @Override
    public Facture creerFacturePourCommande(Commande commande) {
        return new FactureImpl(commande);
    }

    @Override
    public float accorderReductionPourFacture(Facture facture, float reduction) {
        facture.setReductionGlobale(reduction);
    return facture.getMontant();
    }

    @Override
    public float accorderReductionPourProduitDansFacture(Facture facture, Produit produit, float reduction) {
        facture.setReductionSurProduit(produit, reduction);
    return facture.getMontant();
    }

    @Override
    public Paiement payerFacture(Facture facture, Client client, float montant) {
        return new PaiementImpl(client, facture, montant);
    }
}
