package heritage;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Etudiant etudiant = new Etudiant("Alice", 21, "E2026001", 15.5);
        Enseignant enseignant = new Enseignant("Dr. Martin", 45, "POO", 3200.0);
        Cours algo = new Cours("Algorithmique", "Mme Martin");
        Cours poo = new Cours("POO", "M. Dupont");

        etudiant.ajouterCours(algo);
        etudiant.ajouterCours(poo);

        List<Personne> personnes = Arrays.asList(etudiant, enseignant);
        for (Personne personne : personnes) {
            System.out.println(personne.afficherDetails());
        }

        try {
            etudiant.setMoyenne(25.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Modification refusee: " + e.getMessage());
        }
    }
}
