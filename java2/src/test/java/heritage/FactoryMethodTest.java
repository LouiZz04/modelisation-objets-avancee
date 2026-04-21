package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class FactoryMethodTest {

    @Test
    void shouldCreateEtudiantWithoutDirectInstantiationInClient() {
        PersonneFactory factory = new PersonneFactory();

        Personne personne = factory.createPersonne(PersonneType.ETUDIANT, "Alice", 20, "E01", 14.0);

        assertInstanceOf(Etudiant.class, personne);
        assertEquals("Alice", personne.getNom());
    }

    @Test
    void shouldCreateProfesseurWithoutDirectInstantiationInClient() {
        PersonneFactory factory = new PersonneFactory();

        Personne personne = factory.createPersonne(PersonneType.PROFESSEUR, "Dr. Martin", 42, "Maths", 0.0);

        assertInstanceOf(Professeur.class, personne);
        assertEquals("Dr. Martin", personne.getNom());
    }
}
