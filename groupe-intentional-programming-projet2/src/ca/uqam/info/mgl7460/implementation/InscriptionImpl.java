package ca.uqam.info.mgl7460.implementation;

import ca.uqam.info.mgl7460.domain.Etudiant;
import ca.uqam.info.mgl7460.domain.GroupeCours;
import ca.uqam.info.mgl7460.domain.Inscription;

public class InscriptionImpl implements Inscription{

    private Etudiant etudiant;

    private GroupeCours groupeCours;

    private float noteNumerique;

    public InscriptionImpl(Etudiant etud, GroupeCours gpeCours) {
        etudiant = etud;
        groupeCours = gpeCours;
        noteNumerique = 0f;
    }

    @Override
    public Etudiant getEtudiant() {
        return etudiant;
    }

    @Override
    public GroupeCours getGroupeCours() {
       return groupeCours;
    }

    @Override
    public float getNoteNumerique() {
       return noteNumerique;
    }

    @Override
    public void setNoteNumerique(float note) {
        noteNumerique = note;
    }

    @Override
    public boolean isReussi() {
       return (noteNumerique >= 2.0f);
    }
    
}
