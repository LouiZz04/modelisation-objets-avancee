package heritage;

public class Main {
    public static void main(String[] args) {
        EtudiantRepository repository = new InMemoryEtudiantRepository();
        RapportEtudiantService service = new RapportEtudiantService(new ConsoleReportGenerator());

        Etudiant etudiant = new Etudiant("Alice", 21, "E2026-001", 14.5);
        etudiant.ajouterCours(new Cours("Algorithmique", "Mme Martin"));
        etudiant.ajouterCours(new Cours("POO", "M. Dupont"));
        repository.save(etudiant);

        String consoleReport = service.genererRapport(etudiant);
        System.out.println(consoleReport);

        service.setReportGenerator(new JsonReportGenerator());
        String jsonReport = service.genererRapport(etudiant);
        System.out.println(jsonReport);
    }
}
