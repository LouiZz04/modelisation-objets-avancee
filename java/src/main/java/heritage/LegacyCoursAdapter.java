package heritage;

import java.util.ArrayList;
import java.util.List;

public class LegacyCoursAdapter {
    private final LegacyCoursSource source;

    public LegacyCoursAdapter(LegacyCoursSource source) {
        this.source = source;
    }

    public List<Cours> recupererCours() {
        List<Cours> cours = new ArrayList<>();
        for (String ligne : source.recupererDonnees()) {
            cours.add(convertirChaine(ligne));
        }
        return cours;
    }

    public static Cours convertirChaine(String ligne) {
        String[] morceaux = ligne.split("\\|", 2);
        if (morceaux.length != 2 || morceaux[0].isBlank() || morceaux[1].isBlank()) {
            throw new IllegalArgumentException("Format legacy invalide: " + ligne);
        }
        return new Cours(morceaux[0].trim(), morceaux[1].trim());
    }
}
