package com.calendar.menu;

import com.calendar.Agenda;
import com.calendar.utilisateur.User;
import java.util.Scanner;
import java.util.List;

public interface CommandeMenu {
    void executer(Scanner sc, User session, Agenda agenda, List<User> annuaire);
}