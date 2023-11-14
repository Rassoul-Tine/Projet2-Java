package ca.uqam.info.mgl7460.tests;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import ca.uqam.info.mgl7460.domain.Cours;
import ca.uqam.info.mgl7460.domain.Etudiant;
import ca.uqam.info.mgl7460.domain.GroupeCours;
import ca.uqam.info.mgl7460.domain.Inscription;
import ca.uqam.info.mgl7460.domain.Programme;
import ca.uqam.info.mgl7460.domain.ServiceDossierAcademique;
import ca.uqam.info.mgl7460.domain.Session;
import ca.uqam.info.mgl7460.implementation.ServiceDossierAcademiqueImpl;;

public class DATestAssociations {
private static ServiceDossierAcademique serviceDA= null;



    @Test
    void testLienProgrammeCours() {
        String code = "1822", titre = "Maitrise en Génie Logiciel";
        Programme mgl = serviceDA.creerProgramme(code, titre,45); 
        Cours
            mgl7260 = serviceDA.creerCours("MGL7260", "Exigences et spécifications de systèmes logiciels","Introduction à l'ingénierie des systèmes. - Modèles de processus des exigences logicielles",3),
            mgl7361 = serviceDA.creerCours("MGL7361", "Principes et applications de la conception de logiciels", "Rôle de la conception dans le cycle de vie du logiciel", 3, mgl7260),
            mgl7460 = serviceDA.creerCours("MGL7460", "Réalisation et maintenance de logiciels","Rôle de la réalisation et de la maintenance dans le cycle de vie du logiciel",3,mgl7361);

        mgl.ajouterCours(mgl7260);
        mgl.ajouterCours(mgl7361);
        mgl.ajouterCours(mgl7460);
        Boolean found = false;
        Iterator<Cours> listeCoursMGL = mgl.getCours();
        while (listeCoursMGL.hasNext()) {
            found = listeCoursMGL.next().equals(mgl7361) || found ;
         }
        Assertions.assertTrue(found,"On ne trouve pas un cours qui a été ajouté au programme");
        // Wow! c'est compliqué de savoir si un Cours est inclus dans un Programme!
    }

    @BeforeAll
    static public void creerDonneesDeTest() {
        serviceDA = new ServiceDossierAcademiqueImpl();
        Cours 
            inf1120 = serviceDA.creerCours("INF1120", "Programmation 1", "Acquérir une méthode de développement", 3),
            inf2120 = serviceDA.creerCours("INF2120", "Programmation II", "Approfondir les concepts de la programmation OO", 3,inf1120),
            inf3135 = serviceDA.creerCours("INF3135", "Construction et maintenance de logiciels", "Initier les étudiants à la programmation à l’aide d’un langage impératif et procédural.", 3,inf1120),
            inf5151 = serviceDA.creerCours("INF5151", "Génie logiciel: analyse et modélisation", "Explorer les fondements et l’évolution des méthodes d’analyse", 3),
            inf5153 = serviceDA.creerCours("INF5153", "Génie logiciel: conception", "Sensibiliser l’étudiant aux difficultés de la conception", 3, inf3135,inf5151);

        GroupeCours 
            inf1120_aut_2020 = serviceDA.creerGroupeCours(inf1120,2020,Session.Automne,null),
            inf1120_aut_2021 = serviceDA.creerGroupeCours(inf1120,2021,Session.Automne,null),
            inf2120_hiv_2021 = serviceDA.creerGroupeCours(inf2120,2021,Session.Hiver,null),
            inf2120_hiv_2022 = serviceDA.creerGroupeCours(inf2120,2022,Session.Hiver,null),
            inf3135_aut_2021 = serviceDA.creerGroupeCours(inf3135,2021,Session.Automne,null),
            inf5151_hiv_2022 = serviceDA.creerGroupeCours(inf5151,2022,Session.Hiver, null),
            inf5153_aut_2022 = serviceDA.creerGroupeCours(inf5153,2022,Session.Automne,null);
        
        Etudiant 
            martin = serviceDA.creerEtudiant("Martin", "Bourgeois", "BOUM12079901"),
            josee = serviceDA.creerEtudiant("Josée", "Cyr", "CYRJ05530301");

        // Inscriptions
        Inscription inscription = null;

        // martin. On essaie les deux façons d'inscrire martin, via:
            // 1) classe Etudiant
        inscription = martin.inscrireGroupeCours(inf1120_aut_2020);
            // 2) classe GroupeCours
        inscription = inf2120_hiv_2021.inscrireEtudiant(martin);
        inscription = martin.inscrireGroupeCours(inf3135_aut_2021);
        inscription = martin.inscrireGroupeCours(inf5151_hiv_2022);
        inscription = martin.inscrireGroupeCours(inf5153_aut_2022);

        inscription = josee.inscrireGroupeCours(inf1120_aut_2021);
        inscription = inf2120_hiv_2022.inscrireEtudiant(josee);
    }
        
