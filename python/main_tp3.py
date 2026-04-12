from src.cours import Cours
from src.enseignant import Enseignant
from src.etudiant import Etudiant
from src.personne import Personne


def main():
    print("--- TP3 Demo: Polymorphism ---")

    student = Etudiant("Alice", 21, "ENSTA-2026-001", 16.5)
    student.ajouter_cours(Cours("Advanced Object Modeling", "Teacher X"))
    student.ajouter_cours(Cours("Software Architecture", "Teacher Y"))

    teacher = Enseignant("Claire", 40, "Mathematics", 3200.0)

    people: list[Personne] = [student, teacher]

    for person in people:
        print(person.afficher_details())


if __name__ == "__main__":
    main()
