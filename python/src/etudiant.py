from src.personne import Personne
from src.cours import Cours


class Etudiant(Personne):
    def __init__(self, nom: str, age: int, numero_etudiant: str, moyenne: float = 0.0):
        super().__init__(nom, age)
        if not isinstance(numero_etudiant, str) or not numero_etudiant.strip():
            raise ValueError("Student number cannot be empty")

        self.__numero_etudiant = numero_etudiant.strip()
        self.moyenne = moyenne
        self.__liste_cours: list[Cours] = []

    @property
    def numero_etudiant(self) -> str:
        return self.__numero_etudiant

    @property
    def moyenne(self) -> float:
        return self.__moyenne

    @moyenne.setter
    def moyenne(self, value: float) -> None:
        if value < 0 or value > 20:
            raise ValueError(f"Average must be between 0 and 20, got: {value}")
        self.__moyenne = value

    @property
    def liste_cours(self) -> tuple[Cours, ...]:
        return tuple(self.__liste_cours)

    def ajouter_cours(self, cours: Cours) -> None:
        if not isinstance(cours, Cours):
            raise TypeError("Course must be a Cours instance")
        self.__liste_cours.append(cours)

    def afficher_details(self) -> str:
        cours_str = ", ".join(cours.nom_cours for cours in self.__liste_cours) or "No courses"
        return (
            f"{super().afficher_details()}, "
            f"student id: {self.numero_etudiant}, "
            f"average: {self.moyenne:.1f}, "
            f"courses: {cours_str}"
        )

    def __str__(self) -> str:
        return self.afficher_details()

    def __repr__(self) -> str:
        return (
            "Etudiant("
            f"nom='{self.nom}', "
            f"age={self.age}, "
            f"numero_etudiant='{self.numero_etudiant}', "
            f"moyenne={self.moyenne}"
            ")"
        )
