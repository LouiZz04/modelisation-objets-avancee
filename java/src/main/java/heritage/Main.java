package heritage;

import java.util.List;

public class Main {
    public static void main(String[] args) {
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
