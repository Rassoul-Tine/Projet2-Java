package ca.uqam.info.mgl7460.domain;

public interface Inscription {

    /**
     * l'étudiant en question
     * @return
     */
    public Etudiant getEtudiant();

    /**
     * le groupe cours en question
     * @return
     */
    public GroupeCours getGroupeCours();

    /**
     * la note numérique sur le cours
     * @return
     */
    public float getNoteNumerique();

    /**
     * attribuer la note au cours
     * @param note
     */
    public void setNoteNumerique(float note);
    
    /**
     * verifier si le cours a été réussi
     * @return
     */
    public boolean isReussi();
}
