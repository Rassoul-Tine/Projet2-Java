package ca.uqam.info.mgl7460.domain;

import java.util.Iterator;

public interface GroupeCours {

    /**
     * retourne le cours en question
     */
    public Cours getCours();

    /**
     * retourne l'année durant laquelle le groupe cours est offert
     */
    public int getAnnee();

    /**
     * retourne la session durant laquelle le groupe cours
     * est offert
     */
    public Session getSession();

    /**
     * retourne (le nom de) l'enseignant qui donne le cours
     */
    public String getEnseignant();

    /**
     * retourne la liste des inscriptions dans le cours.
     * L'iterateur qui est retourné permet de calculer plusieurs
     * choses, dont la moyenne, l'écart type, etc.
     */
    public Iterator<Inscription> getInscriptions();

    /**
     * retourne la liste des étudiants inscrits dans le cours
     */
    public Iterator<Etudiant> getEtudiantsInscrits();

    /**
     * Inscrire un étudiant au groupe cours. La fonction
     * retourne le "record" <code>Inscription</code> qui est créée
     * @param etud  l'étudiant que l'on veut inscrire
     */
    public Inscription inscrireEtudiant(Etudiant etud);

    /**
     * verifier si l'étudiant <code>etud</code> est inscrit
     * @param etud
     * @return
     */
    public boolean estInscrit(Etudiant etud);

    /**
     * Désinscrire un étudiant du groupe cours. La fonction
     * retourne le "record" <code>Inscription</code> correspondant
     * à l'inscription de l'étudiant en question. Si l'étudiant.e n'était
     * pas inscrit.e, on retourne null.
     * @param etud  l'étudiant que l'on veut désinscrire
     */
    public Inscription desinscrireEtudiant(Etudiant etud);

    /**
     * retourne la note de l'étudiant <code>etud</code> au cours
     * @param   etud    l'étudiant dont on veut la note
     */
    public float getNoteEtudiant(Etudiant etud);

    /**
     * attribuer une note de l'étudiant.e passé en paramètre
     * @param   etud    l'étudiant dont on veut assigner la note
     * @param   note    la note en question
     */
    public void setNoteEtudiant(Etudiant etud, float note);

    /**
     * retourne une chaine de caractères permettant d'identifier uniquement
     * le groupe cours. C'est une concaténation de champs
     * @return
     */
    public String getID();
    
}
