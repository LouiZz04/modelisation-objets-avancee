class Personne:
    def __init__(self, nom: str, age: int):
        self.nom = nom
        self.age = age

    @property
    def nom(self) -> str:
        return self.__nom

    @nom.setter
    def nom(self, value: str) -> None:
        if not isinstance(value, str) or not value.strip():
            raise ValueError("Name cannot be empty")
        self.__nom = value.strip()

    @property
    def age(self) -> int:
        return self.__age

    @age.setter
    def age(self, value: int) -> None:
        if value < 0 or value > 100:
            raise ValueError(f"Age must be between 0 and 100, got: {value}")
        self.__age = value

    def afficher_details(self) -> str:
        return f"Person: {self.nom}, age: {self.age}"

    def __str__(self) -> str:
        return self.afficher_details()

    def __repr__(self) -> str:
        return f"Personne(nom='{self.nom}', age={self.age})"
