package heritage;

import java.util.stream.Collectors;

public class JsonReportGenerator implements IReportGenerator {
    @Override
    public String generate(Etudiant etudiant) {
        if (etudiant == null) {
            throw new IllegalArgumentException("L'etudiant est obligatoire");
        }

        String coursJson = etudiant.getCours().stream()
            .map(c -> "{\"nomCours\":\"" + escape(c.getNomCours()) + "\",\"professeur\":\"" + escape(c.getProfesseur()) + "\"}")
            .collect(Collectors.joining(","));

        return "{"
            + "\"nom\":\"" + escape(etudiant.getNom()) + "\","
            + "\"age\":" + etudiant.getAge() + ","
            + "\"numEtudiant\":\"" + escape(etudiant.getNumEtudiant()) + "\","
            + "\"moyenne\":" + etudiant.getMoyenne() + ","
            + "\"cours\":[" + coursJson + "]"
            + "}";
    }

    private String escape(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
