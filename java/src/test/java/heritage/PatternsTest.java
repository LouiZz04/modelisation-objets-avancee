package heritage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatternsTest {

    @AfterEach
    void resetSingleton() {
        ScolariteManager.resetInstance();
    }

    @Test
    void shouldUseSingletonManager() {
        ScolariteManager managerA = ScolariteManager.getInstance();
        ScolariteManager managerB = ScolariteManager.getInstance();

        assertSame(managerA, managerB);
    }

    @Test
    void shouldUseFactoryToCreatePeople() {
        Personne student = PersonneFactory.creerPersonne("etudiant", "Alice", 20, "E001", 12.0);
        Personne teacher = PersonneFactory.creerPersonne("enseignant", "Claire", 40, "Architecture", 3200.0);

        assertInstanceOf(Etudiant.class, student);
        assertInstanceOf(Enseignant.class, teacher);
    }

    @Test
    void shouldAdaptLegacyCourses() {
        LegacyCoursSource source = new LegacyCoursSource(List.of("Patterns | Mme Martin", "UML | M. Dupont"));
        LegacyCoursAdapter adapter = new LegacyCoursAdapter(source);

        List<Cours> cours = adapter.recupererCours();

        assertEquals(2, cours.size());
        assertEquals("Patterns", cours.get(0).getNomCours());
        assertEquals("M. Dupont", cours.get(1).getProfesseurResponsable());
    }

    @Test
    void shouldDecorateStudentDetails() {
        Etudiant student = new Etudiant("Alice", 21, "E001", 14.0);
        EtudiantView decorated = new EtudiantDelegueDecorator(
            new EtudiantBoursierDecorator(student, 250.0),
            "ING2"
        );

        String result = decorated.afficherDetails();

        assertTrue(result.contains("scholarship"));
        assertTrue(result.contains("delegate"));
    }

    @Test
    void shouldChangeMentionStrategy() {
        Etudiant student = new Etudiant("Alice", 21, "E001", 13.0);

        assertEquals("Assez bien", student.calculerMention());

        student.setMentionStrategy(new MentionBienveillanteStrategy());

        assertEquals("Bien", student.calculerMention());
    }

    @Test
    void shouldObserveNotesAndSortStudents() {
        ScolariteManager manager = ScolariteManager.getInstance();
        Etudiant alice = new Etudiant("Alice", 21, "E001", 0.0);
        Etudiant bob = new Etudiant("Bob", 22, "E002", 0.0);

        manager.ajouterEtudiant(alice);
        manager.ajouterEtudiant(bob);

        alice.ajouterNote(12.0);
        alice.ajouterNote(14.0);
        bob.ajouterNote(18.0);

        manager.setTriStrategy(new TriParMoyenneStrategy());

        assertEquals(3, manager.getNombreNotes());
        assertEquals(44.0 / 3.0, manager.getMoyenneGenerale());
        assertEquals(List.of("E001:12.0", "E001:14.0", "E002:18.0"), manager.getNotifications());
        assertEquals(List.of("Bob", "Alice"), manager.getEtudiantsTries().stream().map(Etudiant::getNom).toList());
    }
}
