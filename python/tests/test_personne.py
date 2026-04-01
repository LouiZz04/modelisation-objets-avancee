import pytest
from src.personne import Personne

def test_personne_creation():
    p = Personne("Alice", 20)
    assert p.nom == "Alice"
    assert p.age == 20