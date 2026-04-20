from src.personne_factory import PersonneFactory
from src.scolarite_manager import ScolariteManager


def main():
    print("--- TP Patterns: Factory + Singleton ---")

    manager = ScolariteManager()
    student = PersonneFactory.creer_etudiant("Alice", 21, "ENSTA-2026-001", 14.0)
    teacher = PersonneFactory.creer_enseignant("Claire", 40, "Architecture", 3200.0)

    manager.ajouter_etudiant(student)

    print(student.afficher_details())
    print(teacher.afficher_details())
    print(f"Singleton manager id: {id(manager)}")


if __name__ == "__main__":
    main()
