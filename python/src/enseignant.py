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
            raise ValueError("Subject cannot be empty")
        self.__matiere = value.strip()

    @property
    def salaire(self) -> float:
        return self.__salaire

    @salaire.setter
    def salaire(self, value: float) -> None:
        if value < 0:
            raise ValueError(f"Salary must be positive, got: {value}")
        self.__salaire = value

    def afficher_details(self) -> str:
        return (
            f"{super().afficher_details()}, "
            f"subject: {self.matiere}, "
            f"salary: {self.salaire:.2f}"
        )

    def __str__(self) -> str:
        return self.afficher_details()

    def __repr__(self) -> str:
        return (
            "Enseignant("
            f"nom='{self.nom}', "
            f"age={self.age}, "
            f"matiere='{self.matiere}', "
            f"salaire={self.salaire}"
            ")"
        )
