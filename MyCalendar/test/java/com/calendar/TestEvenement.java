package com.calendar;

import com.calendar.event.*;
import com.calendar.utilisateur.*;
import com.calendar.vo.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TestEvenement {

    //Construction des value objects en amont

    TitreEvenement titreRDV = new TitreEvenement("Rendez-Vous");
    TitreEvenement titreReunion = new TitreEvenement("Réunion");
    TitreEvenement titrePeriodique = new TitreEvenement("Périodique");
    DureeMinute dureeMinute = new DureeMinute(60);
    User user = new User(new NomUtilisateur("u1"),new MDPUtilisateur("mdp1"));
    User user2 = new User(new NomUtilisateur("u2"),new MDPUtilisateur("mdp2"));
    Recurrence recurrence = new Recurrence(7);

    DateEvenement dateEvenement = new DateEvenement(2026,1,1,8,0);
    List<User> users = new ArrayList<User>(List.of(user,user2));


    //Tests

    @Test
    void test_constructeur_rdv() {
        //WHEN
        RDV rdv = new RDV(titreRDV, user, dateEvenement, dureeMinute);

        //THEN
        assertNotNull(rdv);
        assertEquals(titreRDV, rdv.titreEvenement);
        assertEquals(user,rdv.proprietaire);
        assertEquals(dateEvenement,rdv.dateDebut);
        assertEquals(dureeMinute,rdv.dureeMinutes);
    }

    @Test
    void test_constructeur_reunion() {
        //GIVEN
        LieuReunion lieu = new LieuReunion("salle1");

        //WHEN
        Reunion reunion = new Reunion(titreReunion, user, dateEvenement, dureeMinute, lieu, users);

        //THEN
        assertNotNull(reunion);
        assertEquals(titreReunion, reunion.titreEvenement);
        assertEquals(user,reunion.proprietaire);
        assertEquals(dateEvenement,reunion.dateDebut);
        assertEquals(dureeMinute,reunion.dureeMinutes);
        assertEquals(lieu,reunion.lieuReunion);
        assertEquals(users,reunion.participants);
    }

    @Test
    void test_constructeur_periodique() {
        //WHEN
        Periodique periodique = new Periodique(titrePeriodique, user, dateEvenement, dureeMinute, recurrence);

        //THEN
        assertNotNull(periodique);
        assertEquals(titrePeriodique, periodique.titreEvenement);
        assertEquals(user,periodique.proprietaire);
        assertEquals(dateEvenement,periodique.dateDebut);
        assertEquals(dureeMinute,periodique.dureeMinutes);
        assertEquals(recurrence,periodique.recurrence);
    }

    @Test
    void test_description_rdv() {
        //WHEN
        RDV rdv = new RDV(titreRDV, user, dateEvenement, dureeMinute);

        //THEN
        assertEquals("RDV : "+ rdv.titreEvenement.valeur() + " à " + dateEvenement.toString(),rdv.description());
    }

    @Test
    void test_description_reunion_0participants() {
        //WHEN
        Reunion reunion = new Reunion(titreReunion, user, dateEvenement, dureeMinute, new LieuReunion("salle1"), new ArrayList<>());

        //THEN
        assertEquals("Réunion : "+ reunion.titreEvenement.valeur() + " à " + reunion.lieuReunion.valeur() +
                        " avec u1",
                reunion.description());
    }

    @Test
    void test_description_reunion_1participant() {
        //WHEN
        Reunion reunion = new Reunion(titreReunion, user, dateEvenement, dureeMinute, new LieuReunion("salle1"),
                new ArrayList<User>(List.of(user2)));

        //THEN
        assertEquals("Réunion : "+ reunion.titreEvenement.valeur() + " à " + reunion.lieuReunion.valeur() +
                        " avec " + "u1, u2",
                reunion.description());
    }

    @Test
    void test_description_reunion_2participants() {
        //WHEN
        Reunion reunion = new Reunion(titreReunion, user, dateEvenement, dureeMinute, new LieuReunion("salle1"), users);

        //THEN
        assertEquals("Réunion : "+ reunion.titreEvenement.valeur() + " à " + reunion.lieuReunion.valeur() +
                        " avec " + "u1, u1, u2",
                reunion.description());
    }

    @Test
    void test_description_periodique() {
        //WHEN
        Periodique periodique = new Periodique(titrePeriodique, user, dateEvenement, dureeMinute, recurrence);

        //THEN
        assertEquals("Événement périodique : "+ periodique.titreEvenement.valeur() + " tous les " + recurrence.valeur() + "jours",
                periodique.description());
    }

    @Test
    void test_getDateFin_normal(){
        //GIVEN
        //2026/01/01 à 8h00 -> 8h45
        RDV rdv = new RDV(titreRDV, user, dateEvenement, new DureeMinute(45));

        //WHEN
        DateEvenement fin = rdv.getDateFin();

        //THEN
        assertEquals("2026/01/01 à 08h45", fin.toString());
    }

    @Test
    void test_getDateFin_changement_heure(){
        //GIVEN
        //2026/01/01 à 8h00 -> 9h15
        RDV rdv = new RDV(titreRDV, user, dateEvenement, new DureeMinute(75));

        //WHEN
        DateEvenement fin = rdv.getDateFin();

        //THEN
        assertEquals("2026/01/01 à 09h15", fin.toString());
    }

    @Test
    void test_getDateFin_changement_jour(){
        //GIVEN
        //2026/01/01 à 8h00 -> 2026/01/02 à 8h01
        RDV rdv = new RDV(titreRDV, user, dateEvenement, new DureeMinute(1441));

        //WHEN
        DateEvenement fin = rdv.getDateFin();

        //THEN
        assertEquals("2026/01/02 à 08h01", fin.toString());
    }

    @Test
    void test_conflit_periodique_meme_jour() {
        // GIVEN : Un événement tous les 7 jours à 08h00 (durée 60min)
        Periodique hebdo = new Periodique(titrePeriodique, user, dateEvenement, dureeMinute, recurrence);
        // Un RDV le même jour à 08h30
        RDV rdvConflit = new RDV(titreRDV, user, new DateEvenement(2026, 1, 1, 8, 30), new DureeMinute(30));

        // WHEN
        boolean resultat = hebdo.conflit(rdvConflit);

        // THEN
        assertTrue(resultat);
    }

    @Test
    void test_conflit_periodique_semaine_suivante() {
        // GIVEN : Hebdo commence le 01/01/2026
        Periodique hebdo = new Periodique(titrePeriodique, user, dateEvenement, dureeMinute, recurrence);
        // RDV le 08/01/2026 (J+7) à 08h00
        RDV rdvSuivant = new RDV(titreRDV, user, new DateEvenement(2026, 1, 8, 8, 0), dureeMinute);

        // WHEN
        boolean resultat = hebdo.conflit(rdvSuivant);

        // THEN
        assertTrue(resultat);
    }

    @Test
    void test_conflit_symetrie_rdv_periodique() {
        // GIVEN
        Periodique hebdo = new Periodique(titrePeriodique, user, dateEvenement, dureeMinute, recurrence);
        RDV rdvConflit = new RDV(titreRDV, user, new DateEvenement(2026, 1, 8, 8, 30), new DureeMinute(30));

        // WHEN
        boolean cas1 = hebdo.conflit(rdvConflit);
        boolean cas2 = rdvConflit.conflit(hebdo);

        // THEN
        assertTrue(cas1);
        assertTrue(cas2);
    }

    @Test
    void test_pas_de_conflit_periodique_mauvais_jour() {
        // GIVEN : Hebdo le jeudi 01/01/2026
        Periodique hebdo = new Periodique(titrePeriodique, user, dateEvenement, dureeMinute, recurrence);
        // RDV le vendredi 02/01/2026 à 08h00 (Pas dans la récurrence)
        RDV rdvLendemain = new RDV(titreRDV, user, new DateEvenement(2026, 1, 2, 8, 0), dureeMinute);

        // WHEN
        boolean resultat = hebdo.conflit(rdvLendemain);

        // THEN
        assertFalse(resultat);
    }

    @Test
    void test_pas_de_conflit_periodique_bon_jour_mauvaise_heure() {
        // GIVEN : Hebdo 08h00-09h00
        Periodique hebdo = new Periodique(titrePeriodique, user, dateEvenement, dureeMinute, recurrence);
        // RDV le 08/01/2026 à 14h00
        RDV rdvApresMidi = new RDV(titreRDV, user, new DateEvenement(2026, 1, 8, 14, 0), dureeMinute);

        // WHEN
        boolean resultat = hebdo.conflit(rdvApresMidi);

        // THEN
        assertFalse(resultat);
    }

    @Test
    void test_evenement_EventID() {
        // GIVEN
        RDV rdv1 = new RDV(titreRDV, user, dateEvenement, dureeMinute);
        RDV rdv2 = new RDV(titreRDV, user, dateEvenement, dureeMinute);

        // THEN
        assertNotNull(rdv1.eventId);
        assertNotNull(rdv2.eventId);
        // On vérifie que deux objets créés avec les mêmes données ont des identités différentes
        assertNotEquals(rdv1.eventId, rdv2.eventId);
    }

}
