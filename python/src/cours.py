class Cours:
    def __init__(self, nom_cours: str, professeur_responsable: str):
        self.nom_cours = nom_cours
        self.professeur_responsable = professeur_responsable

    def __str__(self) -> str:
        return f"Cours: {self.nom_cours} (Prof: {self.professeur_responsable})"

    def __repr__(self) -> str:
        return f"Cours(nom_cours='{self.nom_cours}', professeur='{self.professeur_responsable}')"