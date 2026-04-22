import pytest
from src.cours import Cours


def test_course_creation():
    course = Cours("POO", "M. Dupont")
    assert course.nom_cours == "POO"
    assert course.professeur_responsable == "M. Dupont"


def test_course_string():
    course = Cours("Java", "Mme Martin")
    assert "Java" in str(course)
    assert "Mme Martin" in str(course)


def test_blank_course_name_raises_error():
    with pytest.raises(ValueError):
        Cours("", "Mme Martin")


def test_blank_teacher_name_raises_error():
    with pytest.raises(ValueError):
        Cours("UML", "   ")
