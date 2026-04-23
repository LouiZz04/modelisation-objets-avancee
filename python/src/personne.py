from abc import ABC, abstractmethod


class Personne(ABC):
    def __init__(self, nom: str, age: int):
        self.nom = nom
        self.age = age

    @property
    def nom(self) -> str:
        return self.__nom

    @nom.setter
    def nom(self, value: str) -> None:
        if not isinstance(value, str) or not value.strip():
            raise ValueError("Le nom est obligatoire")
        self.__nom = value.strip()

    @property
    def age(self) -> int:
        return self.__age

    @age.setter
    def age(self, value: int) -> None:
        if not isinstance(value, int) or isinstance(value, bool):
            raise TypeError(f"L'age doit etre un entier, valeur recue: {value}")
        if value <= 0 or value > 100:
            raise ValueError(f"L'age doit etre compris entre 1 et 100, valeur recue: {value}")
        self.__age = value

    @abstractmethod
    def afficher_details(self) -> str:
        raise NotImplementedError

    def __str__(self) -> str:
        return self.afficher_details()

    def __repr__(self) -> str:
        return f"{self.__class__.__name__}(nom='{self.nom}', age={self.age})"
