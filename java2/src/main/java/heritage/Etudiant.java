package heritage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Etudiant extends Personne {
    private final String numeroEtudiant;
    private final List<Cours> listeCours;
    private final List<Double> notes;
    private final List<NoteObserver> observers;
    private double moyenne;
    private MentionStrategy mentionStrategy;

    public Etudiant(String nom, int age, String numeroEtudiant, double moyenneInitiale) {
        super(nom, age);
        if (numeroEtudiant == null || numeroEtudiant.isBlank()) {
            throw new IllegalArgumentException("Le numero etudiant est obligatoire");
        }
        if (moyenneInitiale < 0.0 || moyenneInitiale > 20.0) {
            throw new IllegalArgumentException("La moyenne doit etre comprise entre 0 et 20");
        }

        this.numeroEtudiant = numeroEtudiant;
        this.listeCours = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.moyenne = moyenneInitiale;
        this.mentionStrategy = new StandardMentionStrategy();

        if (moyenneInitiale > 0.0) {
            this.notes.add(moyenneInitiale);
        }
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public List<Cours> getListeCours() {
        return Collections.unmodifiableList(listeCours);
    }

    public List<Double> getNotes() {
        return Collections.unmodifiableList(notes);
    }

    public void setMentionStrategy(MentionStrategy mentionStrategy) {
        if (mentionStrategy == null) {
            throw new IllegalArgumentException("La strategy de mention est obligatoire");
        }
        this.mentionStrategy = mentionStrategy;
    }

    public String getMention() {
        return mentionStrategy.calculerMention(moyenne);
    }

    public void ajouterCours(Cours cours) {
        if (cours == null) {
            throw new IllegalArgumentException("Le cours ne peut pas etre null");
        }
        listeCours.add(cours);
    }

    public void ajouterObservateur(NoteObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("L'observateur ne peut pas etre null");
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void retirerObservateur(NoteObserver observer) {
        observers.remove(observer);
    }

    public void ajouterNote(double note) {
        if (note < 0.0 || note > 20.0) {
            throw new IllegalArgumentException("La note doit etre comprise entre 0 et 20");
        }
        notes.add(note);
        recalculerMoyenne();
        notifierObservateurs(note);
    }

    private void recalculerMoyenne() {
        this.moyenne = notes.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    private void notifierObservateurs(double note) {
        for (NoteObserver observer : observers) {
            observer.onNoteAjoutee(this, note, moyenne);
        }
    }

    @Override
    public String afficherDetails() {
        return "Etudiant{" +
            "numeroEtudiant='" + numeroEtudiant + '\'' +
            ", nom='" + getNom() + '\'' +
            ", age=" + getAge() +
            ", moyenne=" + moyenne +
            ", mention='" + getMention() + '\'' +
            ", cours=" + listeCours +
            '}';
    }
}
