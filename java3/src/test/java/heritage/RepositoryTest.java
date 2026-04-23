package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RepositoryTest {

    @Test
    void shouldSaveAndFindStudentUsingRepositoryAbstraction() {
        EtudiantRepository repository = new InMemoryEtudiantRepository();
        Etudiant etudiant = new Etudiant("Alice", 21, "E01", 14.0);

        repository.save(etudiant);

        assertTrue(repository.findByNumero("E01").isPresent());
        assertEquals(1, repository.findAll().size());
    }
}
