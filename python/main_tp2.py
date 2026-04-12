from src.etudiant import Etudiant


def main():
    print("--- TP2 Demo: Encapsulation ---")

    student = Etudiant("Alice", 21, "ENSTA-2026-001", 14.0)
    print(student.afficher_details())

    try:
        student.moyenne = 25
    except ValueError as error:
        print(f"Update refused: {error}")


if __name__ == "__main__":
    main()
