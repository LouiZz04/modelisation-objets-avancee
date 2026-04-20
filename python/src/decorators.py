from __future__ import annotations

from abc import ABC

from src.etudiant import Etudiant


class EtudiantDecorator(ABC):
    def __init__(self, composant: Etudiant | EtudiantDecorator):
        self._composant = composant

    @property
    def etudiant(self) -> Etudiant:
        if isinstance(self._composant, Etudiant):
            return self._composant
        return self._composant.etudiant

    def afficher_details(self) -> str:
        return self._composant.afficher_details()

    def __str__(self) -> str:
        return self.afficher_details()


class EtudiantBoursierDecorator(EtudiantDecorator):
    def __init__(self, composant: Etudiant | EtudiantDecorator, montant_bourse: float):
        super().__init__(composant)
        if montant_bourse < 0:
            raise ValueError("Scholarship amount must be positive")
        self.__montant_bourse = float(montant_bourse)

    def afficher_details(self) -> str:
        return f"{super().afficher_details()}, scholarship: {self.__montant_bourse:.2f} EUR"


class EtudiantDelegueDecorator(EtudiantDecorator):
    def __init__(self, composant: Etudiant | EtudiantDecorator, promotion: str):
        super().__init__(composant)
        if not promotion.strip():
            raise ValueError("Promotion cannot be empty")
        self.__promotion = promotion.strip()

    def afficher_details(self) -> str:
        return f"{super().afficher_details()}, delegate of: {self.__promotion}"
