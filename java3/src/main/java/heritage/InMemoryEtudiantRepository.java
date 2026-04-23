package heritage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InMemoryEtudiantRepository implements EtudiantRepository {
    private final List<Etudiant> etudiants = new ArrayList<>();

    @Override
    public void save(Etudiant etudiant) {
        if (etudiant == null) {
            throw new IllegalArgumentException("L'etudiant ne peut pas etre null");
        }
        etudiants.removeIf(e -> e.getNumEtudiant().equals(etudiant.getNumEtudiant()));
        etudiants.add(etudiant);
    }

    @Override
    public Optional<Etudiant> findByNumero(String numero) {
        return etudiants.stream().filter(e -> e.getNumEtudiant().equals(numero)).findFirst();
    }

    @Override
    public List<Etudiant> findAll() {
        return Collections.unmodifiableList(etudiants);
    }
}
