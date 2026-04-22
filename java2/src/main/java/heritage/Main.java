package heritage;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ScolariteManager manager = ScolariteManager.getInstance();
        manager.reset();

        PersonneFactory factory = new PersonneFactory();
        Etudiant etudiant = factory.createEtudiant("Alice", 21, "E100", 14.0);
        Professeur professeur = factory.createProfesseur("M. Dupont", 45, "POO");

        CoursAdapter adapter = new LegacyCoursAdapter();
        Cours cours = adapter.adapter("Design Patterns|M. Dupont");
        etudiant.ajouterCours(cours);

        manager.inscrireEtudiant(etudiant);

        EtudiantComponent profil = new EtudiantDelegueDecorator(
            new EtudiantBoursierDecorator(new EtudiantSimpleComponent(etudiant), 500.0),
            "L3-INFO"
        );

        etudiant.setMentionStrategy(new StrictMentionStrategy());
        etudiant.ajouterNote(16.0);

        List<Etudiant> tries = manager.trierEtudiants(new TriParMoyenneStrategy());

        System.out.println(profil.afficherDetails());
        System.out.println(professeur.afficherDetails());
        System.out.println("Total notifications manager: " + manager.getTotalNotifications());
        System.out.println("Nombre d'etudiants tries: " + tries.size());
    }
}
