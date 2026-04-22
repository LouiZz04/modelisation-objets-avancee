package heritage;

public class PersonneFactory {

    public Personne createPersonne(PersonneType type, String nom, int age, String reference, double moyenne) {
        if (type == PersonneType.ETUDIANT) {
            return createEtudiant(nom, age, reference, moyenne);
        }
        if (type == PersonneType.PROFESSEUR) {
            return createProfesseur(nom, age, reference);
        }
        throw new IllegalArgumentException("Type de personne non supporte");
    }

    public Etudiant createEtudiant(String nom, int age, String numeroEtudiant, double moyenne) {
        return new Etudiant(nom, age, numeroEtudiant, moyenne);
    }

    public Professeur createProfesseur(String nom, int age, String matiere) {
        return new Professeur(nom, age, matiere);
    }
}
