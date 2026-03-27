package com.calendar;

import com.calendar.event.*;
import com.calendar.utilisateur.*;
import com.calendar.vo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestAgenda {

    private Agenda agenda;
    private User proprietaire;
    private TitreEvenement titre;
    private DureeMinute uneHeure;

    @BeforeEach
    void setUp() {
        agenda = new Agenda();
        proprietaire = new User(new NomUtilisateur("u1"), new MDPUtilisateur("mdp123"));
        titre = new TitreEvenement("Test");
        uneHeure = new DureeMinute(60);
    }

    @Test
    void test_ajouter_succes() {
        // GIVEN
        RDV rdv = new RDV(titre, proprietaire, new DateEvenement(2026, 1, 1, 8, 0), uneHeure);

        // WHEN
        agenda.ajouter(rdv);

        // THEN
        assertEquals(1, agenda.getEvenements().size());
        assertEquals(rdv, agenda.getEvenements().getFirst());
    }

    @Test
    void test_ajouter_echec() {
        // GIVEN
        RDV rdv1 = new RDV(titre, proprietaire, new DateEvenement(2026, 1, 1, 8, 0), uneHeure);
        agenda.ajouter(rdv1);

        // RDV2 commence à 08h30 (pendant RDV1)
        RDV rdv2 = new RDV(titre, proprietaire, new DateEvenement(2026, 1, 1, 8, 30), uneHeure);

        // WHEN & THEN
        assertThrows(IllegalStateException.class, () -> {
            agenda.ajouter(rdv2);
        }, "L'agenda devrait refuser un événement en conflit direct.");
    }

    @Test
    void test_ajouter_periodique_conflit() {
        // GIVEN : Un cours tous les 7 jours (Jeudi 01/01/2026)
        Periodique coursHebdo = new Periodique(titre, proprietaire,
                new DateEvenement(2026, 1, 1, 8, 0),
                uneHeure, new Recurrence(7));
        agenda.ajouter(coursHebdo);

        // Un RDV ponctuel le Jeudi suivant (08/01/2026) à 08h30
        RDV rdvConflit = new RDV(titre, proprietaire,
                new DateEvenement(2026, 1, 8, 8, 30),
                new DureeMinute(15));

        // WHEN & THEN
        assertThrows(IllegalStateException.class, () -> {
            agenda.ajouter(rdvConflit);
        }, "L'agenda doit détecter le conflit avec la récurrence du périodique.");
    }

    @Test
    void test_supprimer_id() {
        // GIVEN
        RDV rdv = new RDV(titre, proprietaire, new DateEvenement(2026, 1, 1, 8, 0), uneHeure);
        EventId id = rdv.eventId;
        agenda.ajouter(rdv);

        // On vérifie qu'un nouveau RDV à la même heure est refusé
        RDV rdvConflit = new RDV(titre, proprietaire, new DateEvenement(2026, 1, 1, 8, 0), uneHeure);
        assertThrows(IllegalStateException.class, () -> agenda.ajouter(rdvConflit));

        // WHEN
        agenda.supprimer(id);

        // THEN
        // Maintenant que le premier est supprimé, on doit pouvoir ajouter le deuxième
        assertDoesNotThrow(() -> agenda.ajouter(rdvConflit));
    }

    @Test
    void test_getConflits() {
        // GIVEN
        RDV rdvExistant = new RDV(new TitreEvenement("Existant"), proprietaire,
                new DateEvenement(2026, 5, 1, 10, 0), uneHeure);
        agenda.ajouter(rdvExistant);

        RDV nouveauConflit = new RDV(new TitreEvenement("Nouveau"), proprietaire,
                new DateEvenement(2026, 5, 1, 10, 30), uneHeure);

        // WHEN
        List<Evenement> conflits = agenda.getConflits(nouveauConflit);

        // THEN
        assertEquals(1, conflits.size());
        assertEquals(rdvExistant.eventId, conflits.getFirst().eventId);
    }
}