package heritage;

public class Professeur extends Personne {
    private String matiere;

    public Professeur(String nom, int age, String matiere) {
        super(nom, age);
        setMatiere(matiere);
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        if (matiere == null || matiere.isBlank()) {
            throw new IllegalArgumentException("La matiere est obligatoire");
        }
        this.matiere = matiere;
    }

    @Override
    public String afficherDetails() {
        return "Professeur{" +
            "nom='" + getNom() + '\'' +
            ", age=" + getAge() +
            ", matiere='" + matiere + '\'' +
            '}';
    }
}
