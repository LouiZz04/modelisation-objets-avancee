package heritage;

public class Enseignant extends Personne {
    private String matiere;
    private double salaire;

    public Enseignant(String nom, int age, String matiere, double salaire) {
        super(nom, age);
        setMatiere(matiere);
        setSalaire(salaire);
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

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        if (salaire < 0.0) {
            throw new IllegalArgumentException("Le salaire doit etre positif");
        }
        this.salaire = salaire;
    }

    @Override
    public String afficherDetails() {
        return "Enseignant{" +
            "nom='" + getNom() + '\'' +
            ", age=" + getAge() +
            ", matiere='" + matiere + '\'' +
            ", salaire=" + salaire +
            '}';
    }

    @Override
    public String toString() {
        return afficherDetails();
    }
}
