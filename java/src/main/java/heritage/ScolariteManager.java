package heritage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScolariteManager implements EtudiantObserver {
    private static ScolariteManager instance;

    private final List<Etudiant> etudiants;
    private final List<String> notifications;
    private int nombreNotes;
    private double moyenneGenerale;
    private TriEtudiantStrategy triStrategy;

    private ScolariteManager() {
        this.etudiants = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.triStrategy = new TriParNomStrategy();
    }

    public static synchronized ScolariteManager getInstance() {
        if (instance == null) {
            instance = new ScolariteManager();
        }
        return instance;
    }

    public static synchronized void resetInstance() {
        instance = null;
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        if (etudiant == null) {
            throw new IllegalArgumentException("Etudiant obligatoire");
        }
        if (!etudiants.contains(etudiant)) {
            etudiants.add(etudiant);
            etudiant.ajouterObserver(this);
            mettreAJourStatistiques();
        }
    }

    public List<Etudiant> getEtudiants() {
        return Collections.unmodifiableList(etudiants);
    }

    public List<String> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    public int getNombreNotes() {
        return nombreNotes;
    }

    public double getMoyenneGenerale() {
        return moyenneGenerale;
    }

    public void setTriStrategy(TriEtudiantStrategy triStrategy) {
        if (triStrategy == null) {
            throw new IllegalArgumentException("Strategy obligatoire");
        }
        this.triStrategy = triStrategy;
    }

    public List<Etudiant> getEtudiantsTries() {
        return Collections.unmodifiableList(triStrategy.trier(etudiants));
    }

    @Override
    public void noteAjoutee(Etudiant etudiant, double note) {
        notifications.add(etudiant.getNumeroEtudiant() + ":" + note);
        mettreAJourStatistiques();
    }

    private void mettreAJourStatistiques() {
        List<Double> toutesLesNotes = new ArrayList<>();
        for (Etudiant etudiant : etudiants) {
            toutesLesNotes.addAll(etudiant.getNotes());
        }

        nombreNotes = toutesLesNotes.size();
        moyenneGenerale = toutesLesNotes.stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0);
    }
}
