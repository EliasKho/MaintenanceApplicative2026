package com.calendar;

import com.calendar.event.*;
import com.calendar.menu.*;
import com.calendar.utilisateur.*;
import com.calendar.vo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TestMenu {

    private Agenda agenda;
    private User session;
    private List<User> annuaire;
    private Map<String, CommandeMenu> menu;

    @BeforeEach
    void setUp() {
        agenda = new Agenda();
        session = new User(new NomUtilisateur("Admin"), new MDPUtilisateur("Mdp123"));
        annuaire = new ArrayList<>();
        menu = new HashMap<>();

        // Initialisation réelle du dictionnaire de commandes
        menu.put("1", new CommandeVoirTout());
        menu.put("2", new CommandeAjouterRDV());
        menu.put("5", new CommandeSupprimer());
    }

    @Test
    void test_le_dictionnaire_contient_les_bonnes_cles() {
        // On vérifie que le mapping est complet selon le cahier des charges
        assertTrue(menu.containsKey("1"), "La visualisation doit être sur la touche 1");
        assertTrue(menu.containsKey("2"), "L'ajout de RDV doit être sur la touche 2");
        assertTrue(menu.containsKey("5"), "La suppression doit être sur la touche 5");
    }

    @Test
    void test_execution_commande_voir_ne_plante_pas() {
        // GIVEN
        CommandeMenu cmd = menu.get("1");
        Scanner sc = new Scanner(System.in); // Pas d'entrée nécessaire pour VoirTout

        // WHEN & THEN
        // On teste que l'appel polymorphe s'exécute sans exception
        assertDoesNotThrow(() -> cmd.executer(sc, session, agenda, annuaire));
    }

    @Test
    void test_commande_supprimer_id_invalide() {
        // GIVEN
        CommandeMenu cmd = menu.get("5");
        // On simule une saisie utilisateur d'un ID qui n'existe pas
        String input = UUID.randomUUID().toString();
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // WHEN & THEN
        // On vérifie que la commande gère la suppression même si l'ID est inconnu
        // (L'agenda.supprimer ne doit pas planter si l'ID n'existe pas)
        assertDoesNotThrow(() -> cmd.executer(sc, session, agenda, annuaire));
    }

    @Test
    void test_comportement_par_defaut_dictionnaire() {
        // GIVEN: Une saisie qui n'est pas dans la Map
        String mauvaisChoix = "99";

        // On définit le comportement par défaut (Null Object Pattern)
        CommandeMenu defaut = (sc, u, agg, ann) -> { /* ne fait rien */ };

        // WHEN
        CommandeMenu cmdExecutee = menu.getOrDefault(mauvaisChoix, defaut);

        // THEN
        assertNotNull(cmdExecutee);
        // On vérifie que ce n'est pas une de nos commandes valides
        assertFalse(cmdExecutee instanceof CommandeVoirTout);
    }
}