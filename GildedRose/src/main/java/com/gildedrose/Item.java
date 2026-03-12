package com.gildedrose;

/**
 * Classe représentant un objet (item)
 */
public class Item {

    //ATTRIBUTS

    /**
     * nom de l'objet
     */
    public String name;

    /**
     * prix de vente de l'objet
     */
    public int sellIn;

    /**
     * qualité de l'objet
     */
    public int quality;


    //CONSTRUCTEUR

    /**
     * Constructeur de l'item
     * @param name nom
     * @param sellIn prix
     * @param quality qualité
     */
    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }


    //METHODES

    /**
     * Méthode toString pour imprimer l'objet
     * @return
     */
   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
