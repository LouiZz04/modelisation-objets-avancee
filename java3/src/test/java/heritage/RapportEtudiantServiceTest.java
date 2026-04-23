package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RapportEtudiantServiceTest {

    @Test
    void shouldSwitchReportStrategyWithoutChangingEtudiantClass() {
        Etudiant etudiant = new Etudiant("Alice", 21, "E01", 14.0);
        etudiant.ajouterCours(new Cours("POO", "M. Dupont"));
        RapportEtudiantService service = new RapportEtudiantService(new ConsoleReportGenerator());

        String consoleReport = service.genererRapport(etudiant);

        service.setReportGenerator(new JsonReportGenerator());
        String jsonReport = service.genererRapport(etudiant);

        assertTrue(consoleReport.contains("Rapport Etudiant"));
        assertTrue(jsonReport.startsWith("{"));
        assertTrue(jsonReport.contains("\"cours\""));
    }
}
