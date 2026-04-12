package heritage;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
    void shouldRejectAgeAbove100() {
        assertThrows(IllegalArgumentException.class, () -> new Personne("Alice", 101));
    }

    @Test
    void shouldValidateSetters() {
        Personne personne = new Personne("Alice", 20);

        personne.setNom("Marie");
        personne.setAge(35);

        assertEquals("Marie", personne.getNom());
        assertEquals(35, personne.getAge());
        assertThrows(IllegalArgumentException.class, () -> personne.setNom(""));
        assertThrows(IllegalArgumentException.class, () -> personne.setAge(120));
    }

    @Test
    void shouldKeepNomAndAgePrivate() throws NoSuchFieldException {
        Field nomField = Personne.class.getDeclaredField("nom");
        Field ageField = Personne.class.getDeclaredField("age");

        assertTrue(Modifier.isPrivate(nomField.getModifiers()));
        assertTrue(Modifier.isPrivate(ageField.getModifiers()));
    }

    @Test
    void shouldFormatToString() {
        Personne personne = new Personne("Bob", 22);

        String result = personne.toString();

        assertTrue(result.contains("Bob"));
        assertTrue(result.contains("22"));
    }

    @Test
    void shouldExposeAfficherDetails() {
        Personne personne = new Personne("Khadija", 30);

        String details = personne.afficherDetails();

        assertTrue(details.contains("Khadija"));
        assertTrue(details.contains("30"));
    }
}
