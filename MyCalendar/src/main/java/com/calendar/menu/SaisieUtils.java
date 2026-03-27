package com.calendar.menu;


import com.calendar.vo.DateEvenement;

import java.util.Scanner;

class SaisieUtils {
    public static String demander(Scanner sc, String invite) {
        System.out.print(invite + " : ");
        return sc.nextLine();
    }

    public static DateEvenement demanderDate(Scanner sc) {
        return new DateEvenement(
                Integer.parseInt(demander(sc, "Année")),
                Integer.parseInt(demander(sc, "Mois")),
                Integer.parseInt(demander(sc, "Jour")),
                Integer.parseInt(demander(sc, "Heure")),
                Integer.parseInt(demander(sc, "Minute"))
        );
    }
}