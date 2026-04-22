package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class SingletonTest {

    @Test
    void shouldReturnSameManagerInstance() {
        ScolariteManager manager1 = ScolariteManager.getInstance();
        ScolariteManager manager2 = ScolariteManager.getInstance();

        assertSame(manager1, manager2);
    }

    @Test
    void shouldCentralizeGlobalStudentList() {
        ScolariteManager manager = ScolariteManager.getInstance();
        manager.reset();

        manager.inscrireEtudiant(new Etudiant("Alice", 20, "E01", 13.0));
        manager.inscrireEtudiant(new Etudiant("Bob", 21, "E02", 15.0));

        assertEquals(2, manager.getEtudiants().size());
    }
}
