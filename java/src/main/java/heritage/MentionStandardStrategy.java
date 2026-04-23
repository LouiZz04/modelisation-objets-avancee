package heritage;

public class MentionStandardStrategy implements MentionStrategy {
    @Override
    public String calculerMention(double moyenne) {
        if (moyenne >= 16.0) {
            return "Tres bien";
        }
        if (moyenne >= 14.0) {
            return "Bien";
        }
        if (moyenne >= 12.0) {
            return "Assez bien";
        }
        if (moyenne >= 10.0) {
            return "Passable";
        }
        return "Ajourne";
    }
}
