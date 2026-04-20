package heritage;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
        assertTrue(etudiant.getNotes().isEmpty());
    }

    @Test
    void shouldRejectInvalidMoyenne() {
        assertThrows(IllegalArgumentException.class, () -> new Etudiant("Alice", 21, "E001", 25.0));
    }

    @Test
    void shouldUpdateMoyenneWithSetter() {
        Etudiant etudiant = new Etudiant("Alice", 21, "E001", 14.0);

        etudiant.setMoyenne(18.5);

        assertEquals(18.5, etudiant.getMoyenne());
        assertThrows(IllegalArgumentException.class, () -> etudiant.setMoyenne(-1.0));
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
        assertThrows(UnsupportedOperationException.class, () -> etudiant.getListeCours().add(cours1));
    }

    @Test
    void shouldUpdateAverageWhenAddingNotes() {
        Etudiant etudiant = new Etudiant("Alice", 21, "E001", 0.0);

        etudiant.ajouterNote(10.0);
        etudiant.ajouterNote(14.0);

        assertEquals(2, etudiant.getNotes().size());
        assertEquals(12.0, etudiant.getMoyenne());
    }

    @Test
    void shouldKeepMoyennePrivate() throws NoSuchFieldException {
        Field moyenneField = Etudiant.class.getDeclaredField("moyenne");
        assertTrue(Modifier.isPrivate(moyenneField.getModifiers()));
    }

    @Test
    void shouldExposeNoSetterForNumeroEtudiant() {
        Method[] methods = Etudiant.class.getDeclaredMethods();

        for (Method method : methods) {
            assertTrue(!method.getName().equals("setNumeroEtudiant"));
        }
    }

    @Test
    void shouldFormatToStringWithInheritedData() {
        Etudiant etudiant = new Etudiant("Alice", 21, "E001", 14.0);

        String result = etudiant.toString();

        assertTrue(result.contains("Alice"));
        assertTrue(result.contains("E001"));
        assertTrue(result.contains("14.0"));
        assertTrue(result.contains("mention"));
    }
}
