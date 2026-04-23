from src.cours import Cours
from src.etudiant import Etudiant


def main():
    print("--- TP1: Heritage et composition ---")

    etudiant = Etudiant("Alice", 21, "ENSTA-2026-001", 15.5)
    cours_poo = Cours("POO", "M. Dupont")
    cours_algo = Cours("Algorithmique", "Mme Martin")

    etudiant.ajouter_cours(cours_poo)
    etudiant.ajouter_cours(cours_algo)

    print(etudiant.afficher_details())
    print("Cours suivis:")
    for cours in etudiant.liste_cours:
        print(f"- {cours}")



if __name__ == "__main__":
    main()
