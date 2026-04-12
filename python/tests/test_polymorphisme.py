import pytest

from src.cours import Cours
from src.enseignant import Enseignant
from src.etudiant import Etudiant
from src.personne import Personne


def test_teacher_creation():
    teacher = Enseignant("Claire", 40, "Mathematics", 3200.0)

    assert teacher.nom == "Claire"
    assert teacher.age == 40
    assert teacher.matiere == "Mathematics"
    assert teacher.salaire == 3200.0


def test_teacher_rejects_empty_subject():
    with pytest.raises(ValueError):
        Enseignant("Claire", 40, "", 3200.0)


def test_teacher_rejects_negative_salary():
    with pytest.raises(ValueError):
        Enseignant("Claire", 40, "Mathematics", -10.0)


def test_teacher_afficher_details():
    teacher = Enseignant("Claire", 40, "Mathematics", 3200.0)
    details = teacher.afficher_details()

    assert "Claire" in details
    assert "Mathematics" in details
    assert "3200.00" in details


def test_person_list_uses_polymorphism():
    student = Etudiant("Alice", 21, "ENSTA-001", 16.5)
    student.ajouter_cours(Cours("Python", "Teacher X"))
    teacher = Enseignant("Claire", 40, "Mathematics", 3200.0)

    people: list[Personne] = [student, teacher]
    details = [person.afficher_details() for person in people]

    assert "student id" in details[0]
    assert "average" in details[0]
    assert "subject" in details[1]
    assert "salary" in details[1]
