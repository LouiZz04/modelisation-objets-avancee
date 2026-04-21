package heritage;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        demoTp1();
        demoTp2();
        demoTp3();
        demoPatterns();
    }

    private static void demoTp1() {
        System.out.println("--- TP1: Heritage et composition ---");

        Etudiant etudiant = new Etudiant("Alice", 21, "E2026001", 15.5);
        Cours poo = new Cours("POO", "M. Dupont");
        Cours algo = new Cours("Algorithmique", "Mme Martin");

        etudiant.ajouterCours(poo);
        etudiant.ajouterCours(algo);

        System.out.println(etudiant.afficherDetails());
        System.out.println("Cours suivis:");
        for (Cours cours : etudiant.getListeCours()) {
            System.out.println("- " + cours);
        }
    }

    private static void demoTp2() {
        System.out.println("--- TP2: Encapsulation ---");

        Etudiant etudiant = new Etudiant("Alice", 21, "E2026001", 14.0);
        System.out.println(etudiant.afficherDetails());

        try {
            etudiant.setMoyenne(25.0);
        } catch (IllegalArgumentException error) {
            System.out.println("Modification refusee: " + error.getMessage());
        }
    }

    private static void demoTp3() {
        System.out.println("--- TP3: Polymorphisme ---");

        Etudiant etudiant = new Etudiant("Alice", 21, "E2026001", 16.5);
        etudiant.ajouterCours(new Cours("Python", "Mme Martin"));
        Enseignant enseignant = new Enseignant("Claire", 40, "Mathematiques", 3200.0);

        List<Personne> personnes = List.of(etudiant, enseignant);
        for (Personne personne : personnes) {
            System.out.println(personne.afficherDetails());
        }
    }

    private static void demoPatterns() {
        System.out.println("--- TP Patterns: demo complete ---");

        ScolariteManager manager = ScolariteManager.getInstance();

        Etudiant alice = PersonneFactory.creerEtudiant("Alice", 21, "E2026001", 0.0);
        Etudiant bob = PersonneFactory.creerEtudiant("Bob", 22, "E2026002", 0.0);
        Enseignant teacher = PersonneFactory.creerEnseignant("Claire", 40, "Architecture", 3200.0);

        manager.ajouterEtudiant(alice);
        manager.ajouterEtudiant(bob);

        LegacyCoursSource source = new LegacyCoursSource(List.of(
            "Design Patterns | Mme Martin",
            "Software Architecture | M. Dupont"
        ));
        LegacyCoursAdapter adapter = new LegacyCoursAdapter(source);
        for (Cours cours : adapter.recupererCours()) {
            alice.ajouterCours(cours);
            bob.ajouterCours(cours);
        }

        alice.ajouterNote(12.0);
        alice.ajouterNote(14.0);
        bob.ajouterNote(17.0);
        bob.setMentionStrategy(new MentionBienveillanteStrategy());

        manager.setTriStrategy(new TriParMoyenneStrategy());

        EtudiantView decorated = new EtudiantDelegueDecorator(
            new EtudiantBoursierDecorator(bob, 250.0),
            "ING2"
        );

        System.out.println(teacher.afficherDetails());
        System.out.println(alice.afficherDetails());
        System.out.println(decorated.afficherDetails());
        System.out.println("Moyenne globale: " + manager.getMoyenneGenerale());
        System.out.println("Ordre des etudiants: " + manager.getEtudiantsTries().stream().map(Etudiant::getNom).toList());
        System.out.println("Notifications: " + manager.getNotifications());
    }
}
