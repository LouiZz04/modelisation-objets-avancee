class Cours:
    def __init__(self, nom_cours: str, professeur_responsable: str):
        if not isinstance(nom_cours, str) or not nom_cours.strip():
            raise ValueError("Le nom du cours est obligatoire")
        if not isinstance(professeur_responsable, str) or not professeur_responsable.strip():
            raise ValueError("Le nom du professeur est obligatoire")

        self.__nom_cours = nom_cours.strip()
        self.__professeur_responsable = professeur_responsable.strip()

    @property
    def nom_cours(self) -> str:
        return self.__nom_cours

    @property
    def professeur_responsable(self) -> str:
        return self.__professeur_responsable

    def __str__(self) -> str:
        return f"Cours: {self.nom_cours} (professeur: {self.professeur_responsable})"

    def __repr__(self) -> str:
        return (
            "Cours("
            f"nom_cours='{self.nom_cours}', "
            f"professeur_responsable='{self.professeur_responsable}'"
            ")"
        )
