package com.calendar.menu;

import com.calendar.Agenda;
import com.calendar.event.*;
import com.calendar.utilisateur.*;
import java.util.*;

public class CommandeVoirTout implements CommandeMenu {
    @Override
    public void executer(Scanner sc, User session, Agenda agenda, List<User> annuaire) {
        System.out.println("\n=== Menu de visualisation d'Événements ===");
        agenda.afficherTout();
    }
}