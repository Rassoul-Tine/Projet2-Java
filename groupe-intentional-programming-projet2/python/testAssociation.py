import unittest
from app import Cours, Etudiant, GroupeCours, Programme, Session

class TestDATestAssociations(unittest.TestCase):

    def test_lien_programme_cours(self):
        mgl = Programme("1822", "Maitrise en Génie Logiciel", 45)
        
        mgl7260 = Cours("MGL7260", "Exigences et spécifications de systèmes logiciels", "Introduction à l'ingénierie des systèmes. - Modèles de processus des exigences logicielles", 3)
        mgl7361 = Cours("MGL7361", "Principes et applications de la conception de logiciels", "Rôle de la conception dans le cycle de vie du logiciel", 3)
        mgl7460 = Cours("MGL7460", "Réalisation et maintenance de logiciels", "Rôle de la réalisation et de la maintenance dans le cycle de vie du logiciel", 3)

        mgl.ajouter_cours(mgl7260)
        mgl.ajouter_cours(mgl7361)
        mgl.ajouter_cours(mgl7460)

        found = False
        for cours in mgl.cours:
            found = cours == mgl7361 or found

        self.assertTrue(found, "On ne trouve pas un cours qui a été ajouté au programme")

    def test_lien_etudiant_inscription_groupe_cours(self):
        mgl = Programme("1822", "Maitrise en Génie Logiciel", 45)
        martin = Etudiant("Martin", "Bourgeois", "BOUM12079901", mgl)
        inf1120_aut_2020 = GroupeCours("INF1120", 2020, Session.Automne, None)
        martin.inscrire_groupe_cours(inf1120_aut_2020)

        # Vérifiez que l'inscription a bien été créée et que la liste des inscriptions n'est pas vide
        self.assertTrue(martin.inscriptions, "L'inscription de Martin n'a pas été créée")

    def test_attribution_notes(self):
        mgl = Programme("1822", "Maitrise en Génie Logiciel", 45)
        martin = Etudiant("Martin", "Bourgeois", "BOUM12079901", mgl)
        inf1120_aut_2020 = GroupeCours("INF1120", 2020, Session.Automne, None)
        
        martin.inscrire_groupe_cours(inf1120_aut_2020)
        
        # Assurez-vous que Martin a une inscription avant de saisir une note
        self.assertTrue(martin.inscriptions, "L'inscription de Martin n'a pas été créée")

        martin.saisir_note_groupe_cours(inf1120_aut_2020, 3.3)

        # Vérifiez que la note a été correctement saisie
        self.assertEqual(3.3, martin.inscriptions[0].note_numerique, "La note de Martin est erronée")

        martin.saisir_note_groupe_cours(inf1120_aut_2020, 0.0)  # Nettoyer pour éviter de polluer les autres tests

    def test_moyenne_cumulative(self):
        mgl = Programme("1822", "Maitrise en Génie Logiciel", 45)
        martin = Etudiant("Martin", "Bourgeois", "BOUM12079901", mgl)
        josee = Etudiant("Josee", "Cyr", "CYRJ05530301", mgl)
        inf1120_aut_2020 = GroupeCours("INF1120", 2020, Session.Automne, None)
        inf2120_hiv_2021 = GroupeCours("INF2120", 2021, Session.Hiver, None)
        inf3135_aut_2021 = GroupeCours("INF3135", 2021, Session.Automne, None)
        inf5151_hiv_2022 = GroupeCours("INF5151", 2022, Session.Hiver, None)

        inf1120_aut_2020.inscrire_etudiant(josee)

        martin.inscrire_groupe_cours(inf1120_aut_2020)
        martin.inscrire_groupe_cours(inf2120_hiv_2021)
        martin.inscrire_groupe_cours(inf3135_aut_2021)
        martin.inscrire_groupe_cours(inf5151_hiv_2022)

        # Assurez-vous que Martin a des inscriptions avant de saisir des notes
        self.assertTrue(martin.inscriptions, "Martin n'a pas d'inscriptions")

        martin.saisir_note_groupe_cours(inf1120_aut_2020, 3.3)
        martin.saisir_note_groupe_cours(inf2120_hiv_2021, 3.7)
        martin.saisir_note_groupe_cours(inf3135_aut_2021, 2.7)
        martin.saisir_note_groupe_cours(inf5151_hiv_2022, 4.0)

        inf1120_aut_2020.inscrire_etudiant(josee)
        
        # Assurez-vous que Josee a une inscription avant de saisir une note
        self.assertTrue(josee.inscriptions, "L'inscription de Josee n'a pas été créée")

        josee.saisir_note_groupe_cours(inf1120_aut_2020, 4.3)

        # Vérifiez les moyennes cumulatives de Martin et Josee
        self.assertEqual(3.425, martin.obtenir_moyenne_cumulative(), "Moyenne cumulative de Martin mal calculée")
        self.assertEqual(4.3, josee.obtenir_moyenne_cumulative(), "Moyenne cumulative de Josee mal calculée")

if __name__ == '__main__':
    unittest.main()

