package com.calendar.menu;

import com.calendar.Agenda;
import com.calendar.event.*;
import com.calendar.ServiceSauvegarde;
import com.calendar.utilisateur.*;
import com.calendar.vo.*;
import java.util.*;

public class CommandeCreerCompte implements CommandeMenu {
    private final ServiceSauvegarde sauvegarde;

    public CommandeCreerCompte(ServiceSauvegarde sauvegarde) {
        this.sauvegarde = sauvegarde;
    }

    @Override
    public void executer(Scanner sc, User session, Agenda agenda, List<User> annuaire) {
        System.out.print("Nom d'utilisateur : ");
        String nom = sc.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = sc.nextLine();

        User nouveau = new User(new NomUtilisateur(nom), new MDPUtilisateur(mdp));
        annuaire.add(nouveau);

        // Sauvegarde immédiate après modification
        sauvegarde.sauvegarderUtilisateurs(annuaire);
        System.out.println("Compte créé et sauvegardé.");
    }
}