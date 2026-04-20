package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnseignantTest {

    @Test
    void shouldCreateTeacher() {
        Enseignant enseignant = new Enseignant("Claire", 40, "Architecture", 3200.0);

        assertEquals("Claire", enseignant.getNom());
        assertEquals(40, enseignant.getAge());
        assertEquals("Architecture", enseignant.getMatiere());
        assertEquals(3200.0, enseignant.getSalaire());
    }

    @Test
    void shouldRejectInvalidTeacherData() {
        assertThrows(IllegalArgumentException.class, () -> new Enseignant("Claire", 40, "", 3200.0));
        assertThrows(IllegalArgumentException.class, () -> new Enseignant("Claire", 40, "Architecture", -10.0));
    }

    @Test
    void shouldFormatTeacherDetails() {
        Enseignant enseignant = new Enseignant("Claire", 40, "Architecture", 3200.0);

        String result = enseignant.afficherDetails();

        assertTrue(result.contains("Claire"));
        assertTrue(result.contains("Architecture"));
        assertTrue(result.contains("3200.0"));
    }
}
