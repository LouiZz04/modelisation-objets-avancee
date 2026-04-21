package heritage;

public class EtudiantBoursierDecorator extends EtudiantDecorator {
    private final double montantBourse;

    public EtudiantBoursierDecorator(EtudiantComponent composant, double montantBourse) {
        super(composant);
        if (montantBourse < 0.0) {
            throw new IllegalArgumentException("Le montant de bourse doit etre positif");
        }
        this.montantBourse = montantBourse;
    }

    @Override
    public String afficherDetails() {
        return composant.afficherDetails() + " | Statut: Boursier(" + montantBourse + " EUR)";
    }
}
