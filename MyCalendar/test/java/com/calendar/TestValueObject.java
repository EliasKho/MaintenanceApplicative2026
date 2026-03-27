package com.calendar;

import com.calendar.utilisateur.User;
import com.calendar.vo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestValueObject {


    @Test
    void test_LieuReunion_valide(){
        // GIVEN
        String nomSalle = "Salle de conférence A";

        // WHEN
        LieuReunion lieu = new LieuReunion(nomSalle);

        // THEN
        assertNotNull(lieu);
        assertEquals(nomSalle, lieu.valeur());
    }

    @Test
    void test_LieuReunion_null(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new LieuReunion(null);
        }, "Un lieu de réunion ne peut pas être null.");
    }

    @Test
    void test_LieuReunion_vide(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new LieuReunion("");
        }, "Un lieu de réunion ne peut pas être null.");
    }

    @Test
    void test_NomUtilisateur_valide(){
        // GIVEN
        String username = "Alice123";

        // WHEN
        NomUtilisateur nom = new NomUtilisateur(username);

        // THEN
        assertNotNull(nom);
        assertEquals(username, nom.valeur());
    }

    @Test
    void test_NomUtilisateur_null(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new NomUtilisateur(null);
        }, "Le nom d'utilisateur ne peut pas être nul.");
    }

    @Test
    void test_NomUtilisateur_vide(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new NomUtilisateur("");
        }, "Le nom d'utilisateur ne peut pas être nul.");
    }

    @Test
    void test_MDPUtilisateur_valide(){
        // GIVEN
        String motdepasse = "Mdp123";

        // WHEN
        MDPUtilisateur mdp = new MDPUtilisateur(motdepasse);

        // THEN
        assertNotNull(mdp);
        assertEquals(motdepasse, mdp.valeur());
    }

    @Test
    void test_MDPUtilisateur_null(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new MDPUtilisateur(null);
        }, "Le mot de passe ne peut pas être nul.");
    }

    @Test
    void test_MDPUtilisateur_vide(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new MDPUtilisateur("");
        }, "Le mot de passe ne peut pas être nul.");
    }

    @Test
    void test_TitreEvenement_valide(){
        // GIVEN
        String titreEvenement = "Meeting";

        // WHEN
        TitreEvenement titre = new TitreEvenement(titreEvenement);

        // THEN
        assertNotNull(titre);
        assertEquals(titreEvenement, titre.valeur());
    }

    @Test
    void test_TitreEvenement_null(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new TitreEvenement(null);
        }, "Le titre de l'évènement ne doit pas être nul.");
    }

    @Test
    void test_TitreEvenement_vide(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new TitreEvenement("");
        }, "Le titre de l'évènement ne doit pas être nul.");
    }

    @Test
    void test_Recurrence_valide(){
        // GIVEN
        int recurrence = 1;

        // WHEN
        Recurrence rec = new Recurrence(recurrence);

        // THEN
        assertNotNull(rec);
        assertEquals(recurrence, rec.valeur());
    }

    @Test
    void test_Recurrence_zero(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new Recurrence(0);
        },"La récurrence de l'évènement périodique doit être supérieur à 0 jours.");
    }

    @Test
    void test_Recurrence_negatif(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new Recurrence(-1);
        },"La récurrence de l'évènement périodique doit être supérieur à 0 jours.");
    }

    @Test
    void test_DureeMinute_valide(){
        // GIVEN
        int minute = 1;

        // WHEN
        DureeMinute min = new DureeMinute(minute);

        // THEN
        assertNotNull(min);
        assertEquals(minute, min.valeur());
    }

    @Test
    void test_DureeMinute_null(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new DureeMinute(0);
        },"La durée de l'évènement doit être supérieure à 0.");
    }

    @Test
    void test_DureeMinute_negatif(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new DureeMinute(-1);
        },"La durée de l'évènement doit être supérieure à 0.");
    }

    @Test
    void test_DateEvenement_valide(){
        // GIVEN
        int annee = 2026;
        int mois = 1;
        int jour = 1;
        int heure = 8;
        int minute = 0;

        // WHEN
        DateEvenement date = new DateEvenement(annee,mois,jour,heure,minute);

        // THEN
        assertNotNull(date);
        assertEquals(annee, date.annee());
        assertEquals(mois, date.mois());
        assertEquals(heure, date.heure());
        assertEquals(minute, date.minute());
    }

    @Test
    void test_DateEvenement_annee_invalide(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new DateEvenement(1950,1,1,8,0);
        },"La date ne peut pas être inférieure à 2000.");
    }

    @Test
    void test_DateEvenement_mois_zero(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new DateEvenement(2026,0,1,8,0);
        },"Le mois entré n'est pas entre 1 et 12.");
    }

    @Test
    void test_DateEvenement_mois_13(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new DateEvenement(2026,13,1,8,0);
        },"Le mois entré n'est pas entre 1 et 12.");
    }

    @Test
    void test_DateEvenement_jour_zero(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new DateEvenement(2026,1,0,8,0);
        },"Le jour entré est invalide.");
    }

    @Test
    void test_DateEvenement_jour_32(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new DateEvenement(2026,1,32,8,0);
        },"Le jour entré est invalide.");
    }

    @Test
    void test_DateEvenement_heure_negatif(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new DateEvenement(2026,1,1,-1,0);
        },"L'heure entrée n'est pas entre 0 et 23.");
    }

    @Test
    void test_DateEvenement_heure_24(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new DateEvenement(2026,1,1,24,0);
        },"L'heure entrée n'est pas entre 0 et 23.");
    }

    @Test
    void test_DateEvenement_minute_negatif(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new DateEvenement(2026,1,1,1,-1);
        },"La minute entrée n'est pas entre 0 et 59.");
    }

    @Test
    void test_DateEvenement_minute_60(){
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new DateEvenement(2026,1,1,1,60);
        },"La minute entrée n'est pas entre 0 et 59.");
    }

    @Test
    void test_DateEvenement_toString(){
        //WHEN
        String toString =  new DateEvenement(2026,1,2,14,4).toString();

        //THEN
        assertEquals("2026/01/02 à 14h04", toString);
    }

    @Test
    void test_User_toString(){
        //GIVEN
        NomUtilisateur username = new NomUtilisateur("Alice123");
        MDPUtilisateur password = new MDPUtilisateur("mdp123");

        //WHEN
        String toString = new User(username,password).toString();

        //THEN
        assertEquals("Alice123", toString);
    }

    @Test
    void test_DateEvenement_estAvant_vrai_meme_jour() {
        // GIVEN
        DateEvenement matin = new DateEvenement(2026, 1, 1, 8, 0);
        DateEvenement plusTard = new DateEvenement(2026, 1, 1, 8, 30);

        // WHEN
        boolean resultat = matin.estAvant(plusTard);

        // THEN
        assertTrue(resultat);
    }

    @Test
    void test_DateEvenement_estAvant_vrai_jours_differents() {
        // GIVEN
        DateEvenement jour1 = new DateEvenement(2026, 1, 1, 20, 0);
        DateEvenement jour2 = new DateEvenement(2026, 1, 2, 8, 0);

        // WHEN
        boolean resultat = jour1.estAvant(jour2);

        // THEN
        assertTrue(resultat);
    }

    @Test
    void test_DateEvenement_estAvant_faux_apres() {
        // GIVEN
        DateEvenement tard = new DateEvenement(2026, 1, 1, 10, 0);
        DateEvenement tot = new DateEvenement(2026, 1, 1, 9, 0);

        // WHEN
        boolean resultat = tard.estAvant(tot);

        // THEN
        assertFalse(resultat);
    }

    @Test
    void test_DateEvenement_estAvant_faux_identique() {
        // GIVEN
        DateEvenement date1 = new DateEvenement(2026, 1, 1, 8, 0);
        DateEvenement date2 = new DateEvenement(2026, 1, 1, 8, 0);

        // WHEN
        boolean resultat = date1.estAvant(date2);

        // THEN
        assertFalse(resultat);
    }

    @Test
    void test_DateEvenement_ecartEnJours_meme_jour() {
        // GIVEN
        DateEvenement matin = new DateEvenement(2026, 1, 1, 8, 0);
        DateEvenement soir = new DateEvenement(2026, 1, 1, 20, 0);

        // WHEN
        long ecart = matin.ecartEnJours(soir);

        // THEN
        assertEquals(0, ecart);
    }

    @Test
    void test_DateEvenement_ecartEnJours_lendemain() {
        // GIVEN
        DateEvenement jour1 = new DateEvenement(2026, 1, 1, 23, 0);
        DateEvenement jour2 = new DateEvenement(2026, 1, 2, 1, 0);

        // WHEN
        long ecart = jour1.ecartEnJours(jour2);

        // THEN
        assertEquals(1, ecart);
    }

    @Test
    void test_DateEvenement_ecartEnJours_une_semaine() {
        // GIVEN
        DateEvenement dateInitiale = new DateEvenement(2026, 1, 1, 8, 0);
        DateEvenement datePlusTard = new DateEvenement(2026, 1, 8, 8, 0);

        // WHEN
        long ecart = dateInitiale.ecartEnJours(datePlusTard);

        // THEN
        assertEquals(7, ecart);
    }


}
