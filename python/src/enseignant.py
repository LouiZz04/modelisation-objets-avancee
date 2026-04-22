from math import isfinite

from src.personne import Personne


class Enseignant(Personne):
    def __init__(self, nom: str, age: int, matiere: str, salaire: float):
        super().__init__(nom, age)
        self.matiere = matiere
        self.salaire = salaire

    @property
    def matiere(self) -> str:
        return self.__matiere

    @matiere.setter
    def matiere(self, value: str) -> None:
        if not isinstance(value, str) or not value.strip():
            raise ValueError("La matiere est obligatoire")
        self.__matiere = value.strip()

    @property
    def salaire(self) -> float:
        return self.__salaire

    @salaire.setter
    def salaire(self, value: float) -> None:
        if not isinstance(value, (int, float)) or isinstance(value, bool) or not isfinite(float(value)):
            raise TypeError("Le salaire doit etre un nombre")
        if value < 0:
            raise ValueError(f"Le salaire doit etre positif ou nul, valeur recue: {value}")
        self.__salaire = float(value)

    def afficher_details(self) -> str:
        return (
            f"Enseignant: {self.nom}, age: {self.age}, "
            f"matiere: {self.matiere}, "
            f"salaire: {self.salaire:.2f}"
        )

    def __repr__(self) -> str:
        return (
            "Enseignant("
            f"nom='{self.nom}', "
            f"age={self.age}, "
            f"matiere='{self.matiere}', "
            f"salaire={self.salaire}"
            ")"
        )
