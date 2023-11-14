package ca.uqam.info.mgl7460.implementation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import ca.uqam.info.mgl7460.domain.Cours;

public class CoursImpl implements Cours{

    private String sigle;

    private String titre;

    private String description;

    private int nombreCredits;

    private Collection<Cours> prerequis;

    public CoursImpl(String sigle, String titre, String description,int nombreCredits){
        this.sigle = sigle;
        this.titre = titre;
        this.description = description;
        this.nombreCredits = nombreCredits;
        prerequis = new HashSet<Cours>(3);
    }

    @Override
    public String getSigle() {
        return sigle;
    }

    @Override
    public String getTitre() {
       return titre;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getNombreCredits() {
        return nombreCredits;
    }

    @Override
    public Iterator<Cours> getPrerequis() {
        return prerequis.iterator();
    }

    @Override
    public Cours ajouterPrerequis(Cours prereq) {
        if (prerequis == null) prerequis = new HashSet<Cours>();
        prerequis.add(prereq);
        return prereq;
    }

    @Override
    public Cours retirerPrerequis(Cours prereq) {
        boolean included = prerequis.remove(prereq);
        if (included) return prereq;
        return null;
    }
    
}
