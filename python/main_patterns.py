from src.decorators import EtudiantBoursierDecorator, EtudiantDelegueDecorator
from src.legacy_cours_adapter import LegacyCoursAdapter, LegacyCoursSource
from src.personne_factory import PersonneFactory
from src.scolarite_manager import ScolariteManager
from src.strategies import MentionBienveillanteStrategy, TriParMoyenneStrategy


def main():
    print("--- TP Patterns: demo complete ---")

    manager = ScolariteManager()

    alice = PersonneFactory.creer_etudiant("Alice", 21, "ENSTA-001", 0.0)
    bob = PersonneFactory.creer_etudiant("Bob", 22, "ENSTA-002", 0.0)
    enseignant = PersonneFactory.creer_enseignant("Claire", 40, "Architecture logicielle", 3200.0)

    manager.ajouter_etudiant(alice)
    manager.ajouter_etudiant(bob)

    source = LegacyCoursSource(
        [
            "Design Patterns | Claire Martin",
            "Architecture Logicielle | Marc Lebrun",
        ]
    )
    adapter = LegacyCoursAdapter(source)
    for cours in adapter.recuperer_cours():
        alice.ajouter_cours(cours)
        bob.ajouter_cours(cours)

    alice.ajouter_note(12)
    alice.ajouter_note(14)
    bob.ajouter_note(17)
    bob.definir_mention_strategy(MentionBienveillanteStrategy())

    manager.definir_tri_strategy(TriParMoyenneStrategy())

    bob_decore = EtudiantDelegueDecorator(EtudiantBoursierDecorator(bob, 250.0), "ING2")

    print(enseignant.afficher_details())
    print(alice.afficher_details())
    print(bob_decore.afficher_details())
    print(f"Moyenne generale: {manager.moyenne_generale:.2f}")
    print("Tri par moyenne:", [etudiant.nom for etudiant in manager.etudiants_tries()])
    print("Notifications:", list(manager.notifications))


if __name__ == "__main__":
    main()
