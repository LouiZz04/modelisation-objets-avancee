package heritage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LegacyCoursSource {
    private final List<String> donnees;

    public LegacyCoursSource(List<String> donnees) {
        this.donnees = new ArrayList<>(donnees);
    }

    public List<String> recupererDonnees() {
        return Collections.unmodifiableList(donnees);
    }
}
