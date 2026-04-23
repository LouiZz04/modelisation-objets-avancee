package heritage;

public abstract class EtudiantDecorator implements EtudiantView {
    private final EtudiantView composant;

    public EtudiantDecorator(EtudiantView composant) {
        this.composant = composant;
    }

    protected EtudiantView getComposant() {
        return composant;
    }

    @Override
    public String afficherDetails() {
        return composant.afficherDetails();
    }

    @Override
    public Etudiant getEtudiant() {
        return composant.getEtudiant();
    }

    @Override
    public String toString() {
        return afficherDetails();
    }
}
