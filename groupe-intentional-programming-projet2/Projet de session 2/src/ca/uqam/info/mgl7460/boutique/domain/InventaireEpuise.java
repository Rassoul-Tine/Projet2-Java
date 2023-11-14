package ca.uqam.info.mgl7460.boutique.domain;

public class InventaireEpuise extends Exception {

    private Produit produit;

    private String message;

    public InventaireEpuise(Produit prod, String mess) {
        produit = prod;
        message = mess;
    }

    public String getMessage() {
        return message;
    }

    public Produit geProduit() {
        return produit;
    }
}
