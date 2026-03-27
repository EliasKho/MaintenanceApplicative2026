package com.calendar;

import com.calendar.utilisateur.*;
import com.calendar.vo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        // On simule l'enregistrement des commandes dans le dictionnaire
        menu.put("1", new CommandeVoir());
        menu.put("2", new CommandeAjouterRDV());
    }

    @Test
    void test_recherche_commande_dans_map() {
        // GIVEN: L'utilisateur tape "1"
        String saisie = "1";

        // WHEN: On cherche dans le dictionnaire
        CommandeMenu cmd = menu.get(saisie);

        // THEN: On doit récupérer une instance de CommandeVoir
        assertNotNull(cmd);
        assertTrue(cmd instanceof CommandeVoir);
    }

    @Test
    void test_commande_inconnue_renvoie_null() {
        // GIVEN: L'utilisateur tape "99" (n'existe pas)
        String saisie = "99";

        // WHEN
        CommandeMenu cmd = menu.get(saisie);

        // THEN
        assertNull(cmd);
    }
}