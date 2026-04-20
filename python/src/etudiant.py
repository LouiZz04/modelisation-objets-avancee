from __future__ import annotations

from typing import TYPE_CHECKING

from src.cours import Cours
from src.personne import Personne
from src.strategies import MentionStandardStrategy, MentionStrategy

if TYPE_CHECKING:
    from src.observer import EtudiantObserver


class Etudiant(Personne):
    def __init__(self, nom: str, age: int, numero_etudiant: str, moyenne: float = 0.0):
        super().__init__(nom, age)
        if not isinstance(numero_etudiant, str) or not numero_etudiant.strip():
            raise ValueError("Student number cannot be empty")

        self.__numero_etudiant = numero_etudiant.strip()
        self.__liste_cours: list[Cours] = []
        self.__notes: list[float] = []
        self.__observateurs: list[EtudiantObserver] = []
        self.__mention_strategy: MentionStrategy = MentionStandardStrategy()
        self.moyenne = moyenne

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
        self.__moyenne = float(value)

    @property
    def liste_cours(self) -> tuple[Cours, ...]:
        return tuple(self.__liste_cours)

    @property
    def notes(self) -> tuple[float, ...]:
        return tuple(self.__notes)

    def ajouter_cours(self, cours: Cours) -> None:
        if not isinstance(cours, Cours):
            raise TypeError("Course must be a Cours instance")
        self.__liste_cours.append(cours)

    def ajouter_note(self, note: float) -> None:
        if note < 0 or note > 20:
            raise ValueError(f"Grade must be between 0 and 20, got: {note}")
        self.__notes.append(float(note))
        self.moyenne = sum(self.__notes) / len(self.__notes)
        self.__notifier_observateurs(float(note))

    def ajouter_observateur(self, observateur: EtudiantObserver) -> None:
        if observateur not in self.__observateurs:
            self.__observateurs.append(observateur)

    def retirer_observateur(self, observateur: EtudiantObserver) -> None:
        if observateur in self.__observateurs:
            self.__observateurs.remove(observateur)

    def definir_mention_strategy(self, strategy: MentionStrategy) -> None:
        if not isinstance(strategy, MentionStrategy):
            raise TypeError("Strategy must inherit from MentionStrategy")
        self.__mention_strategy = strategy

    def calculer_mention(self) -> str:
        return self.__mention_strategy.calculer_mention(self.moyenne)

    def afficher_details(self) -> str:
        cours_str = ", ".join(cours.nom_cours for cours in self.__liste_cours) or "No courses"
        return (
            f"Student: {self.nom}, age: {self.age}, "
            f"student id: {self.numero_etudiant}, "
            f"average: {self.moyenne:.1f}, "
            f"mention: {self.calculer_mention()}, "
            f"courses: {cours_str}"
        )

    def __notifier_observateurs(self, note: float) -> None:
        for observateur in self.__observateurs:
            observateur.notifier_note_ajoutee(self, note)

    def __repr__(self) -> str:
        return (
            "Etudiant("
            f"nom='{self.nom}', "
            f"age={self.age}, "
            f"numero_etudiant='{self.numero_etudiant}', "
            f"moyenne={self.moyenne}"
            ")"
        )
