class Personne:
    def __init__(self, nom: str, age: int):
        self.nom = nom
        self.age = age

    def __repr__(self) -> str:
        return f"Personne(nom='{self.nom}', age={self.age})"