package heritage;

public class ConsoleReportGenerator implements IReportGenerator {
    @Override
    public String generate(Etudiant etudiant) {
        if (etudiant == null) {
            throw new IllegalArgumentException("L'etudiant est obligatoire");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== Rapport Etudiant ===\n");
        sb.append("Nom: ").append(etudiant.getNom()).append('\n');
        sb.append("Age: ").append(etudiant.getAge()).append('\n');
        sb.append("Numero: ").append(etudiant.getNumEtudiant()).append('\n');
        sb.append("Moyenne: ").append(etudiant.getMoyenne()).append('\n');
        sb.append("Cours:\n");
        for (Cours cours : etudiant.getCours()) {
            sb.append("- ").append(cours.getNomCours())
                .append(" (").append(cours.getProfesseur()).append(")\n");
        }
        return sb.toString();
    }
}
