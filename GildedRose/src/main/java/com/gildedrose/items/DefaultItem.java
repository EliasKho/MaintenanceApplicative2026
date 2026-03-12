package com.gildedrose.items;

import com.gildedrose.Item;

public class DefaultItem extends Item {

    /**
     * Constructeur de l'item
     *
     * @param sellIn  prix
     * @param quality qualité
     */
    public DefaultItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

}
