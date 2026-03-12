package com.gildedrose.items;

import com.gildedrose.Item;

public class AgedBrie extends Item {

    /**
     * Constructeur de l'item
     *
     * @param sellIn  prix
     * @param quality qualité
     */
    public AgedBrie(int sellIn, int quality) {
        super(Item.AGED_BRIE, sellIn, quality);
    }

}
