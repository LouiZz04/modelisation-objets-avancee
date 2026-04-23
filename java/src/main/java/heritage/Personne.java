package heritage;

public abstract class Personne {
    private String nom;
    private int age;

    public Personne(String nom, int age) {
        setNom(nom);
        setAge(age);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0 || age > 100) {
            throw new IllegalArgumentException("L'age doit etre compris entre 1 et 100");
        }
        this.age = age;
    }

    public abstract String afficherDetails();

    @Override
    public String toString() {
        return afficherDetails();
    }

    @Override
    public String toString() {
        return afficherDetails();
    }
}
