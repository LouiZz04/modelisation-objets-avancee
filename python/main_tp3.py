from src.decorators import EtudiantBoursierDecorator, EtudiantDelegueDecorator
from src.legacy_cours_adapter import LegacyCoursAdapter, LegacyCoursSource
from src.personne_factory import PersonneFactory
from src.scolarite_manager import ScolariteManager
from src.strategies import MentionBienveillanteStrategy, TriParMoyenneStrategy


def main():
    print("--- TP Patterns: full demo ---")

    manager = ScolariteManager()

    alice = PersonneFactory.creer_etudiant("Alice", 21, "ENSTA-001", 0.0)
    bob = PersonneFactory.creer_etudiant("Bob", 22, "ENSTA-002", 0.0)
    teacher = PersonneFactory.creer_enseignant("Claire", 40, "Software Engineering", 3200.0)

    manager.ajouter_etudiant(alice)
    manager.ajouter_etudiant(bob)

    legacy_source = LegacyCoursSource(
        [
            "Design Patterns | Claire Martin",
            "Software Architecture | Marc Lebrun",
        ]
    )
    adapter = LegacyCoursAdapter(legacy_source)
    for cours in adapter.recuperer_cours():
        alice.ajouter_cours(cours)
        bob.ajouter_cours(cours)

    alice.ajouter_note(12)
    alice.ajouter_note(14)
    bob.ajouter_note(17)
    bob.definir_mention_strategy(MentionBienveillanteStrategy())

    manager.definir_tri_strategy(TriParMoyenneStrategy())

    decorated = EtudiantDelegueDecorator(EtudiantBoursierDecorator(bob, 250.0), "ING2")

    print(teacher.afficher_details())
    print(alice.afficher_details())
    print(decorated.afficher_details())
    print(f"Global average: {manager.moyenne_generale:.2f}")
    print("Sorted students:", [etudiant.nom for etudiant in manager.etudiants_tries()])
    print("Notifications:", list(manager.notifications))


if __name__ == "__main__":
    main()
