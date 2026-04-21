package heritage;

public class EtudiantDelegueDecorator extends EtudiantDecorator {
    private final String promotion;

    public EtudiantDelegueDecorator(EtudiantComponent composant, String promotion) {
        super(composant);
        if (promotion == null || promotion.isBlank()) {
            throw new IllegalArgumentException("La promotion est obligatoire");
        }
        this.promotion = promotion;
    }

    @Override
    public String afficherDetails() {
        return composant.afficherDetails() + " | Role: Delegue(" + promotion + ")";
    }
}
