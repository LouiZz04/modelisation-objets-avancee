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
    
    # Vérification stricte de l'héritage demandée dans le TP
    assert isinstance(e, Personne) is True 

def test_etudiant_ajouter_cours():
    e = Etudiant("Bob", 22, "E12345")
    c = Cours("UML", "Prof. X")
    e.ajouter_cours(c)
    assert len(e.liste_cours) == 1
    assert e.liste_cours[0] == c