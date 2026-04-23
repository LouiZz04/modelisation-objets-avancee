package heritage;

public class MentionBienveillanteStrategy implements MentionStrategy {
    @Override
    public String calculerMention(double moyenne) {
        double moyenneMajoree = Math.min(20.0, moyenne + 1.0);
        return new MentionStandardStrategy().calculerMention(moyenneMajoree);
    }
}
