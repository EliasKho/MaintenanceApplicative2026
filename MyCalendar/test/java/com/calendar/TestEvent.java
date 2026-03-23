package com.calendar;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TestEvent {

    LocalDateTime dateDebut =  LocalDateTime.of(2026,1,1,8,0,0);

    Event rdv = new Event("RDV_PERSONNEL","titre","proprio",dateDebut,60,"endroit",
            "participant",5);

    Event reunion = new Event("REUNION","titre","proprio",dateDebut,60,"endroit",
            "participant",5);

    Event periodique = new Event("PERIODIQUE","titre","proprio",dateDebut,60,"endroit",
            "participant",5);

    Event autre = new Event("autre","titre","proprio",dateDebut,60,"endroit",
            "participant",5);



    @Test
    void testConstructeur(){

        assertEquals("REUNION", reunion.type);
        assertEquals("titre", reunion.title);
        assertEquals("proprio", reunion.proprietaire);
        assertSame(reunion.dateDebut,dateDebut);
        assertEquals(60, reunion.dureeMinutes);
        assertEquals("endroit", reunion.lieu);
        assertEquals("participant", reunion.participants);
        assertEquals(5, reunion.frequenceJours);

    }

    @Test
    void test_description(){
        assertEquals("RDV : titre à "+dateDebut.toString(),rdv.description());
        assertEquals("Réunion : titre à endroit avec participant",reunion.description());
        assertEquals("Événement périodique : titre tous les 5 jours",periodique.description());
        assertEquals("",autre.description());
    }
}
