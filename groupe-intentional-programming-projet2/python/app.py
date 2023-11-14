class Cours:
    def __init__(self, sigle, titre, description, nombre_credits, prerequis=None):
        self.sigle = sigle
        self.titre = titre
        self.description = description
        self.nombre_credits = nombre_credits
        self.prerequis = prerequis if prerequis is not None else []

class Programme:
    def __init__(self, code, titre, nombre_credits):
        self.code = code
        self.titre = titre
        self.nombre_credits = nombre_credits
        self.cours = []

    def ajouter_cours(self, cours):
        self.cours.append(cours)

class GroupeCours:
    def __init__(self, cours, annee, session, enseignant):
        self.cours = cours
        self.annee = annee
        self.session = session
        self.enseignant = enseignant
        self.inscriptions = []

    def inscrire_etudiant(self, etudiant):
        inscription = Inscription(etudiant, self, note_numerique=0, reussi=False)
        self.inscriptions.append(inscription)

class Inscription:
    def __init__(self, etudiant, groupe_cours, note_numerique, reussi):
        self.etudiant = etudiant
        self.groupe_cours = groupe_cours
        self.note_numerique = note_numerique
        self.reussi = reussi

class Session:
    Automne = "Automne"
    Hiver = "Hiver"
    Printemps = "Printemps"
    Ete = "Été"

class Etudiant:
    def __init__(self, nom, prenom, code_permanent, programme):
        self.nom = nom
        self.prenom = prenom
        self.code_permanent = code_permanent
        self.programme = programme
        self.inscriptions = []

    def inscrire_groupe_cours(self, groupe_cours):
        inscription = Inscription(self, groupe_cours, note_numerique=None, reussi=False)
        self.inscriptions.append(inscription)
        groupe_cours.inscriptions.append(inscription)  # Ajout de l'inscription au groupe de cours

    def saisir_note_groupe_cours(self, groupe_cours, note):
        for inscription in self.inscriptions:
            if inscription.groupe_cours == groupe_cours:
                inscription.note_numerique = note
                inscription.reussi = note >= 50  # Met à jour l'état de réussite en fonction de la note

    def obtenir_note_groupe_cours(self, groupe_cours):
        for inscription in self.inscriptions:
            if inscription.groupe_cours == groupe_cours:
                return inscription.note_numerique
        return None  # Retourne None si la note n'est pas disponible

    def obtenir_moyenne_cumulative(self):
        total_notes = 0
        total_credits = 0
        for inscription in self.inscriptions:
            if inscription.note_numerique is not None:
                total_notes += inscription.note_numerique * inscription.groupe_cours.cours.nombre_credits
                total_credits += inscription.groupe_cours.cours.nombre_credits
        if total_credits == 0:
            return 0
        return total_notes / total_credits
