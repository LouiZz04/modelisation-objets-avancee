package heritage;

public class Personne {
    protected String nom;
    protected int age;

    public Personne(String nom, int age) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (age < 0) {
            throw new IllegalArgumentException("L'age ne peut pas etre negatif");
        }
        this.nom = nom;
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Personne{" +
            "nom='" + nom + '\'' +
            ", age=" + age +
            '}';
    }
}
