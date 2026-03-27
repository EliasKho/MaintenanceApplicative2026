package com.calendar.menu;

import com.calendar.Agenda;
import com.calendar.event.*;
import com.calendar.ServiceSauvegarde;
import com.calendar.utilisateur.*;
import com.calendar.vo.*;
import java.util.*;

public class CommandeAjouterRDV implements CommandeMenu {
    private final ServiceSauvegarde sauvegarde;

    public CommandeAjouterRDV(ServiceSauvegarde sauvegarde) {
        this.sauvegarde = sauvegarde;
    }

    @Override
    public void executer(Scanner sc, User session, Agenda agenda, List<User> annuaire) {
        System.out.print("Titre de l'événement : ");
        TitreEvenement titre = new TitreEvenement(sc.nextLine());
        DateEvenement date = SaisieUtils.demanderDate(sc);
        System.out.print("Durée (en minutes) : ");
        DureeMinute duree = new DureeMinute(Integer.parseInt(sc.nextLine()));

        agenda.ajouter(new RDV(titre, session, date, duree));
        sauvegarde.sauvegarderAgenda(agenda);
        System.out.println("Événement ajouté.");
    }
}