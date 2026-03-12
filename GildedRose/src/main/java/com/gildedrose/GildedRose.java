package com.gildedrose;

/**
 * Classe représentant une auberge
 */
class GildedRose {

    //ATTRIBUTS

    /**
     * liste des objets
     */
    Item[] items;


    //CONSTRUCTEUR

    /**
     * Constructeur à partir d'un tableau d'objets
     * @param items tableau d'items
     */
    public GildedRose(Item[] items) {
        this.items = items;
    }


    //METHODES

    /**
     * Méthode pour mettre à jour la qualité de la liste des items
     */
    public void updateQuality() {
        for (Item item : items) {
            item.updateQuality();
        }
    }
}
