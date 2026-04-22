from src.enseignant import Enseignant
from src.etudiant import Etudiant
from src.personne import Personne


class PersonneFactory:
    @staticmethod
    def creer_etudiant(nom: str, age: int, numero_etudiant: str, moyenne: float = 0.0) -> Etudiant:
        return Etudiant(nom, age, numero_etudiant, moyenne)

    @staticmethod
    def creer_enseignant(nom: str, age: int, matiere: str, salaire: float) -> Enseignant:
        return Enseignant(nom, age, matiere, salaire)

    @staticmethod
    def creer_personne(type_personne: str, **kwargs: object) -> Personne:
        if not isinstance(type_personne, str) or not type_personne.strip():
            raise ValueError("Le type de personne est obligatoire")
        type_normalise = type_personne.strip().lower()
        if type_normalise == "etudiant":
            return PersonneFactory.creer_etudiant(
                kwargs["nom"],
                int(kwargs["age"]),
                kwargs["numero_etudiant"],
                float(kwargs.get("moyenne", 0.0)),
            )
        if type_normalise == "enseignant":
            return PersonneFactory.creer_enseignant(
                kwargs["nom"],
                int(kwargs["age"]),
                kwargs["matiere"],
                float(kwargs["salaire"]),
            )
        raise ValueError(f"Type de personne inconnu: {type_personne}")
