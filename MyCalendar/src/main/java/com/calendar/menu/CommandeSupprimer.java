package com.calendar.menu;

import com.calendar.Agenda;
import com.calendar.event.*;
import com.calendar.ServiceSauvegarde;
import com.calendar.utilisateur.*;
import com.calendar.vo.*;
import java.util.*;

public class CommandeSupprimer implements CommandeMenu {
    private final ServiceSauvegarde sauvegarde;

    public CommandeSupprimer(ServiceSauvegarde sauvegarde) {
        this.sauvegarde = sauvegarde;
    }

    @Override
    public void executer(Scanner sc, User session, Agenda agenda, List<User> annuaire) {
        System.out.print("Entrez l'UUID de l'événement à supprimer : ");
        EventId id = new EventId(UUID.fromString(sc.nextLine()));

        agenda.supprimer(id);
        sauvegarde.sauvegarderAgenda(agenda);
        System.out.println("Événement supprimé.");
    }
}