import pytest
from src.cours import Cours


def test_course_creation():
    course = Cours("Advanced Python", "Mr. Dupont")
    assert course.nom_cours == "Advanced Python"
    assert course.professeur_responsable == "Mr. Dupont"


def test_course_string():
    course = Cours("Advanced Java", "Ms. Martin")
    assert "Java" in str(course)
    assert "Ms. Martin" in str(course)


def test_blank_course_name_raises_error():
    with pytest.raises(ValueError):
        Cours("", "Ms. Martin")


def test_blank_teacher_name_raises_error():
    with pytest.raises(ValueError):
        Cours("UML", "   ")
