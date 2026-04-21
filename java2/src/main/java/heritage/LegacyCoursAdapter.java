package heritage;

public class LegacyCoursAdapter implements CoursAdapter {
    @Override
    public Cours adapter(String legacyData) {
        if (legacyData == null || legacyData.isBlank()) {
            throw new IllegalArgumentException("La donnee legacy est obligatoire");
        }

        String[] elements = legacyData.split("[|;:]");
        if (elements.length != 2) {
            throw new IllegalArgumentException("Format legacy invalide. Attendu: nom|professeur");
        }

        String nomCours = elements[0].trim();
        String professeur = elements[1].trim();
        return new Cours(nomCours, professeur);
    }
}
