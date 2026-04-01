package heritage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Etudiant extends Personne {
    private final String numeroEtudiant;
    private double moyenne;
    private final List<Cours> listeCours;

    public Etudiant(String nom, int age, String numeroEtudiant, double moyenne) {
        super(nom, age);
        if (numeroEtudiant == null || numeroEtudiant.isBlank()) {
            throw new IllegalArgumentException("Le numero etudiant est obligatoire");
        }
        this.numeroEtudiant = numeroEtudiant;
        setMoyenne(moyenne);
        this.listeCours = new ArrayList<>();
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        if (moyenne < 0.0 || moyenne > 20.0) {
            throw new IllegalArgumentException("La moyenne doit etre comprise entre 0 et 20");
        }
        this.moyenne = moyenne;
    }

    public void ajouterCours(Cours cours) {
        if (cours == null) {
            throw new IllegalArgumentException("Le cours ne peut pas etre null");
        }
        listeCours.add(cours);
    }

    public List<Cours> getListeCours() {
        return Collections.unmodifiableList(listeCours);
    }

    @Override
    public String toString() {
        return "Etudiant{" +
            "numeroEtudiant='" + numeroEtudiant + '\'' +
            ", moyenne=" + moyenne +
            ", nom='" + nom + '\'' +
            ", age=" + age +
            ", listeCours=" + listeCours +
            '}';
    }
}
