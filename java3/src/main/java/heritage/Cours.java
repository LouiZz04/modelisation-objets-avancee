package heritage;

public class Cours {
    private final String nomCours;
    private final String professeur;

    public Cours(String nomCours, String professeur) {
        if (nomCours == null || nomCours.isBlank()) {
            throw new IllegalArgumentException("Le nom du cours est obligatoire");
        }
        if (professeur == null || professeur.isBlank()) {
            throw new IllegalArgumentException("Le professeur est obligatoire");
        }
        this.nomCours = nomCours;
        this.professeur = professeur;
    }

    public String getNomCours() {
        return nomCours;
    }

    public String getProfesseur() {
        return professeur;
    }
}
