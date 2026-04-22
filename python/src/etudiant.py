from __future__ import annotations

from math import isfinite

from src.cours import Cours
from src.observer import EtudiantObserver
from src.personne import Personne
from src.strategies import MentionStandardStrategy, MentionStrategy


def _note_valide(value: float, label: str) -> float:
    if not isinstance(value, (int, float)) or isinstance(value, bool) or not isfinite(float(value)):
        raise TypeError(f"{label} doit etre un nombre")
    note = float(value)
    if note < 0 or note > 20:
        raise ValueError(f"{label} doit etre comprise entre 0 et 20, valeur recue: {value}")
    return note


class Etudiant(Personne):
    def __init__(self, nom: str, age: int, numero_etudiant: str, moyenne: float = 0.0):
        super().__init__(nom, age)
        if not isinstance(numero_etudiant, str) or not numero_etudiant.strip():
            raise ValueError("Le numero etudiant est obligatoire")

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
        self.__moyenne = _note_valide(value, "La moyenne")

    @property
    def liste_cours(self) -> tuple[Cours, ...]:
        return tuple(self.__liste_cours)

    @property
    def notes(self) -> tuple[float, ...]:
        return tuple(self.__notes)

    def ajouter_cours(self, cours: Cours) -> None:
        if not isinstance(cours, Cours):
            raise TypeError("Le cours doit etre une instance de Cours")
        self.__liste_cours.append(cours)

    def ajouter_note(self, note: float) -> None:
        note_validee = _note_valide(note, "La note")
        self.__notes.append(note_validee)
        self.moyenne = sum(self.__notes) / len(self.__notes)
        self.__notifier_observateurs(note_validee)

    def ajouter_observateur(self, observateur: EtudiantObserver) -> None:
        if not isinstance(observateur, EtudiantObserver):
            raise TypeError("L'observateur doit etre un EtudiantObserver")
        if observateur not in self.__observateurs:
            self.__observateurs.append(observateur)

    def retirer_observateur(self, observateur: EtudiantObserver) -> None:
        if observateur in self.__observateurs:
            self.__observateurs.remove(observateur)

    def definir_mention_strategy(self, strategy: MentionStrategy) -> None:
        if not isinstance(strategy, MentionStrategy):
            raise TypeError("La strategie doit etre une MentionStrategy")
        self.__mention_strategy = strategy

    def calculer_mention(self) -> str:
        return self.__mention_strategy.calculer_mention(self.moyenne)

    def afficher_details(self) -> str:
        cours_str = ", ".join(cours.nom_cours for cours in self.__liste_cours) or "aucun"
        return (
            f"Etudiant: {self.nom}, age: {self.age}, "
            f"numero: {self.numero_etudiant}, "
            f"moyenne: {self.moyenne:.1f}, "
            f"mention: {self.calculer_mention()}, "
            f"cours: {cours_str}"
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
