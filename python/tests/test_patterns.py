import pytest

from src.decorators import EtudiantBoursierDecorator, EtudiantDelegueDecorator
from src.enseignant import Enseignant
from src.etudiant import Etudiant
from src.legacy_cours_adapter import LegacyCoursAdapter, LegacyCoursSource
from src.personne_factory import PersonneFactory
from src.scolarite_manager import ScolariteManager
from src.strategies import (
    MentionBienveillanteStrategy,
    TriParMoyenneStrategy,
)


def setup_function():
    ScolariteManager.reset_instance()


def test_singleton_manager_returns_same_instance():
    manager_a = ScolariteManager()
    manager_b = ScolariteManager()

    assert manager_a is manager_b


def test_factory_creates_expected_person_types():
    student = PersonneFactory.creer_personne(
        "etudiant",
        nom="Alice",
        age=20,
        numero_etudiant="E001",
        moyenne=12.0,
    )
    teacher = PersonneFactory.creer_personne(
        "enseignant",
        nom="Claire",
        age=40,
        matiere="Architecture",
        salaire=3200.0,
    )

    assert isinstance(student, Etudiant)
    assert isinstance(teacher, Enseignant)


def test_adapter_converts_legacy_course_strings():
    source = LegacyCoursSource(["Patterns | Mme Martin", "UML | M. Dupont"])
    adapter = LegacyCoursAdapter(source)

    courses = adapter.recuperer_cours()

    assert len(courses) == 2
    assert courses[0].nom_cours == "Patterns"
    assert courses[1].professeur_responsable == "M. Dupont"


def test_adapter_rejects_invalid_legacy_course_string():
    with pytest.raises(ValueError):
        LegacyCoursAdapter.convertir_chaine("Patterns")


def test_decorator_adds_extra_details():
    student = Etudiant("Alice", 21, "E001", 14.0)
    decorated = EtudiantDelegueDecorator(EtudiantBoursierDecorator(student, 250.0), "ING2")

    details = decorated.afficher_details()

    assert "bourse" in details
    assert "delegue" in details


def test_decorator_rejects_invalid_data():
    student = Etudiant("Alice", 21, "E001", 14.0)

    with pytest.raises(ValueError):
        EtudiantBoursierDecorator(student, -1.0)

    with pytest.raises(ValueError):
        EtudiantDelegueDecorator(student, " ")


def test_factory_rejects_empty_person_type():
    with pytest.raises(ValueError):
        PersonneFactory.creer_personne(" ", nom="Alice", age=20)


def test_strategy_can_change_student_mention():
    student = Etudiant("Alice", 21, "E001", 13.0)

    assert student.calculer_mention() == "Assez bien"

    student.definir_mention_strategy(MentionBienveillanteStrategy())

    assert student.calculer_mention() == "Bien"


def test_manager_observes_new_grades_and_sorts_students():
    manager = ScolariteManager()
    alice = Etudiant("Alice", 21, "E001", 0.0)
    bob = Etudiant("Bob", 22, "E002", 0.0)

    manager.ajouter_etudiant(alice)
    manager.ajouter_etudiant(bob)

    alice.ajouter_note(12)
    alice.ajouter_note(14)
    bob.ajouter_note(18)

    manager.definir_tri_strategy(TriParMoyenneStrategy())

    assert manager.nombre_notes == 3
    assert manager.moyenne_generale == 44 / 3
    assert list(manager.notifications) == ["E001:12.0", "E001:14.0", "E002:18.0"]
    assert [etudiant.nom for etudiant in manager.etudiants_tries()] == ["Bob", "Alice"]
