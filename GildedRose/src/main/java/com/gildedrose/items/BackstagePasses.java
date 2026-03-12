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

    @Override
    public void updateQuality() {
        //le prix diminue de 1 dans tous les cas
        this.sellIn -= 1;

        //la qualité tombe à 0 si le prix est négatif/nul
        if(this.sellIn <= 0) {
            this.quality = 0;
        } else {
            //si c'est positif, on sépare les fourchettes de prix 1-5/6-10/11+
            if(this.sellIn >= 11) {
                gestion11plus();
            } else {
                if(this.sellIn >= 6) {
                    gestion6_10();
                } else {
                    gestion5moins();
                }
            }

        }

    }

    //METHODES PRIVEES POUR GERER LES FOURCHETTES

    /**
     * Méthode privée pour gérer la fourchette de sellIn >=11
     */
    private void gestion11plus(){
        if(this.quality < 0){
            this.quality += 1;
        }
    }

    /**
     * Méthode privée pour gérer la fourchette de sellIn 6-10
     */
    private void gestion6_10(){
        //on répète 2 fois l'opération afin de bloquer à 50 la qualité
        for(int i=1;i<=2;i++){
            if(this.quality < 50) {
                this.quality += 1;
            }
        }
    }

    /**
     * Méthode privée pour gérer la fourchette de sellIn 1-5
     */
    private void gestion5moins(){
        //on répète 3 fois l'opération afin de bloquer à 50 la qualité
        for(int i=1;i<=3;i++){
            if(this.quality < 50) {
                this.quality += 1;
            }
        }
    }


}
