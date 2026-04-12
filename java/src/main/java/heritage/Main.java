package heritage;

public class Main {
    public static void main(String[] args) {
        Etudiant etudiant = new Etudiant("Alice", 21, "E2026001", 15.5);
        Cours algo = new Cours("Algorithmique", "Mme Martin");
        Cours poo = new Cours("POO", "M. Dupont");

        etudiant.ajouterCours(algo);
        etudiant.ajouterCours(poo);

        System.out.println("Instance de Personne ? " + (etudiant instanceof Personne));
        System.out.println(etudiant);

        try {
            etudiant.setMoyenne(25.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Modification refusee: " + e.getMessage());
        }
    }
}
