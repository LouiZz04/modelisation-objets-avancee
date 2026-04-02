class Personne:
    def __init__(self, nom: str, age: int):
        self.__nom = nom
        self.age = age  # uses setter

    @property
    def nom(self) -> str:
        return self.__nom

    @nom.setter
    def nom(self, value: str):
        self.__nom = value

    @property
    def age(self) -> int:
        return self.__age

    @age.setter
    def age(self, value: int):
        if value < 0 or value > 100:
            raise ValueError(f"L'âge doit être entre 0 et 100, reçu : {value}")
        self.__age = value

    def __repr__(self) -> str:
        return f"Personne(nom='{self.nom}', age={self.age})"