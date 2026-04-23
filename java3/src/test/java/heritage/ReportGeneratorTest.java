package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportGeneratorTest {

    @Test
    void shouldGenerateConsoleFormat() {
        Etudiant etudiant = new Etudiant("Alice", 21, "E01", 14.0);
        etudiant.ajouterCours(new Cours("POO", "M. Dupont"));
        IReportGenerator generator = new ConsoleReportGenerator();

        String report = generator.generate(etudiant);

        assertTrue(report.contains("Rapport Etudiant"));
        assertTrue(report.contains("Alice"));
        assertTrue(report.contains("POO"));
    }

    @Test
    void shouldGenerateJsonFormat() {
        Etudiant etudiant = new Etudiant("Alice", 21, "E01", 14.0);
        etudiant.ajouterCours(new Cours("POO", "M. Dupont"));
        IReportGenerator generator = new JsonReportGenerator();

        String report = generator.generate(etudiant);

        assertTrue(report.contains("\"nom\":\"Alice\""));
        assertTrue(report.contains("\"numEtudiant\":\"E01\""));
        assertTrue(report.contains("\"nomCours\":\"POO\""));
    }
}
