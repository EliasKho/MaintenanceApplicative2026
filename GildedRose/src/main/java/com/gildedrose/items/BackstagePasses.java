package com.gildedrose.items;

import com.gildedrose.Item;

public class BackstagePasses extends Item {

    /**
     * Constructeur de l'item
     *
     * @param sellIn  prix
     * @param quality qualité
     */
    public BackstagePasses(int sellIn, int quality) {
        super(Item.BACKSTAGE_PASSE, sellIn, quality);
    }

}
