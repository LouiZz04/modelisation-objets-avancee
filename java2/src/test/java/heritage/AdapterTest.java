package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AdapterTest {

    @Test
    void shouldAdaptLegacyStringToCoursObject() {
        CoursAdapter adapter = new LegacyCoursAdapter();

        Cours cours = adapter.adapter("Algorithmique|Mme Martin");

        assertEquals("Algorithmique", cours.getNomCours());
        assertEquals("Mme Martin", cours.getProfesseur());
    }

    @Test
    void shouldRejectInvalidLegacyFormat() {
        CoursAdapter adapter = new LegacyCoursAdapter();

        assertThrows(IllegalArgumentException.class, () -> adapter.adapter("format-invalide"));
    }
}
