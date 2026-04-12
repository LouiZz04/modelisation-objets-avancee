import pytest
from src.personne import Personne
from src.etudiant import Etudiant
from src.cours import Cours


def test_student_creation_and_inheritance():
    student = Etudiant("Bob", 22, "E12345", 15.0)
    assert student.nom == "Bob"
    assert student.age == 22
    assert student.numero_etudiant == "E12345"
    assert student.moyenne == 15.0
    assert student.liste_cours == ()

    assert isinstance(student, Personne) is True


def test_student_can_add_course():
    student = Etudiant("Bob", 22, "E12345")
    course = Cours("UML", "Teacher X")
    student.ajouter_cours(course)

    assert len(student.liste_cours) == 1
    assert student.liste_cours[0] == course


def test_invalid_high_average_raises_error():
    student = Etudiant("Alice", 20, "E99999", 10.0)
    with pytest.raises(ValueError):
        student.moyenne = 25


def test_negative_average_raises_error():
    student = Etudiant("Alice", 20, "E99999", 10.0)
    with pytest.raises(ValueError):
        student.moyenne = -1


def test_valid_average_update():
    student = Etudiant("Alice", 20, "E99999", 10.0)
    student.moyenne = 18.5

    assert student.moyenne == 18.5


def test_invalid_high_age_raises_error():
    with pytest.raises(ValueError):
        Etudiant("Alice", 150, "E99999")


def test_negative_age_raises_error():
    with pytest.raises(ValueError):
        Etudiant("Alice", -5, "E99999")


def test_blank_student_number_raises_error():
    with pytest.raises(ValueError):
        Etudiant("Alice", 20, "   ")


def test_student_number_is_read_only():
    student = Etudiant("Alice", 20, "E99999", 10.0)

    with pytest.raises(AttributeError):
        student.numero_etudiant = "NEW-ID"


def test_course_list_is_not_writable():
    student = Etudiant("Alice", 20, "E99999", 10.0)

    with pytest.raises(AttributeError):
        student.liste_cours = []


def test_course_list_is_not_mutable_from_outside():
    student = Etudiant("Alice", 20, "E99999", 10.0)
    course = Cours("Python", "Teacher Y")
    student.ajouter_cours(course)

    with pytest.raises(AttributeError):
        student.liste_cours.append(Cours("Java", "Teacher Z"))


def test_afficher_details_for_student():
    student = Etudiant("Alice", 20, "E99999", 17.5)
    course = Cours("Python", "Teacher Y")
    student.ajouter_cours(course)

    details = student.afficher_details()

    assert "Alice" in details
    assert "17.5" in details
    assert "Python" in details
