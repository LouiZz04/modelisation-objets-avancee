import pytest
from src.cours import Cours

def test_cours_creation():
    c = Cours("Python Avancé", "M. Dupont")
    assert c.nom_cours == "Python Avancé"
    assert c.professeur_responsable == "M. Dupont"

def test_cours_str():
    c = Cours("Java Avancé", "Mme. Martin")
    assert "Java" in str(c)
    assert "Mme. Martin" in str(c)