from src.cours import Cours
from src.enseignant import Enseignant
from src.etudiant import Etudiant
from src.personne import Personne


def main():
    print("--- TP3: Polymorphisme ---")

    etudiant = Etudiant("Alice", 21, "ENSTA-001", 16.5)
    etudiant.ajouter_cours(Cours("Python", "Mme Martin"))
    enseignant = Enseignant("Claire", 40, "Mathematiques", 3200.0)

    personnes: list[Personne] = [etudiant, enseignant]

    for personne in personnes:
        print(personne.afficher_details())


if __name__ == "__main__":
    main()
