from src.cours import Cours
from src.etudiant import Etudiant


def main():
    print("--- TP1 Demo: Inheritance ---")

    c1 = Cours("Advanced Object Modeling", "Teacher X")
    c2 = Cours("Software Architecture", "Teacher Y")

    etudiant1 = Etudiant("Alice", 21, "ENSTA-2026-001", 16.5)

    etudiant1.ajouter_cours(c1)
    etudiant1.ajouter_cours(c2)

    print(etudiant1)

    print("\n--- Technical View (repr) ---")
    print(repr(etudiant1))
    print(repr(c1))


if __name__ == "__main__":
    main()
