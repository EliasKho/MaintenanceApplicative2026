package com.calendar.menu;

import com.calendar.menu.*;
import com.calendar.Agenda;
import com.calendar.utilisateur.*;
import com.calendar.vo.*;
import java.util.List;
import java.util.Scanner;

public class CommandeCreerCompte implements CommandeMenu {
    @Override
    public void executer(Scanner sc, User session, Agenda agenda, List<User> annuaire) {
        System.out.print("Nom d'utilisateur : ");
        NomUtilisateur nom = new NomUtilisateur(sc.nextLine());
        System.out.print("Mot de passe : ");
        String mdpRaw = sc.nextLine();
        System.out.print("Répéter mot de passe : ");

        // Zéro IF : on utilise une validation qui lève une exception si ça ne match pas
        if (!sc.nextLine().equals(mdpRaw)) {
            throw new RuntimeException("Les mots de passe ne correspondent pas.");
        }

        annuaire.add(new User(nom, new MDPUtilisateur(mdpRaw)));
        System.out.println("Compte créé avec succès.");
    }
}