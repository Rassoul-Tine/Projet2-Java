package ca.uqam.info.mgl7460.domain;

import java.util.Iterator;

public interface Cours {

    /**
     * retourne le sigle du cours
     */
    public String getSigle();

    /**
     * retourne le titre du cours
     */
    public String getTitre();

    /**
     * retourne la description du cours
     */
    public String getDescription();

    /**
     * retourne le nombre de credits du cours
     */
    public int getNombreCredits();
    
    /**
     * retourne les prerequis du cours
     */
    public Iterator<Cours> getPrerequis();

    /**
     * ajoute un prerequis pour le cours
     * @param prerequis : le cours à ajouter comme prérequis
     */
    public Cours ajouterPrerequis(Cours prerequis);

    /**
     * retirer un prérequis au cours
     * @param prerequis : le prerequis à retirer
     */
    public Cours retirerPrerequis(Cours prerequis);
}
