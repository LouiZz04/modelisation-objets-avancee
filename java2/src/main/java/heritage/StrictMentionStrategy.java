package heritage;

public class StrictMentionStrategy implements MentionStrategy {
    @Override
    public String calculerMention(double moyenne) {
        if (moyenne < 10.0) {
            return "Ajourne";
        }
        if (moyenne < 12.0) {
            return "Passable";
        }
        if (moyenne < 14.0) {
            return "Sans mention";
        }
        if (moyenne < 16.0) {
            return "Assez bien";
        }
        if (moyenne < 18.0) {
            return "Bien";
        }
        return "Excellent";
    }
}
