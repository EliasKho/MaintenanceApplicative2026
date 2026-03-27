package com.calendar.menu;

import com.calendar.vo.DateEvenement;
import java.util.Scanner;

public class SaisieUtils {
    public static DateEvenement demanderDate(Scanner sc) {
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(sc.nextLine());
        System.out.print("Mois (1-12) : ");
        int mois = Integer.parseInt(sc.nextLine());
        System.out.print("Jour (1-31) : ");
        int jour = Integer.parseInt(sc.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(sc.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(sc.nextLine());
        return new DateEvenement(annee, mois, jour, heure, minute);
    }
}