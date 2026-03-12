package com.gildedrose.items;

import com.gildedrose.Item;

/**
 * Classe représentant un Item par défaut dans le cas où le nom de l'item n'est pas reconnu
 */
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

    @Override
    public void updateQuality() {

        //on diminie la valeur de sellIn de 1 dans tous les cas
        this.sellIn -= 1;

        //si la qualité est strictement positive
        if(this.quality > 0){

            //on diminue la qualité de 1
            this.quality -= 1;

            //si le prix est inférieur à 0, la qualité baisse de 2 et non 1
            if(this.sellIn < 0){
                this.quality -= 1;
            }
        }

    }

}
