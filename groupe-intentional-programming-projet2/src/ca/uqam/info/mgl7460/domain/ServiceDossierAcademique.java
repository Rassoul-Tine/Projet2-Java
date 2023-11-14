package ca.uqam.info.mgl7460.domain;

public interface ServiceDossierAcademique {

    /**
     * Créér un étudiant.
     * 
     * @param prenom
     * @param nom
     * @param codePermanent
     * @return
     */

    public Etudiant creerEtudiant(String prenom, String nom, String codePermanent);

    /**
     * crééer un programme avec les données passées en paramètre
     * @param codeProg
     * @param titre
     * @param nombreCredits
     * @return
     */
    public Programme creerProgramme(String codeProg, String titre, int nombreCredits);

    /**
     * créér un cours avec les données passées en paramètre
     * @param sigle
     * @param titre
     * @param description
     * @param nombreCredits
     * @param prerequis
     * @return
     */
    public Cours creerCours(String sigle, String titre, String description, int nombreCredits, Cours... prerequis);

    /**
     * Créer un <code>GroupeCours</code> avec les données passées en paramètres
     * @param crs
     * @param annee
     * @param session
     * @param enseignant
     * @return
     */
    public GroupeCours creerGroupeCours(Cours crs, int annee, Session session, String enseignant);

    /**
     * cette méthode ba inscrire un.e étudiant.e à un groupe cours. Elle retourne
     * le nouvel objet <code>Inscription</code> correspondant
     * @param et
     * @param gpeCours
     * @return
     */
    public Inscription inscrireEtudiantCours(Etudiant et, GroupeCours gpeCours);

    /**
     * Cette méthode va inscrire un étudiant à un programme. Elle retourne l'étudiant
     * qui aura été lié au programme.
     * @param etud
     * @param prog
     * @return
     */
    public Etudiant inscrireEtudiantProgramme(Etudiant etud, Programme prog);

    /**
     * cette méthode permet de saisir la <code>note</code> d'un étudiant <code>etud</code>
     * à un groupe cours <code>gpeCours</code>
     * @param etud
     * @param gpeCours
     * @param note
     */
    public void saisirNote(Etudiant etud, GroupeCours gpeCours, float note);

    /**
     * cette méthode retourne la moyenne académique de l'étudiant.e (<code>etud</code>)
     * passé en paramètre
     * @param etud
     * @return
     */
    public float getMoyenne(Etudiant etud);

    /**
     * cette méthode retourne le nombre de crédits complétés par
     * l'étudiant.e code>etud</code>
     * @param etud
     * @return
     */
    public float getNombreCreditsCompletes(Etudiant etud);

    /**
     * cette méthode utilitaire charge les données du système pour pouvoir
     * réaliser les différentes fonctions et les différents tests.
     */
    public void chargerDonnees();

    /**
     * retourne l'étudiant avec code <code>code</code>
     * @param code
     * @return
     */
    public Etudiant getEtudiantAvecCodePermanent(String code);

    /**
     * retourne le cours avcec sigle <code>sigle</code>
     * @param sigle
     * @return
     */
    public Cours getCoursAvecSigle(String sigle);

    /**
     * retourne le programme avec code <code>code</code>
     * @param code
     * @return
     */
    public Programme getProgrammeAvecCode(String code);

    /**
     * retourne le groupe cours avec code <code>code</code>
     * @param code
     * @return
     */
    public GroupeCours getGroupeCoursAvecCode(String code);
    
}
