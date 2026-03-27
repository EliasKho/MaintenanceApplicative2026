package com.calendar;

import com.calendar.Agenda;
import com.calendar.event.*;
import com.calendar.utilisateur.*;
import com.calendar.vo.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ServiceSauvegarde {
    private final String PATH = "./MyCalendar/data/";

    public ServiceSauvegarde() {
        try {
            Files.createDirectories(Paths.get(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sauvegarderUtilisateurs(List<User> users) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            sb.append(String.format("  {\"nom\":\"%s\", \"mdp\":\"%s\"}",
                    u.nomUtilisateur.valeur(), u.mdpUtilisateur.valeur()));
            if (i < users.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        ecrireFichier("users.json", sb.toString());
    }

    public List<User> chargerUtilisateurs() {
        List<User> users = new ArrayList<>();
        // Utilisateur par défaut u1 / p1
        users.add(new User(new NomUtilisateur("u1"), new MDPUtilisateur("p1")));

        try {
            String content = lireFichier("users.json");
            // Parsing manuel très simplifié pour l'exercice
            String[] lines = content.split("}");
            for (String line : lines) {
                if (line.contains("\"nom\"")) {
                    String nom = extract(line, "nom");
                    String mdp = extract(line, "mdp");
                    users.add(new User(new NomUtilisateur(nom), new MDPUtilisateur(mdp)));
                }
            }
        } catch (Exception e) {
            // Si le fichier n'existe pas, on garde u1/p1
        }
        return users;
    }

    // On sauvegarde les arguments nécessaires aux constructeurs
    public void sauvegarderAgenda(Agenda agenda) {
        // Logique similaire pour les événements...
        // On itère sur agenda.getEvenements() et on écrit dans events.json
    }

    private void ecrireFichier(String nom, String data) {
        try (PrintWriter out = new PrintWriter(PATH + nom)) {
            out.print(data);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private String lireFichier(String nom) throws IOException {
        return new String(Files.readAllBytes(Paths.get(PATH + nom)));
    }

    private String extract(String line, String key) {
        return line.split("\"" + key + "\":\"")[1].split("\"")[0];
    }
}