package ca.uqam.info.mgl7460.implementation;

import java.util.HashMap;
import java.util.Iterator;

import ca.uqam.info.mgl7460.domain.Cours;
import ca.uqam.info.mgl7460.domain.Etudiant;
import ca.uqam.info.mgl7460.domain.GroupeCours;
import ca.uqam.info.mgl7460.domain.Inscription;
import ca.uqam.info.mgl7460.domain.Session;

public class GroupeCoursImpl implements GroupeCours{

    private Cours cours;

    private int annee;

    private Session session;

    private String enseignant;

    private HashMap<Etudiant,Inscription> inscriptions;

    public GroupeCoursImpl(Cours cours, int annee, Session session, String enseignant){
        this(cours, annee, session);
        this.enseignant = enseignant;
    }

    public GroupeCoursImpl(Cours cours, int annee, Session session) {
        this.cours = cours;
        this.annee = annee;
        this.session = session;
        inscriptions = new HashMap<Etudiant,Inscription>();
    }

    @Override
    public Cours getCours() {
        return cours;
    }

    @Override
    public int getAnnee() {
        return annee;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public String getEnseignant() {
        return enseignant;
    }

    @Override
    public Iterator<Inscription> getInscriptions() {
        return inscriptions.values().iterator();
    }

    @Override
    public Iterator<Etudiant> getEtudiantsInscrits() {
        return inscriptions.keySet().iterator();
    }

    @Override
    public Inscription inscrireEtudiant(Etudiant etud) {
        // first check is student has been registered
        Inscription inscription = inscriptions.get(etud);
        if (inscription == null) {
            // delegate registration to Etudiant
           return etud.inscrireGroupeCours(this);
        }
        return inscription;
    }

    @Override
    public float getNoteEtudiant(Etudiant etud) {
        return inscriptions.get(etud).getNoteNumerique();
    }

    @Override
    public void setNoteEtudiant(Etudiant etud, float note) {
        inscriptions.get(etud).setNoteNumerique(note);
    }

    @Override
    public Inscription desinscrireEtudiant(Etudiant etud) {
        return inscriptions.remove(etud);
    }

    @Override
    public boolean estInscrit(Etudiant etud) {
        return inscriptions.containsKey(etud);
    }

    /**
     * cette méthode ne fait pas partie de l'interface publique. Elle a visibilité "package"
     * Elle sert à "attacher" les inscriptions des deux côtés
     * @param inscription
     */

    void ajouterInscription(Inscription inscription){
        inscriptions.put(inscription.getEtudiant(),inscription);
    }

    @Override
    public String getID() {
        return cours.getSigle()+"-"+annee+"-"+session;
    }
    
}
