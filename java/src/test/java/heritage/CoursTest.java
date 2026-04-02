package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoursTest {

    @Test
    void shouldCreateCoursWithExpectedFields() {
        Cours cours = new Cours("POO", "M. Dupont");

        assertEquals("POO", cours.getNomCours());
        assertEquals("M. Dupont", cours.getProfesseurResponsable());
    }

    @Test
    void shouldRejectBlankCourseName() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Cours(" ", "M. Dupont")
        );

        assertTrue(exception.getMessage().contains("nom du cours"));
    }

    @Test
    void shouldReturnReadableToString() {
        Cours cours = new Cours("Java", "Mme Lopez");

        String result = cours.toString();

        assertTrue(result.contains("Java"));
        assertTrue(result.contains("Mme Lopez"));
    }
}
