package com.gildedrose.items;

import com.gildedrose.Item;

public class Sulfuras extends Item {

    /**
     * Constructeur de l'item
     *
     * @param sellIn  prix
     * @param quality qualité
     */
    public Sulfuras(int sellIn, int quality) {
        super(Item.SULFURAS, sellIn, quality);
    }

    /**
     * Méthode héritée pour mettre à jour la qualité
     */
    @Override
    public void updateQuality() {
        //Cet objet doit garder les mêmes valeurs
    }
}
