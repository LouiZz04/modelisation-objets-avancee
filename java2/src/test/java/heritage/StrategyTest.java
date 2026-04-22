package heritage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategyTest {
    private ScolariteManager manager;

    @BeforeEach
    void setUp() {
        manager = ScolariteManager.getInstance();
        manager.reset();
    }

    @Test
    void shouldChangeMentionStrategyDynamically() {
        Etudiant etudiant = new Etudiant("Alice", 20, "E01", 15.0);

        assertEquals("Bien", etudiant.getMention());

        etudiant.setMentionStrategy(new StrictMentionStrategy());

        assertEquals("Assez bien", etudiant.getMention());
    }

    @Test
    void shouldSortStudentsWithDifferentStrategies() {
        Etudiant bob = new Etudiant("Bob", 21, "E02", 12.0);
        Etudiant alice = new Etudiant("Alice", 20, "E01", 15.0);

        manager.inscrireEtudiant(bob);
        manager.inscrireEtudiant(alice);

        List<Etudiant> triNom = manager.trierEtudiants(new TriParNomStrategy());
        List<Etudiant> triMoyenne = manager.trierEtudiants(new TriParMoyenneStrategy());

        assertEquals("Alice", triNom.get(0).getNom());
        assertEquals("Alice", triMoyenne.get(0).getNom());
    }
}
