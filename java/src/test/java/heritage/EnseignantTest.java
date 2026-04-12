package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnseignantTest {

    @Test
    void shouldBeAPersonne() {
        Enseignant enseignant = new Enseignant("Dr. Diallo", 40, "Maths", 3000.0);

        assertInstanceOf(Personne.class, enseignant);
        assertEquals("Dr. Diallo", enseignant.getNom());
        assertEquals(40, enseignant.getAge());
    }

    @Test
    void shouldStoreMatiereAndSalaire() {
        Enseignant enseignant = new Enseignant("Dr. Diallo", 40, "Maths", 3000.0);

        assertEquals("Maths", enseignant.getMatiere());
        assertEquals(3000.0, enseignant.getSalaire());
    }

    @Test
    void shouldRejectInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> new Enseignant("Dr. Diallo", 40, "", 3000.0));
        assertThrows(IllegalArgumentException.class, () -> new Enseignant("Dr. Diallo", 40, "Maths", -100.0));
    }

    @Test
    void shouldOverrideAfficherDetails() {
        Enseignant enseignant = new Enseignant("Dr. Diallo", 40, "Maths", 3000.0);

        String details = enseignant.afficherDetails();

        assertTrue(details.contains("Dr. Diallo"));
        assertTrue(details.contains("Maths"));
        assertTrue(details.contains("3000.0"));
    }
}
