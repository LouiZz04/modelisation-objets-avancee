package heritage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DecoratorTest {

    @Test
    void shouldAddResponsibilitiesWithoutChangingEtudiantClass() {
        Etudiant etudiant = new Etudiant("Alice", 20, "E01", 14.0);
        EtudiantComponent composant = new EtudiantSimpleComponent(etudiant);
        EtudiantComponent boursier = new EtudiantBoursierDecorator(composant, 600.0);
        EtudiantComponent delegueEtBoursier = new EtudiantDelegueDecorator(boursier, "L3-INFO");

        String details = delegueEtBoursier.afficherDetails();

        assertTrue(details.contains("Alice"));
        assertTrue(details.contains("Boursier"));
        assertTrue(details.contains("Delegue"));
    }
}
