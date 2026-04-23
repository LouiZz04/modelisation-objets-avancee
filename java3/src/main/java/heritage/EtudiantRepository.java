package heritage;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepository {
    void save(Etudiant etudiant);
    Optional<Etudiant> findByNumero(String numero);
    List<Etudiant> findAll();
}
