package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EtudiantTest {

    @Test
    void shouldAddCoursesToStudent() {
        Etudiant etudiant = new Etudiant("Alice", 21, "E01", 14.0);
        Cours cours1 = new Cours("Algorithmique", "Mme Martin");
        Cours cours2 = new Cours("POO", "M. Dupont");

        etudiant.ajouterCours(cours1);
        etudiant.ajouterCours(cours2);

        assertEquals(2, etudiant.getCours().size());
        assertEquals("Algorithmique", etudiant.getCours().get(0).getNomCours());
    }

    @Test
    void shouldRejectInvalidMoyenne() {
        assertThrows(IllegalArgumentException.class, () -> new Etudiant("Alice", 21, "E01", 25.0));
    }
}
