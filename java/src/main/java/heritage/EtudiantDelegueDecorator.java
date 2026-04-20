package heritage;

public class EtudiantDelegueDecorator extends EtudiantDecorator {
    private final String promotion;

    public EtudiantDelegueDecorator(EtudiantView composant, String promotion) {
        super(composant);
        if (promotion == null || promotion.isBlank()) {
            throw new IllegalArgumentException("La promotion est obligatoire");
        }
        this.promotion = promotion;
    }

    @Override
    public String afficherDetails() {
        return super.afficherDetails() + ", delegate=" + promotion;
    }
}
