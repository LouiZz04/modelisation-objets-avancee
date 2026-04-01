from src.cours import Cours
from src.etudiant import Etudiant

def main():
    print("--- Démonstration TP1 : Héritage ---")
    
    # Création des cours
    c1 = Cours("Modélisation Objets Avancée", "Prof. X")
    c2 = Cours("Architecture Logicielle", "Prof. Y")
    
    # Création d'un étudiant
    etudiant1 = Etudiant("Alice", 21, "ENSTA-2026-001", 16.5)
    
    # Ajout des cours
    etudiant1.ajouter_cours(c1)
    etudiant1.ajouter_cours(c2)
    
    # Affichage
    print(etudiant1)

if __name__ == "__main__":
    main()