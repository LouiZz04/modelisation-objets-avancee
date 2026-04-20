package heritage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Etudiant extends Personne implements EtudiantView {
    private final String numeroEtudiant;
    private double moyenne;
    private final List<Cours> listeCours;
    private final List<Double> notes;
    private final List<EtudiantObserver> observers;
    private MentionStrategy mentionStrategy;

    public Etudiant(String nom, int age, String numeroEtudiant, double moyenne) {
        super(nom, age);
        if (numeroEtudiant == null || numeroEtudiant.isBlank()) {
            throw new IllegalArgumentException("Le numero etudiant est obligatoire");
        }
        this.numeroEtudiant = numeroEtudiant;
        this.listeCours = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.mentionStrategy = new MentionStandardStrategy();
        setMoyenne(moyenne);
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        if (moyenne < 0.0 || moyenne > 20.0) {
            throw new IllegalArgumentException("La moyenne doit etre comprise entre 0 et 20");
        }
        this.moyenne = moyenne;
    }

    public void ajouterCours(Cours cours) {
        if (cours == null) {
            throw new IllegalArgumentException("Le cours ne peut pas etre null");
        }
        listeCours.add(cours);
    }

    public List<Cours> getListeCours() {
        return Collections.unmodifiableList(listeCours);
    }

    public List<Double> getNotes() {
        return Collections.unmodifiableList(notes);
    }

    public void ajouterNote(double note) {
        if (note < 0.0 || note > 20.0) {
            throw new IllegalArgumentException("La note doit etre comprise entre 0 et 20");
        }
        notes.add(note);
        moyenne = notes.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        notifierObservers(note);
    }

    public void ajouterObserver(EtudiantObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer obligatoire");
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void retirerObserver(EtudiantObserver observer) {
        observers.remove(observer);
    }

    public void setMentionStrategy(MentionStrategy mentionStrategy) {
        if (mentionStrategy == null) {
            throw new IllegalArgumentException("Strategy obligatoire");
        }
        this.mentionStrategy = mentionStrategy;
    }

    public String calculerMention() {
        return mentionStrategy.calculerMention(moyenne);
    }

    @Override
    public String afficherDetails() {
        String cours = listeCours.isEmpty()
            ? "aucun"
            : listeCours.stream().map(Cours::getNomCours).reduce((a, b) -> a + ", " + b).orElse("aucun");

        return "Etudiant{" +
            "nom='" + getNom() + '\'' +
            ", age=" + getAge() +
            ", numeroEtudiant='" + numeroEtudiant + '\'' +
            ", moyenne=" + moyenne +
            ", mention='" + calculerMention() + '\'' +
            ", cours=" + cours +
            '}';
    }

    @Override
    public Etudiant getEtudiant() {
        return this;
    }

    private void notifierObservers(double note) {
        for (EtudiantObserver observer : observers) {
            observer.noteAjoutee(this, note);
        }
    }
}
