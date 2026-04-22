package heritage;

public class StandardMentionStrategy implements MentionStrategy {
    @Override
    public String calculerMention(double moyenne) {
        if (moyenne < 10.0) {
            return "Ajourne";
        }
        if (moyenne < 12.0) {
            return "Passable";
        }
        if (moyenne < 14.0) {
            return "Assez bien";
        }
        if (moyenne < 16.0) {
            return "Bien";
        }
        return "Tres bien";
    }
}
