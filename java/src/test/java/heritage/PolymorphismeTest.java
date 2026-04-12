package heritage;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PolymorphismeTest {

    @Test
    void shouldUseDynamicDispatchFromPersonneList() {
        Etudiant etudiant = new Etudiant("Alice", 21, "E001", 15.0);
        etudiant.ajouterCours(new Cours("POO", "M. Dupont"));
        Enseignant enseignant = new Enseignant("Dr. Martin", 45, "Informatique", 3200.0);

        List<Personne> personnes = Arrays.asList(etudiant, enseignant);

        String detailEtudiant = personnes.get(0).afficherDetails();
        String detailEnseignant = personnes.get(1).afficherDetails();

        assertTrue(detailEtudiant.contains("Etudiant{"));
        assertTrue(detailEtudiant.contains("E001"));
        assertTrue(detailEnseignant.contains("Enseignant{"));
        assertTrue(detailEnseignant.contains("Informatique"));
    }
}
