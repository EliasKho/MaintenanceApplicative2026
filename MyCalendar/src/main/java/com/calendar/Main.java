package com.calendar;

import com.calendar.menu.*;
import com.calendar.utilisateur.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        ServiceSauvegarde sauvegarde = new ServiceSauvegarde();

        // CHARGEMENT INITIAL
        List<User> annuaire = sauvegarde.chargerUtilisateurs();
        // agenda.charger(sauvegarde.chargerEvenements());

        afficherLogo();

        Map<String, CommandeMenu> authMenu = new HashMap<>();
        // On passe désormais la sauvegarde aux commandes pour qu'elles enregistrent après un ajout
        authMenu.put("1", new CommandeConnexion());
        authMenu.put("2", new CommandeCreerCompte(sauvegarde));

        Map<String, CommandeMenu> menu = new HashMap<>();
        menu.put("1", new CommandeVoirTout());
        menu.put("2", new CommandeAjouterRDV(sauvegarde));
        // ... (Idem pour les autres commandes)

        lancement(scanner, agenda, annuaire, authMenu, menu, sauvegarde);
    }

    private static void lancement(Scanner sc, Agenda agg, List<User> ann, Map<String, CommandeMenu> auth, Map<String, CommandeMenu> menu, ServiceSauvegarde sv) {
        afficherLogo();
        System.out.println("1 - Se connecter");
        System.out.println("2 - Créer un compte");
        System.out.print("Choix : ");

        String choix = sc.nextLine();

        try {
            // On récupère la taille avant pour détecter un nouvel utilisateur si besoin
            int nbAvant = ann.size();

            // Exécution de la commande d'auth (Connexion ou Création)
            // Note : CommandeConnexion doit lever une Exception si l'utilisateur n'est pas trouvé
            auth.getOrDefault(choix, (s, u, ag, an) -> { throw new RuntimeException("Choix invalide"); })
                    .executer(sc, null, agg, ann);

            // Récupération de l'utilisateur de session :
            // Soit le dernier ajouté (Création), soit celui qui correspond aux identifiants.
            // On peut stocker l'utilisateur "actif" dans une variable temporaire
            // ou simplement reprendre le dernier de la liste si on vient de créer un compte.
            User session = ann.get(ann.size() - 1);

            // Passage à l'agenda
            boucleApplication(sc, session, agg, ann, menu, sv);

        } catch (Exception e) {
            System.err.println("\n[ERREUR] : " + e.getMessage());
            // Pas de IF : on relance la méthode (récursion) pour afficher à nouveau le login
            lancement(sc, agg, ann, auth, menu, sv);
        }
    }

    private static void boucleApplication(Scanner sc, User u, Agenda agg, List<User> ann, Map<String, CommandeMenu> menu, ServiceSauvegarde sv) {
        System.out.println("\nBonjour, " + u.nomUtilisateur.valeur());
        System.out.println("=== Menu Gestionnaire d'Événements ===");
        System.out.println("1 - Voir les événements");
        System.out.println("2 - Ajouter un rendez-vous perso");
        System.out.println("3 - Ajouter une réunion");
        System.out.println("4 - Ajouter un évènement périodique");
        System.out.println("5 - Supprimer un événement");
        System.out.println("6 - Se déconnecter");
        System.out.print("Votre choix : ");

        String choix = sc.nextLine();

        try {
            menu.getOrDefault(choix, (s, us, ag, an) -> System.out.println("Option invalide."))
                    .executer(sc, u, agg, ann);

            // Sauvegarde automatique après chaque action réussie
            sv.sauvegarderAgenda(agg);
        } catch (Exception e) {
            System.err.println("\n[ERREUR] : " + e.getMessage());
        }

        Map<Boolean, Runnable> cycle = new HashMap<>();
        cycle.put(true, () -> boucleApplication(sc, u, agg, ann, menu, sv));
        cycle.put(false, () -> lancement(sc, agg, ann, (Map)new HashMap<>(), menu, sv)); // Retour au login

        // On reboucle sauf si choix est "6"
        cycle.get(!choix.equals("6")).run();
    }

    private static void afficherLogo() {
        System.out.println("  _____         _                   _                __  __");
        System.out.println(" / ____|       | |                 | |              |  \\/  |");
        System.out.println("| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
        System.out.println("| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
        System.out.println("| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
        System.out.println(" \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
        System.out.println("                                                                                   __/ |");
        System.out.println("                                                                                  |___/");
    }
}