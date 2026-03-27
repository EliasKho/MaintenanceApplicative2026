package com.calendar.menu;

import com.calendar.Agenda;
import com.calendar.utilisateur.User;
import java.util.*;

public class CommandeVoirTout implements CommandeMenu {
    public void executer(Scanner sc, User session, Agenda agenda, List<User> ann) {
        agenda.afficherTout();
    }
}
