package heritage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObserverTest {
    private ScolariteManager manager;

    @BeforeEach
    void setUp() {
        manager = ScolariteManager.getInstance();
        manager.reset();
    }

    @Test
    void shouldNotifyManagerWhenStudentAddsNote() {
        Etudiant etudiant = new Etudiant("Alice", 20, "E01", 14.0);
        manager.inscrireEtudiant(etudiant);

        assertEquals(0, manager.getTotalNotifications());
        assertEquals(14.0, manager.getMoyenneGlobale());

        etudiant.ajouterNote(16.0);

        assertEquals(1, manager.getTotalNotifications());
        assertEquals(15.0, manager.getMoyenneGlobale());
    }
}
