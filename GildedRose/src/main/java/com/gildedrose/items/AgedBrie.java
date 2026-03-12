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

    @Override
    public void updateQuality() {
        //Le prix de l'objet baisse de 1 point dans tous les cas
        this.sellIn-=1;

        //Si la qualité est < 50, au l'augmente de 1
        if(this.quality<50){
            this.quality+=1;

            //la qualité est augmentée de 1 de plus si la valeur de sellIn est négative
            if(this.sellIn<0){
                this.quality+=1;
            }
        }
    }
}
