import unittest
from app import Etudiant, Programme, GroupeCours, Inscription, Cours, Session

class TestDAAssociations(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.cours1 = Cours("MGL7460", "Cours d'exemple", "Description du cours", 3)
        cls.programme1 = Programme("7617", "Bacc. en Info et Génie Logiciel", 90)
        cls.etudiant1 = Etudiant("Doe", "John", "123456", cls.programme1)
        cls.groupe_cours1 = GroupeCours(cls.cours1, 2023, Session.Automne, "Professeur Smith")
        cls.groupe_cours2 = GroupeCours(cls.cours1, 2023, Session.Hiver, "Professeur Smith")

    def test_lien_programme_cours(self):
        mgl7260 = Cours("MGL7260", "Exigences et spécifications de systèmes logiciels", "Introduction à l'ingénierie des systèmes. - Modèles de processus des exigences logicielles", 3)
        mgl7361 = Cours("MGL7361", "Principes et applications de la conception de logiciels", "Rôle de la conception dans le cycle de vie du logiciel", 3)
        mgl7460 = Cours("MGL7460", "Réalisation et maintenance de logiciels", "Rôle de la réalisation et de la maintenance dans le cycle de vie du logiciel", 3)

        self.programme1.ajouter_cours(mgl7260)
        self.programme1.ajouter_cours(mgl7361)
        self.programme1.ajouter_cours(mgl7460)

        self.assertTrue(self.programme1.cours, "Le programme ne contient pas de cours ajoutés")

    def test_lien_etudiant_inscription_groupe_cours(self):
        self.groupe_cours1.inscrire_etudiant(self.etudiant1)
        self.groupe_cours2.inscrire_etudiant(self.etudiant1)

        self.assertTrue(self.etudiant1.inscriptions, "L'étudiant n'a pas d'inscription")

    def test_attribution_notes(self):
        self.groupe_cours1.inscrire_etudiant(self.etudiant1)
        self.groupe_cours2.inscrire_etudiant(self.etudiant1)

        self.etudiant1.saisir_note_groupe_cours(self.groupe_cours1, 80)
        self.etudiant1.saisir_note_groupe_cours(self.groupe_cours2, 90)

        self.assertEqual(self.etudiant1.obtenir_note_groupe_cours(self.groupe_cours1), 80, "La note de l'étudiant est incorrecte pour le groupe de cours 1")
        self.assertEqual(self.etudiant1.obtenir_note_groupe_cours(self.groupe_cours2), 90, "La note de l'étudiant est incorrecte pour le groupe de cours 2")

    def test_moyenne_cumulative(self):
        self.groupe_cours1.inscrire_etudiant(self.etudiant1)
        self.groupe_cours2.inscrire_etudiant(self.etudiant1)

        self.etudiant1.saisir_note_groupe_cours(self.groupe_cours1, 80)
        self.etudiant1.saisir_note_groupe_cours(self.groupe_cours2, 90)

        moyenne = self.etudiant1.obtenir_moyenne_cumulative()
        self.assertEqual(moyenne, 85.0, "La moyenne cumulative de l'étudiant est incorrecte")

if __name__ == '__main__':
    unittest.main()
