package ca.uqam.info.mgl7460.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import ca.uqam.info.mgl7460.domain.Cours;
import ca.uqam.info.mgl7460.domain.Etudiant;
import ca.uqam.info.mgl7460.domain.GroupeCours;
import ca.uqam.info.mgl7460.domain.Programme;
import ca.uqam.info.mgl7460.domain.ServiceDossierAcademique;
import ca.uqam.info.mgl7460.domain.Session;
import ca.uqam.info.mgl7460.implementation.ServiceDossierAcademiqueImpl;

public class DATestCreation {

    private static ServiceDossierAcademique serviceDA= null;

    @BeforeAll
    public static void initialiseServiceDossierAcademique() {
        serviceDA = new ServiceDossierAcademiqueImpl();
    }

    @Test
    public void testCreationProgramme() {
        String code = "1822", titre = "Maitrise en Génie Logiciel";
        Programme mgl = serviceDA.creerProgramme(code, titre,45);
        Assertions.assertEquals(code,mgl.getCodeProgramme(),"Wrong program code assigned");
        Assertions.assertEquals(titre, mgl.getTitre(), "Wrong title assigned");
        Assertions.assertEquals(mgl,serviceDA.getProgrammeAvecCode(code),"Cannot retrieve program by code");
    }

    @Test
    public void testCreationEtudiant() {
        String prenom = "Martin", nom = "Bourgeois", codePermanent = "BOUM12079901";
        Etudiant etud = serviceDA.creerEtudiant(prenom,nom, codePermanent);
        Assertions.assertEquals(prenom, etud.getPrenom());
        Assertions.assertEquals(nom,etud.getNom());
        Assertions.assertEquals(codePermanent, etud.getCodePermanent());
        Assertions.assertEquals(etud, serviceDA.getEtudiantAvecCodePermanent(codePermanent),"Cannot retrieve student by code permanent");       
    }

    @Test
    public void testCreationCours() {
        String sigle = "INF1120", titre = "Programmation 1", description = "Acquérir une méthode de développement";
        Cours inf1120 = serviceDA.creerCours(sigle, titre, description, 3);
        Assertions.assertEquals(sigle, inf1120.getSigle(),"Mauvais sigle");
        Assertions.assertEquals(titre,inf1120.getTitre(), "Mauvais titre");
        
        Assertions.assertEquals(description,inf1120.getDescription(), "Mauvaise description");
        Assertions.assertEquals(inf1120, serviceDA.getCoursAvecSigle(sigle),"Ne peut accéder aux cours par sigle");       
    }

    @Test
    public void testCreationGroupeCours() {
        String sigle = "INF1120", titre = "Programmation 1", description = "Acquérir une méthode de développement";
        Cours inf1120 = serviceDA.creerCours(sigle, titre, description, 3);
        int annee = 2023; Session session = Session.Automne; String professeur = "Tournesol";
        GroupeCours groupeCours = serviceDA.creerGroupeCours(inf1120, annee, session, professeur);
        Assertions.assertEquals(inf1120, groupeCours.getCours(), "GroupeCours non associé au bon cours");
        Assertions.assertEquals(annee,groupeCours.getAnnee(),"Groupe cours non associé à la bonne année");
        Assertions.assertEquals(session,groupeCours.getSession(), "Groupe cours associé à la mauvaise session");
        Assertions.assertEquals(professeur, groupeCours.getEnseignant(),"Non attribué au bon professeur");       
    }

}
