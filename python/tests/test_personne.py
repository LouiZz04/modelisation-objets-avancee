import pytest

from src.personne import Personne


class PersonneTestDouble(Personne):
    def afficher_details(self) -> str:
        return f"{self.nom}:{self.age}"


def test_person_creation_through_subclass():
    person = PersonneTestDouble("Alice", 20)
    assert person.nom == "Alice"
    assert person.age == 20


def test_blank_name_raises_error():
    with pytest.raises(ValueError):
        PersonneTestDouble("   ", 20)


def test_invalid_age_raises_error():
    with pytest.raises(ValueError):
        PersonneTestDouble("Alice", 101)


def test_zero_age_raises_error():
    with pytest.raises(ValueError):
        PersonneTestDouble("Alice", 0)


def test_personne_is_abstract():
    with pytest.raises(TypeError):
        Personne("Bob", 22)
