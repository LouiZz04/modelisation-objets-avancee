from __future__ import annotations

from abc import ABC
from math import isfinite

from src.etudiant import Etudiant


class EtudiantDecorator(ABC):
    def __init__(self, composant: Etudiant | EtudiantDecorator):
        if not isinstance(composant, (Etudiant, EtudiantDecorator)):
            raise TypeError("Le composant doit etre un etudiant")
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
        if (
            not isinstance(montant_bourse, (int, float))
            or isinstance(montant_bourse, bool)
            or not isfinite(float(montant_bourse))
        ):
            raise TypeError("Le montant de la bourse doit etre un nombre")
        if montant_bourse < 0:
            raise ValueError("Le montant de la bourse doit etre positif ou nul")
        self.__montant_bourse = float(montant_bourse)

    def afficher_details(self) -> str:
        return f"{super().afficher_details()}, bourse: {self.__montant_bourse:.2f} EUR"


class EtudiantDelegueDecorator(EtudiantDecorator):
    def __init__(self, composant: Etudiant | EtudiantDecorator, promotion: str):
        super().__init__(composant)
        if not isinstance(promotion, str) or not promotion.strip():
            raise ValueError("La promotion est obligatoire")
        self.__promotion = promotion.strip()

    def afficher_details(self) -> str:
        return f"{super().afficher_details()}, delegue de: {self.__promotion}"
