package heritage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Etudiant extends Personne {
    private final String numEtudiant;
    private double moyenne;
    private final List<Cours> cours;

    public Etudiant(String nom, int age, String numEtudiant, double moyenne) {
        super(nom, age);
        if (numEtudiant == null || numEtudiant.isBlank()) {
            throw new IllegalArgumentException("Le numero etudiant est obligatoire");
        }
        this.numEtudiant = numEtudiant;
        this.cours = new ArrayList<>();
        setMoyenne(moyenne);
    }

    public String getNumEtudiant() {
        return numEtudiant;
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
        this.cours.add(cours);
    }

    public List<Cours> getCours() {
        return Collections.unmodifiableList(cours);
    }
}
