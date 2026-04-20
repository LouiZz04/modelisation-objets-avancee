from src.cours import Cours


class LegacyCoursSource:
    def __init__(self, donnees: list[str]):
        self.__donnees = list(donnees)

    def recuperer_donnees(self) -> tuple[str, ...]:
        return tuple(self.__donnees)


class LegacyCoursAdapter:
    def __init__(self, source: LegacyCoursSource):
        self.__source = source

    @staticmethod
    def convertir_chaine(chaine: str) -> Cours:
        morceaux = [morceau.strip() for morceau in chaine.split("|", maxsplit=1)]
        if len(morceaux) != 2 or not morceaux[0] or not morceaux[1]:
            raise ValueError(f"Invalid legacy course format: {chaine}")
        return Cours(morceaux[0], morceaux[1])

    def recuperer_cours(self) -> tuple[Cours, ...]:
        return tuple(self.convertir_chaine(chaine) for chaine in self.__source.recuperer_donnees())
