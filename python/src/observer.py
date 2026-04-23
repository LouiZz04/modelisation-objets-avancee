from __future__ import annotations

from abc import ABC, abstractmethod
from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from src.etudiant import Etudiant


class EtudiantObserver(ABC):
    @abstractmethod
    def notifier_note_ajoutee(self, etudiant: Etudiant, note: float) -> None:
        raise NotImplementedError
