package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EtudiantTest {

    @Test
    void shouldBeAPersonne() {
        Etudiant etudiant = new Etudiant("Alice", 21, "E001", 14.0);

        assertInstanceOf(Personne.class, etudiant);
        assertEquals("Alice", etudiant.getNom());
        assertEquals(21, etudiant.getAge());
    }

    @Test
    void shouldStoreAcademicFields() {
        Etudiant etudiant = new Etudiant("Alice", 21, "E001", 14.0);

        assertEquals("E001", etudiant.getNumeroEtudiant());
        assertEquals(14.0, etudiant.getMoyenne());
    }

    @Test
    void shouldRejectInvalidMoyenne() {
        assertThrows(IllegalArgumentException.class, () -> new Etudiant("Alice", 21, "E001", 25.0));
    }

    @Test
    void shouldAddCours() {
        Etudiant etudiant = new Etudiant("Alice", 21, "E001", 14.0);
        Cours cours1 = new Cours("POO", "M. Dupont");
        Cours cours2 = new Cours("Algo", "Mme Martin");

        etudiant.ajouterCours(cours1);
        etudiant.ajouterCours(cours2);

        assertEquals(2, etudiant.getListeCours().size());
        assertEquals(cours1, etudiant.getListeCours().get(0));
        assertEquals(cours2, etudiant.getListeCours().get(1));
    }

    @Test
    void shouldFormatToStringWithInheritedData() {
        Etudiant etudiant = new Etudiant("Alice", 21, "E001", 14.0);

        String result = etudiant.toString();

        assertTrue(result.contains("Alice"));
        assertTrue(result.contains("E001"));
        assertTrue(result.contains("14.0"));
    }
}
