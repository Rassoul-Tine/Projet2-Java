package ca.uqam.info.mgl7460.implementation;

import java.util.HashMap;

import ca.uqam.info.mgl7460.domain.Cours;
import ca.uqam.info.mgl7460.domain.Etudiant;
import ca.uqam.info.mgl7460.domain.GroupeCours;
import ca.uqam.info.mgl7460.domain.Inscription;
import ca.uqam.info.mgl7460.domain.Programme;

public class EtudiantImpl implements Etudiant{

    private String prenom;

    private String nom;

    private String codePermanent;

    private Programme programme;

    private HashMap<Cours,Inscription> inscriptions;

    public EtudiantImpl(String prenom, String nom, String codePermanent) {
        this.prenom = prenom;
        this.nom = nom;
        this.codePermanent = codePermanent;
        inscriptions = new HashMap<Cours,Inscription>(30);
    }

    @Override
    public String getNom() {
       return nom;
    }

    @Override
    public String getPrenom() {
        return prenom;
    }

    @Override
    public String getCodePermanent() {
       return codePermanent;
    }

    @Override
    public void setCodePermanent(String code) {
        codePermanent = code;
    }

    @Override
    public Programme getProgramme() {
        return programme;
    }

    @Override
    public void inscrireProgramme(Programme prog) {
        programme = prog;
    }

    @Override
    public float getNombreCreditsReussis() {
        // Il vaut mieux les calculer on-the-fly, pour s'assurer que le nombre est
        // toujours à jour
        return inscriptions.values().stream().filter(inscription -> (inscription.isReussi())).map(inscription -> inscription.getGroupeCours().getCours().getNombreCredits()).mapToInt((x) -> x).sum();
    }

    @Override
    public float getMoyenneCumulative() {
        // on calcule la somme pondérée de note * nombre de crédits pour chaque cours ayant une note /= 0, puis on divise par
        // le nombre de credits (y compris non réussis).
        double sommePonderee = inscriptions.values().stream().filter(inscription -> (inscription.getNoteNumerique()!=0.0f)).map(inscription -> (inscription.getNoteNumerique()*inscription.getGroupeCours().getCours().getNombreCredits())).mapToDouble((x)->x).sum();
        int creditsSuivis = inscriptions.values().stream().filter(inscription -> (inscription.getNoteNumerique()!=0.0f)).map(inscription -> (inscription.getGroupeCours().getCours().getNombreCredits())).mapToInt((x) -> x).sum();
        return (float)(sommePonderee/creditsSuivis);
    }

    @Override
    public Inscription inscrireGroupeCours(GroupeCours gpeCours) {
       // créer l'inscription
       Inscription inscription = new InscriptionImpl(this, gpeCours);

       // ajouter l'inscription aux inscriptions de l'étudiant
       inscriptions.put(gpeCours.getCours(), inscription);

       // ajouter l'inscription au groupe cours
       ((GroupeCoursImpl)gpeCours).ajouterInscription(inscription);

       return inscription;
    }

    @Override
    public void setNoteGroupeCours(GroupeCours gpeCours, float note) {
        // trouver la bonne inscription
        Inscription inscription = inscriptions.get(gpeCours.getCours());
        if (inscription != null) {
            inscription.setNoteNumerique(note);
        }
    }

    @Override
    /**
     * retourne -1 si le cours n'a pas été pris
     */
    public float getNotePourCours(Cours cours) {
       Inscription inscription = inscriptions.get(cours);
       if (inscription!= null) return inscription.getNoteNumerique();
       return -1.0f;
    }
    
}
