package com.calendar.vo;

/**
 * Valeur représentant le mot de passe d'un utilisateur
 * @param valeur
 */
public record MDPUtilisateur(String valeur) {
    public MDPUtilisateur {
        //TODO Ajouter conditions sur le mot de passe (8 caractères minimum par exemple)
        if (valeur == null || valeur.isBlank()) throw new IllegalArgumentException("Le mot de passe ne peut pas être nul.");
    }
}
