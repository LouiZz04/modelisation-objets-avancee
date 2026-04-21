from src.etudiant import Etudiant


def main():
    print("--- TP2: Encapsulation ---")

    etudiant = Etudiant("Alice", 21, "ENSTA-2026-001", 14.0)
    print(etudiant.afficher_details())

    try:
        etudiant.moyenne = 25
    except ValueError as error:
        print(f"Modification refusee: {error}")


if __name__ == "__main__":
    main()
