package heritage;

public class PersonneFactory {
    public static Etudiant creerEtudiant(String nom, int age, String numeroEtudiant, double moyenne) {
        return new Etudiant(nom, age, numeroEtudiant, moyenne);
    }

    public static Enseignant creerEnseignant(String nom, int age, String matiere, double salaire) {
        return new Enseignant(nom, age, matiere, salaire);
    }

    public static Personne creerPersonne(String type, String nom, int age, String reference, double valeur) {
        String typeNormalise = type.toLowerCase();
        if ("etudiant".equals(typeNormalise)) {
            return creerEtudiant(nom, age, reference, valeur);
        }
        if ("enseignant".equals(typeNormalise)) {
            return creerEnseignant(nom, age, reference, valeur);
        }
        throw new IllegalArgumentException("Type de personne inconnu: " + type);
    }
}
