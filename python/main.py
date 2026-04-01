from src.cours import Cours
from src.etudiant import Etudiant

def main():
    print("--- Démonstration TP1 : Héritage ---")
    
    c1 = Cours("Modélisation Objets Avancée", "Prof. X")
    c2 = Cours("Architecture Logicielle", "Prof. Y")
    
    etudiant1 = Etudiant("Alice", 21, "ENSTA-2026-001", 16.5)
    
    etudiant1.ajouter_cours(c1)
    etudiant1.ajouter_cours(c2)
    
    print(etudiant1)
    
    print("\n--- Représentation Technique (repr) ---")
    print(repr(etudiant1))
    print(repr(c1))

if __name__ == "__main__":
    main()