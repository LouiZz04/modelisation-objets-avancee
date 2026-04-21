package heritage;

public class EtudiantSimpleComponent implements EtudiantComponent {
    private final Etudiant etudiant;

    public EtudiantSimpleComponent(Etudiant etudiant) {
        if (etudiant == null) {
            throw new IllegalArgumentException("L'etudiant est obligatoire");
        }
        this.etudiant = etudiant;
    }

    @Override
    public Etudiant getEtudiant() {
        return etudiant;
    }

    @Override
    public String afficherDetails() {
        return etudiant.afficherDetails();
    }
}
