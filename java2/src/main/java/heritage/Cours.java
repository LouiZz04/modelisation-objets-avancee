package heritage;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Cours{" +
            "nomCours='" + nomCours + '\'' +
            ", professeur='" + professeur + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cours cours)) {
            return false;
        }
        return Objects.equals(nomCours, cours.nomCours)
            && Objects.equals(professeur, cours.professeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomCours, professeur);
    }
}
