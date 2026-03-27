package com.calendar;

import com.calendar.event.Evenement;
import com.calendar.vo.EventId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Agenda {
    private final List<Evenement> evenements;

    public Agenda() {
        this.evenements = new ArrayList<>();
    }

    /**
     * Ajoute un événement seulement s'il n'y a pas de conflit
     */
    public void ajouter(Evenement nouvelEvenement) {
        if (getConflits(nouvelEvenement).isEmpty()) {
            this.evenements.add(nouvelEvenement);
        } else {
            throw new IllegalStateException("Impossible d'ajouter : l'événement est en conflit.");
        }
    }

    /**
     * Retourne la liste des événements déjà présents qui sont en conflit avec celui-ci
     */
    public List<Evenement> getConflits(Evenement aTester) {
        return evenements.stream()
                .filter(e -> e.conflit(aTester))
                .collect(Collectors.toList());
    }

    /**
     * Suppression propre via le Value Object EventId
     */
    public void supprimer(EventId id) {
        evenements.removeIf(e -> e.eventId.equals(id));
    }

    public void afficherTout() {
        evenements.forEach(e -> System.out.println(e.description()));
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }
}