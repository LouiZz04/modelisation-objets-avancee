from src.personne import Personne
from src.cours import Cours

class Etudiant(Personne):
    def __init__(self, nom: str, age: int, numero_etudiant: str, moyenne: float = 0.0):
        super().__init__(nom, age)
        self.numero_etudiant = numero_etudiant
        self.moyenne = moyenne
        self.liste_cours = []

    def ajouter_cours(self, cours: Cours):
        self.liste_cours.append(cours)

    def __str__(self) -> str:
        cours_str = ", ".join([c.nom_cours for c in self.liste_cours])
        return f"Etudiant {self.nom} (Age: {self.age}, Num: {self.numero_etudiant}, Moy: {self.moyenne}) - Cours: [{cours_str}]"

    def __repr__(self) -> str:
        return f"Etudiant(nom='{self.nom}', age={self.age}, numero='{self.numero_etudiant}', moyenne={self.moyenne})"