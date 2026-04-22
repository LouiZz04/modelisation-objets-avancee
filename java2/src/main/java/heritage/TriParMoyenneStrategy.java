package heritage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TriParMoyenneStrategy implements TriEtudiantStrategy {
    @Override
    public List<Etudiant> trier(List<Etudiant> etudiants) {
        List<Etudiant> copie = new ArrayList<>(etudiants);
        copie.sort(Comparator.comparingDouble(Etudiant::getMoyenne).reversed());
        return copie;
    }
}
