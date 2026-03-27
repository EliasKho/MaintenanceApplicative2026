package com.calendar.menu;

import com.calendar.Agenda;
import com.calendar.event.*;
import com.calendar.utilisateur.User;
import com.calendar.vo.*;
import java.util.*;

public class CommandeSupprimer implements CommandeMenu {
    public void executer(Scanner sc, User session, Agenda agenda, List<User> ann) {
        String id = SaisieUtils.demander(sc, "ID de l'événement à supprimer");
        agenda.supprimer(new EventId(UUID.fromString(id)));
    }
}