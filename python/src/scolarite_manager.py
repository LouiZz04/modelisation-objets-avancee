from __future__ import annotations

from src.etudiant import Etudiant
from src.observer import EtudiantObserver
from src.strategies import TriEtudiantsStrategy, TriParNomStrategy


class ScolariteManager(EtudiantObserver):
    _instance: ScolariteManager | None = None

    def __new__(cls) -> ScolariteManager:
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance._initialized = False
        return cls._instance

    def __init__(self) -> None:
        if self._initialized:
            return
        self.__etudiants: list[Etudiant] = []
        self.__notifications: list[str] = []
        self.__nombre_notes = 0
        self.__moyenne_generale = 0.0
        self.__tri_strategy: TriEtudiantsStrategy = TriParNomStrategy()
        self._initialized = True

    @classmethod
    def reset_instance(cls) -> None:
        cls._instance = None

    @property
    def etudiants(self) -> tuple[Etudiant, ...]:
        return tuple(self.__etudiants)

    @property
    def notifications(self) -> tuple[str, ...]:
        return tuple(self.__notifications)

    @property
    def nombre_notes(self) -> int:
        return self.__nombre_notes

    @property
    def moyenne_generale(self) -> float:
        return self.__moyenne_generale

    def ajouter_etudiant(self, etudiant: Etudiant) -> None:
        if not isinstance(etudiant, Etudiant):
            raise TypeError("Manager only accepts Etudiant instances")
        if etudiant not in self.__etudiants:
            self.__etudiants.append(etudiant)
            etudiant.ajouter_observateur(self)
            self.__mettre_a_jour_statistiques()

    def definir_tri_strategy(self, strategy: TriEtudiantsStrategy) -> None:
        if not isinstance(strategy, TriEtudiantsStrategy):
            raise TypeError("Strategy must inherit from TriEtudiantsStrategy")
        self.__tri_strategy = strategy

    def etudiants_tries(self) -> tuple[Etudiant, ...]:
        return tuple(self.__tri_strategy.trier(list(self.__etudiants)))

    def notifier_note_ajoutee(self, etudiant: Etudiant, note: float) -> None:
        self.__notifications.append(f"{etudiant.numero_etudiant}:{note:.1f}")
        self.__mettre_a_jour_statistiques()

    def __mettre_a_jour_statistiques(self) -> None:
        toutes_les_notes = [note for etudiant in self.__etudiants for note in etudiant.notes]
        self.__nombre_notes = len(toutes_les_notes)
        if toutes_les_notes:
            self.__moyenne_generale = sum(toutes_les_notes) / self.__nombre_notes
        else:
            self.__moyenne_generale = 0.0
