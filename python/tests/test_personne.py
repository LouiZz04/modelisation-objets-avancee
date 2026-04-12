import pytest
from src.personne import Personne


def test_person_creation():
    person = Personne("Alice", 20)
    assert person.nom == "Alice"
    assert person.age == 20


def test_blank_name_raises_error():
    with pytest.raises(ValueError):
        Personne("   ", 20)


def test_invalid_age_raises_error():
    with pytest.raises(ValueError):
        Personne("Alice", 101)


def test_afficher_details_for_person():
    person = Personne("Bob", 22)
    details = person.afficher_details()

    assert "Bob" in details
    assert "22" in details
