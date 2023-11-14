package ca.uqam.info.mgl7460.boutique;
import ca.uqam.info.mgl7460.boutique.domain.Client;
import ca.uqam.info.mgl7460.boutique.domain.Commande;
import ca.uqam.info.mgl7460.boutique.domain.Facture;
import ca.uqam.info.mgl7460.boutique.domain.Livraison;
import ca.uqam.info.mgl7460.boutique.domain.Paiement;
import ca.uqam.info.mgl7460.boutique.domain.Panier;
import ca.uqam.info.mgl7460.boutique.domain.Produit;
import ca.uqam.info.mgl7460.boutique.domain.Province;
import ca.uqam.info.mgl7460.boutique.domain.Salutation;
import ca.uqam.info.mgl7460.boutique.implementation.BoutiqueImpl;

public class MainClass {

    public static void main(String[] args) {
        BoutiqueImpl boutique = BoutiqueImpl.getBoutiqueSingleton();
        System.out.println("Hello, World, from MGL7460 Boutique!");
        Client client = boutique.inscrireClient("Uncle", "Bob", Salutation.MONSIEUR, "10A", "201", "Président Kennedy", "Montréal", "H2X3Y7", Province.QUEBEC);
        System.out.println("nouveau client " + client );

        Panier panier = boutique.demarrerSessionClient(client);
        Produit chaise = boutique.getFabriqueBoutique().creerProduit("CHAISE101", "Chaise", 4, 275);
        Produit table = boutique.getFabriqueBoutique().creerProduit("TABLE101", "Table", 1, 375);
        Produit lampe = boutique.getFabriqueBoutique().creerProduit("LAMPE101", "Lampe", 1, 49);

        boutique.ajouterProduit(chaise, panier);
        boutique.ajouterProduit(chaise, panier);
        boutique.ajouterProduit(chaise, panier);
        boutique.ajouterProduit(chaise, panier);
        boutique.ajouterProduit(lampe, panier);
        boutique.ajouterProduit(table, panier);

        Commande commande = boutique.commander(panier);
        Facture facture = boutique.creerFacturePourCommande(commande);

        boutique.accorderReductionPourFacture(facture, 10/100);
        boutique.accorderReductionPourProduitDansFacture(facture, chaise, 20/100);

        Paiement paiement = boutique.payerFacture(facture, client, 500);
        float soldeApresPremierPaiement = facture.getMontant() - paiement.getMontant();
    
        Paiement secondPaiement = boutique.payerFacture(facture, client, 250);
        Livraison premiereLivraison = boutique.getFabriqueBoutique().creerLivraison(commande, client);
        Livraison deuxiemeLivraison = boutique.getFabriqueBoutique().creerLivraison(commande, client);
    
    }
}
