package com.calendar.menu;

import com.calendar.menu.*;
import com.calendar.Agenda;
import com.calendar.utilisateur.*;
import com.calendar.vo.*;
import java.util.List;
import java.util.Scanner;

public class CommandeConnexion implements CommandeMenu {
    @Override
    public void executer(Scanner sc, User session, Agenda agenda, List<User> annuaire) {
        System.out.print("Nom d'utilisateur : ");
        String nom = sc.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = sc.nextLine();

        // On cherche l'utilisateur. Si non trouvé, on lève une exception pour le catch du Main
        annuaire.stream()
                .filter(u -> u.nomUtilisateur.valeur().equals(nom) && u.mdpUtilisateur.valeur().equals(mdp))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Identifiants incorrects."));

        System.out.println("Connexion réussie !");
    }
}
