package heritage;

public abstract class EtudiantDecorator implements EtudiantComponent {
    protected final EtudiantComponent composant;

    protected EtudiantDecorator(EtudiantComponent composant) {
        if (composant == null) {
            throw new IllegalArgumentException("Le composant est obligatoire");
        }
        this.composant = composant;
    }

    @Override
    public Etudiant getEtudiant() {
        return composant.getEtudiant();
    }

    @Override
    public String afficherDetails() {
        return composant.afficherDetails();
    }
}
