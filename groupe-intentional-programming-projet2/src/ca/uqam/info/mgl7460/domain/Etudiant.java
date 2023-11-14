package ca.uqam.info.mgl7460.domain;

public interface Etudiant {

    /**
     * nom de famille de la personne étudiante
     */
    public String getNom();

    /**
     * prénom de la personne étudiante
     */
    public String getPrenom();

    /**
     * lire code permanent
     */
    public String getCodePermanent();

    /**
     * modifier code permanent
     * @param code Le code permanent
     */
    public void setCodePermanent(String code);

    /**
     * programme d'études
     */
    public Programme getProgramme();

    /**
     * inscrire à un programme
     * @param prog Le programme en question
     */
    public void inscrireProgramme(Programme prog);

    /**
     * nombre de crédits réussis
     */
    public float getNombreCreditsReussis();

    /**
     * moyenne cumulative
     */
    public float getMoyenneCumulative();

    /**
     * inscrire l'étudiant dans un groupe cours
     * @param gpeCours Le groupe cours auquel on inscrit l'étudiant.e
     */
    public Inscription inscrireGroupeCours(GroupeCours gpeCours);

    /**
     * attribuer une note numérique pour le groupe cours
     * @param gpeCours Le groupe cours
     * @param note la note au cours
     */
    public void setNoteGroupeCours(GroupeCours gpeCours, float note);

    /**
     * retourne la note pour le groupe cours passé en paramètre. 
     * @param gpeCours le groupe cours
     * @param note la note au groupe cours
     */
    public float getNotePourCours(Cours cours);
}
