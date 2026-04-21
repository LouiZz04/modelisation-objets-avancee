package heritage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TriParNomStrategy implements TriEtudiantStrategy {
    @Override
    public List<Etudiant> trier(List<Etudiant> etudiants) {
        List<Etudiant> copie = new ArrayList<>(etudiants);
        copie.sort(Comparator.comparing(Etudiant::getNom));
        return copie;
    }
}
