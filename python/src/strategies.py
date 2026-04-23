from __future__ import annotations

from abc import ABC, abstractmethod
from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from src.etudiant import Etudiant


class MentionStrategy(ABC):
    @abstractmethod
    def calculer_mention(self, moyenne: float) -> str:
        raise NotImplementedError


class MentionStandardStrategy(MentionStrategy):
    def calculer_mention(self, moyenne: float) -> str:
        if moyenne >= 16:
            return "Tres bien"
        if moyenne >= 14:
            return "Bien"
        if moyenne >= 12:
            return "Assez bien"
        if moyenne >= 10:
            return "Passable"
        return "Ajourne"


class MentionBienveillanteStrategy(MentionStrategy):
    def calculer_mention(self, moyenne: float) -> str:
        moyenne_majoree = min(20.0, moyenne + 1.0)
        return MentionStandardStrategy().calculer_mention(moyenne_majoree)


class TriEtudiantsStrategy(ABC):
    @abstractmethod
    def trier(self, etudiants: list[Etudiant]) -> list[Etudiant]:
        raise NotImplementedError


class TriParNomStrategy(TriEtudiantsStrategy):
    def trier(self, etudiants: list[Etudiant]) -> list[Etudiant]:
        return sorted(etudiants, key=lambda etudiant: etudiant.nom.lower())


class TriParMoyenneStrategy(TriEtudiantsStrategy):
    def trier(self, etudiants: list[Etudiant]) -> list[Etudiant]:
        return sorted(etudiants, key=lambda etudiant: etudiant.moyenne, reverse=True)
