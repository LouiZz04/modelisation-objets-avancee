package heritage;

public class RapportEtudiantService {
    private IReportGenerator reportGenerator;

    public RapportEtudiantService(IReportGenerator reportGenerator) {
        setReportGenerator(reportGenerator);
    }

    public void setReportGenerator(IReportGenerator reportGenerator) {
        if (reportGenerator == null) {
            throw new IllegalArgumentException("Le report generator est obligatoire");
        }
        this.reportGenerator = reportGenerator;
    }

    public String genererRapport(Etudiant etudiant) {
        return reportGenerator.generate(etudiant);
    }
}
