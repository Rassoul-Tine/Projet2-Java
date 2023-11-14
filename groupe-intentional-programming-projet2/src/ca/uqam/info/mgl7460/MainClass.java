package ca.uqam.info.mgl7460;

import ca.uqam.info.mgl7460.domain.Etudiant;
import ca.uqam.info.mgl7460.domain.ServiceDossierAcademique;
import ca.uqam.info.mgl7460.implementation.ServiceDossierAcademiqueImpl;

public class MainClass
{
    private static ServiceDossierAcademique serviceDossierAcademique;

    public static void main(String[] args) throws Exception {

        System.out.println("Hello, MGL7460 World!");
        
        ServiceDossierAcademique serviceDA = getServiceDossierAcademique();

        serviceDA.chargerDonnees();

        System.out.println("Je viens de créer une instance de ServiceDossierAcademique et de charger les données");

        // Martin
        Etudiant martin = serviceDA.getEtudiantAvecCodePermanent("BOUM12079901");

        System.out.println("Le nombre de credits completes par Martin est: " + martin.getNombreCreditsReussis());
        System.out.println("Sa moyenne cumulative est: " + martin.getMoyenneCumulative());

        // Josee
        Etudiant josee = serviceDA.getEtudiantAvecCodePermanent("CYRJ05530301");

        System.out.println("Le nombre de credits completes par Josee est: " + josee.getNombreCreditsReussis());
        System.out.println("Sa moyenne cumulative est: " + josee.getMoyenneCumulative());

    }

    public static ServiceDossierAcademique getServiceDossierAcademique() {
        if (serviceDossierAcademique == null) serviceDossierAcademique = new ServiceDossierAcademiqueImpl();
        return serviceDossierAcademique;
    }
}
