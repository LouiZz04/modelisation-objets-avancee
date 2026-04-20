from src.etudiant import Etudiant
from src.strategies import MentionBienveillanteStrategy


def main():
    print("--- TP Patterns: Strategy ---")

    student = Etudiant("Alice", 21, "ENSTA-2026-001", 13.0)
    print(student.afficher_details())

    student.definir_mention_strategy(MentionBienveillanteStrategy())
    print(student.afficher_details())


if __name__ == "__main__":
    main()
