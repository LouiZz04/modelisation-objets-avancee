package heritage;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonneTest {

    private static class PersonneTestDouble extends Personne {
        public PersonneTestDouble(String nom, int age) {
            super(nom, age);
        }

        @Override
        public String afficherDetails() {
            return getNom() + ":" + getAge();
        }
    }

    @Test
    void shouldCreatePersonneThroughSubclass() {
        Personne personne = new PersonneTestDouble("Alice", 20);

        assertEquals("Alice", personne.getNom());
        assertEquals(20, personne.getAge());
    }

    @Test
    void shouldRejectBlankNom() {
        assertThrows(IllegalArgumentException.class, () -> new PersonneTestDouble(" ", 20));
    }

    @Test
    void shouldRejectNegativeAge() {
        assertThrows(IllegalArgumentException.class, () -> new PersonneTestDouble("Alice", -1));
    }

    @Test
    void shouldRejectAgeAbove100() {
        assertThrows(IllegalArgumentException.class, () -> new PersonneTestDouble("Alice", 101));
    }

    @Test
    void shouldValidateSetters() {
        Personne personne = new PersonneTestDouble("Alice", 20);

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
    void shouldBeAbstract() {
        assertTrue(Modifier.isAbstract(Personne.class.getModifiers()));
    }
}
