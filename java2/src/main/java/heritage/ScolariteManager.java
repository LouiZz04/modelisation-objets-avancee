package heritage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScolariteManager implements NoteObserver {
    private static final ScolariteManager INSTANCE = new ScolariteManager();

    private final List<Etudiant> etudiants;
    private int totalNotifications;
    private double moyenneGlobale;

    private ScolariteManager() {
        this.etudiants = new ArrayList<>();
    }

    public static ScolariteManager getInstance() {
        return INSTANCE;
    }

    public void inscrireEtudiant(Etudiant etudiant) {
        if (etudiant == null) {
            throw new IllegalArgumentException("L'etudiant ne peut pas etre null");
        }
        boolean existe = etudiants.stream()
            .anyMatch(e -> e.getNumeroEtudiant().equals(etudiant.getNumeroEtudiant()));
        if (!existe) {
            etudiants.add(etudiant);
            etudiant.ajouterObservateur(this);
            recalculerMoyenneGlobale();
        }
    }

    public void desinscrireEtudiant(Etudiant etudiant) {
        if (etudiant == null) {
            return;
        }
        if (etudiants.remove(etudiant)) {
            etudiant.retirerObservateur(this);
            recalculerMoyenneGlobale();
        }
    }

    public List<Etudiant> getEtudiants() {
        return Collections.unmodifiableList(etudiants);
    }

    public int getTotalNotifications() {
        return totalNotifications;
    }

    public double getMoyenneGlobale() {
        return moyenneGlobale;
    }

    public List<Etudiant> trierEtudiants(TriEtudiantStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("La strategy de tri est obligatoire");
        }
        return strategy.trier(etudiants);
    }

    public void reset() {
        for (Etudiant etudiant : etudiants) {
            etudiant.retirerObservateur(this);
        }
        etudiants.clear();
        totalNotifications = 0;
        moyenneGlobale = 0.0;
    }

    @Override
    public void onNoteAjoutee(Etudiant etudiant, double note, double nouvelleMoyenne) {
        totalNotifications++;
        recalculerMoyenneGlobale();
    }

    private void recalculerMoyenneGlobale() {
        moyenneGlobale = etudiants.stream()
            .mapToDouble(Etudiant::getMoyenne)
            .average()
            .orElse(0.0);
    }
}
