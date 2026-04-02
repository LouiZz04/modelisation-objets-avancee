import pytest
from src.personne import Personne
from src.etudiant import Etudiant
from src.cours import Cours

def test_etudiant_creation_et_heritage():
    e = Etudiant("Bob", 22, "E12345", 15.0)
    assert e.nom == "Bob"
    assert e.age == 22
    assert e.numero_etudiant == "E12345"
    assert e.moyenne == 15.0
    assert e.liste_cours == []

    assert isinstance(e, Personne) is True

def test_etudiant_ajouter_cours():
    e = Etudiant("Bob", 22, "E12345")
    c = Cours("UML", "Prof. X")
    e.ajouter_cours(c)
    assert len(e.liste_cours) == 1
    assert e.liste_cours[0] == c

def test_moyenne_invalide_leve_exception():
    e = Etudiant("Alice", 20, "E99999", 10.0)
    with pytest.raises(ValueError):
        e.moyenne = 25  # invalide : > 20

def test_moyenne_negative_leve_exception():
    e = Etudiant("Alice", 20, "E99999", 10.0)
    with pytest.raises(ValueError):
        e.moyenne = -1  # invalide : < 0

def test_moyenne_valide():
    e = Etudiant("Alice", 20, "E99999", 10.0)
    e.moyenne = 18.5
    assert e.moyenne == 18.5

def test_age_invalide_leve_exception():
    with pytest.raises(ValueError):
        Etudiant("Alice", 150, "E99999")  # âge > 100

def test_age_negatif_leve_exception():
    with pytest.raises(ValueError):
        Etudiant("Alice", -5, "E99999")  # âge < 0