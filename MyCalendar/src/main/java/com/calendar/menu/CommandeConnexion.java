package com.calendar.menu;

import com.calendar.Agenda;
import com.calendar.event.*;
import com.calendar.utilisateur.*;
import java.util.*;

public class CommandeConnexion implements CommandeMenu {
    @Override
    public void executer(Scanner sc, User session, Agenda agenda, List<User> annuaire) {
        System.out.print("Nom d'utilisateur : ");
        String nom = sc.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = sc.nextLine();

        User trouve = annuaire.stream()
                .filter(u -> u.nomUtilisateur.valeur().equals(nom) && u.mdpUtilisateur.valeur().equals(mdp))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Identifiants incorrects."));

        // Astuce technique : on le déplace à la fin pour que Main récupère la session
        annuaire.remove(trouve);
        annuaire.add(trouve);
        System.out.println("Connexion réussie !");
    }
}