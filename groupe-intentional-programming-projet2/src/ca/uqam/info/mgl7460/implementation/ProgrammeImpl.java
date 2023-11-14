package ca.uqam.info.mgl7460.implementation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import ca.uqam.info.mgl7460.domain.Cours;
import ca.uqam.info.mgl7460.domain.Programme;

public class ProgrammeImpl implements Programme {

    private String code;

    private String titre;

    private int nombreCredits;

    private Collection<Cours> cours;

    public ProgrammeImpl(String code, String titre, int nombreCredits){
        this.code = code;
        this.titre = titre;
        this.nombreCredits = nombreCredits;
        cours = new HashSet<>(50);
    }

    @Override
    public String getCodeProgramme() {
        return code;
    }

    @Override
    public String getTitre() {
        return titre;
    }

    @Override
    public int getNombreCredits() {
        return nombreCredits;
    }

    @Override
    public Cours ajouterCours(Cours unCours) {
        boolean nouveau= cours.add(unCours);
        // vérifier si le cours est nouveau dans l'ensemble
        // ou non. Si oui, retourner le cours, else retourner null
        if (nouveau) return unCours;
        return null;
    }

    @Override
    public Cours enleverCours(Cours unCours) {
        boolean existant = cours.remove(unCours);
        // vérifier si le cours était contenu dans l'ensemble
        // ou non. Si oui, retourner le cours, else retourner null
        if (existant) return unCours;
        return null;
    }

    @Override
    public Iterator<Cours> getCours() {
        return cours.iterator();
    }
    
}
