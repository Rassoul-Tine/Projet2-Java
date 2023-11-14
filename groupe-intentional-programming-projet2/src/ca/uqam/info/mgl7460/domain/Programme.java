package ca.uqam.info.mgl7460.domain;

import java.util.Iterator;

public interface Programme {

    /**
     * retourne le code du programme
     */
    public String getCodeProgramme();
    
    /**
     * retourne le titre du programme
     */
    public String getTitre();
    
    /**
     * retourne le nombre de crédits du programme
     */
    public int getNombreCredits();
    
    /**
     * Ajouter un cours à la liste de cours du programme
     * @param   unCours   cours à ajouter
     */
    public Cours ajouterCours(Cours unCours);

    /**
     * retirer un cours de la liste de cours du programme
     * @param   unCours   cours à retirer
     */
    public Cours enleverCours(Cours unCours);
    
    /**
     * retourner la liste des cours du programme.
     */
    public Iterator<Cours> getCours();
}