    /**
     * vérifier que quand on s'inscrit à un groupe cours, on ressort dans la liste de ses 
     * inscriptions.
     * 
     * Notez que nous n'avons (je n'ai) prévu de naviguer 
     */
    @Test
    public void testLienEtudiantInscriptionGroupeCours() {
                
        // nous avons (méthode creerDonneesDeTest()) inscrit Martin
        // au cours "INF1120-2020-Automne". Nous assurer qu'il est
        // dans la liste des inscrits

        Etudiant martin = serviceDA.getEtudiantAvecCodePermanent("BOUM12079901");

        // tester inscription via <code>Etudiant</code>
        Assertions.assertTrue(serviceDA.getGroupeCoursAvecCode("INF1120-2020-Automne").estInscrit(martin), "Martin n'a pas été inscrit");
        
        // tester inscription via <code>GroupeCours</code>
        Assertions.assertTrue(serviceDA.getGroupeCoursAvecCode("INF2120-2021-Hiver").estInscrit(martin), "Martin n'a pas été inscrit");
    }

    @Test
    public void testAttributionNotes() {
                

        Etudiant martin = serviceDA.getEtudiantAvecCodePermanent("BOUM12079901");
    
        // attribuer les notes de martin

        martin.setNoteGroupeCours(serviceDA.getGroupeCoursAvecCode("INF1120-2020-Automne"), 3.3f);
        martin.setNoteGroupeCours(serviceDA.getGroupeCoursAvecCode("INF2120-2021-Hiver"),3.7f);

        // tester l'accès aux notes via martin ...
        Assertions.assertEquals(3.3f, martin.getNotePourCours(serviceDA.getCoursAvecSigle("INF1120")), "Ouains, la note de martin est erronée");
        // et via le groupe cours
        Assertions.assertEquals(3.7f, serviceDA.getGroupeCoursAvecCode("INF2120-2021-Hiver").getNoteEtudiant(martin), "Ouains, pas fort ton système");

        // nettoyer pour pas que ça pollue les autres tests
        martin.setNoteGroupeCours(serviceDA.getGroupeCoursAvecCode("INF1120-2020-Automne"), 0.0f);
        martin.setNoteGroupeCours(serviceDA.getGroupeCoursAvecCode("INF2120-2021-Hiver"),0.0f);
    }

    @Test
    public void testMoyenneCumulative() {
                

        Etudiant martin = serviceDA.getEtudiantAvecCodePermanent("BOUM12079901"),
            josee = serviceDA.getEtudiantAvecCodePermanent("CYRJ05530301");
    
        // attribuer les notes de martin

        martin.setNoteGroupeCours(serviceDA.getGroupeCoursAvecCode("INF1120-2020-Automne"), 3.3f);
        martin.setNoteGroupeCours(serviceDA.getGroupeCoursAvecCode("INF2120-2021-Hiver"),3.7f);
        martin.setNoteGroupeCours(serviceDA.getGroupeCoursAvecCode("INF3135-2021-Automne"),2.7f);
        martin.setNoteGroupeCours(serviceDA.getGroupeCoursAvecCode("INF5151-2022-Hiver"),4.0f);

        // verifier que josee est inscrite au cours en question
        Assertions.assertTrue(serviceDA.getGroupeCoursAvecCode("INF1120-2021-Automne").estInscrit(josee),"Oops! josée n'est pas inscrite dans le INF1120-2021-Automne");
        // attribuer les notes de josee
        josee.setNoteGroupeCours(serviceDA.getGroupeCoursAvecCode("INF1120-2021-Automne"),4.3f);
        josee.setNoteGroupeCours(serviceDA.getGroupeCoursAvecCode("INF2120-2022-Hiver"),4.3f);

        // tester moyenne cumulative de martin
        Assertions.assertEquals(3.425f, martin.getMoyenneCumulative(),"Moyenne cumulative de martin mal calculee");
        // test moyenne cumulative de josee
        Assertions.assertEquals(4.3f, josee.getMoyenneCumulative(),"Moyenne cumulative de josee mal calculee");
    }
}
