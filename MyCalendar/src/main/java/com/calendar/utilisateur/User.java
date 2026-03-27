package com.calendar.utilisateur;

import com.calendar.vo.MDPUtilisateur;
import com.calendar.vo.NomUtilisateur;

/**
 * Classe représentant un utilisateur
 */
public class User {
    public NomUtilisateur nomUtilisateur;
    public MDPUtilisateur mdpUtilisateur;

    public User(NomUtilisateur username, MDPUtilisateur mdp) {
        this.nomUtilisateur = username;
        this.mdpUtilisateur = mdp;
    }

    @Override
    public String toString()
    {
        return nomUtilisateur.toString();
    }
}
