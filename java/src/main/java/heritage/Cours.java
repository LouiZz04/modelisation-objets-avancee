package heritage;

import java.util.Objects;

public class Cours {
    private final String nomCours;
    private final String professeurResponsable;

    public Cours(String nomCours, String professeurResponsable) {
        if (nomCours == null || nomCours.isBlank()) {
            throw new IllegalArgumentException("Le nom du cours est obligatoire");
        }
        if (professeurResponsable == null || professeurResponsable.isBlank()) {
            throw new IllegalArgumentException("Le nom du professeur est obligatoire");
        }
        this.nomCours = nomCours;
        this.professeurResponsable = professeurResponsable;
    }

    public String getNomCours() {
        return nomCours;
    }

    public String getProfesseurResponsable() {
        return professeurResponsable;
    }

    @Override
    public String toString() {
        return "Cours{" +
            "nomCours='" + nomCours + '\'' +
            ", professeurResponsable='" + professeurResponsable + '\'' +
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
            && Objects.equals(professeurResponsable, cours.professeurResponsable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomCours, professeurResponsable);
    }
}
