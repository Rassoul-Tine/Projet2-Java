package ca.uqam.info.mgl7460.implementation;

import java.util.HashMap;

import ca.uqam.info.mgl7460.domain.Cours;
import ca.uqam.info.mgl7460.domain.Etudiant;
import ca.uqam.info.mgl7460.domain.GroupeCours;
import ca.uqam.info.mgl7460.domain.Inscription;
import ca.uqam.info.mgl7460.domain.Programme;
import ca.uqam.info.mgl7460.domain.ServiceDossierAcademique;
import ca.uqam.info.mgl7460.domain.Session;

public class ServiceDossierAcademiqueImpl implements ServiceDossierAcademique {

    private HashMap<String,Etudiant> etudiants;

    private HashMap<String,Cours> cours;

    private HashMap<String,Programme> programmes;

    private HashMap<String,GroupeCours> groupesCours;

    public ServiceDossierAcademiqueImpl() {
        etudiants = new HashMap<>();
        cours = new HashMap<>();
        programmes = new HashMap<>();
        groupesCours = new HashMap<>();
    }

    @Override
    public Etudiant creerEtudiant(String prenom, String nom, String codePermanent) {
        Etudiant etudiant = new EtudiantImpl(prenom, nom, codePermanent);
        etudiants.put(codePermanent,etudiant);
        return etudiant;
    }

    @Override
    public Programme creerProgramme(String codeProg, String titre, int nombreCredits) {
        Programme prog = new ProgrammeImpl(codeProg, titre, nombreCredits);
        programmes.put(codeProg,prog);
        return prog;
    }

    @Override
    public Cours creerCours(String sigle, String titre, String description, int nombreCredits, Cours... prerequis) {
        Cours[] coursPrerequis = prerequis;
        Cours nouveauCours = new CoursImpl(sigle,titre,description,nombreCredits);
        for (int i=0; i < prerequis.length; i++) nouveauCours.ajouterPrerequis(coursPrerequis[i]);

        cours.put(sigle,nouveauCours);

        return nouveauCours;
    }

    @Override
    public GroupeCours creerGroupeCours(Cours crs, int annee, Session session, String enseignant) {
        GroupeCours gpeCours = new GroupeCoursImpl(crs, annee, session, enseignant);
        String key = crs.getSigle()+"-"+annee+"-"+session;
        groupesCours.put(key,gpeCours);
        return gpeCours;
    }

    @Override
    public Inscription inscrireEtudiantCours(Etudiant et, GroupeCours gpeCours) {
        return et.inscrireGroupeCours(gpeCours);
    }

    @Override
    public Etudiant inscrireEtudiantProgramme(Etudiant etud, Programme prog) {
        etud.inscrireProgramme(prog);
        return etud;
    }

    @Override
    public void saisirNote(Etudiant etud, GroupeCours gpeCours, float note) {
        etud.setNoteGroupeCours(gpeCours, note);
    }

    @Override
    public float getMoyenne(Etudiant etud) {
        return etud.getMoyenneCumulative();
    }

    @Override
    public float getNombreCreditsCompletes(Etudiant etud) {
        return etud.getNombreCreditsReussis();
    }

    @Override
    public void chargerDonnees() {
        return;
    }

    @Override
    public Etudiant getEtudiantAvecCodePermanent(String code) {
       return etudiants.get(code);
    }

    @Override
    public Cours getCoursAvecSigle(String sigle) {
        return cours.get(sigle);
    }

    @Override
    public Programme getProgrammeAvecCode(String code) {
        return programmes.get(code);
    }

    @Override
    public GroupeCours getGroupeCoursAvecCode(String code) {
        return groupesCours.get(code);
    }
}
