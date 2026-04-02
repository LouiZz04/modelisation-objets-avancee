package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonneTest {

    @Test
    void shouldCreatePersonneWithNomAndAge() {
        Personne personne = new Personne("Alice", 20);

        assertEquals("Alice", personne.getNom());
        assertEquals(20, personne.getAge());
    }

    @Test
    void shouldRejectBlankNom() {
        assertThrows(IllegalArgumentException.class, () -> new Personne(" ", 20));
    }

    @Test
    void shouldRejectNegativeAge() {
        assertThrows(IllegalArgumentException.class, () -> new Personne("Alice", -1));
    }

    @Test
    void shouldFormatToString() {
        Personne personne = new Personne("Bob", 22);

        String result = personne.toString();

        assertTrue(result.contains("Bob"));
        assertTrue(result.contains("22"));
    }
}
