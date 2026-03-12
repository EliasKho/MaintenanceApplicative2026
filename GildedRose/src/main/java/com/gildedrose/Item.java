package com.gildedrose;

import com.gildedrose.items.*;

/**
 * Classe représentant un objet (item)
 */
public abstract class Item {

    //constantes des noms
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSE = "Backstage passes to a TAFKAL80ETC concert";

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
    protected Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    //METHODES

    //Mise en place d'une fabrique pour renvoyer le bon item
    public static Item create(String name, int sellIn, int quality){
        Item item;
        switch (name){
            case AGED_BRIE:
                item = new AgedBrie(sellIn,quality);
                break;
            case SULFURAS:
                item = new Sulfuras(sellIn,quality);
                break;
            case BACKSTAGE_PASSE:
                item = new BackstagePasses(sellIn,quality);
                break;
            default:
                item = new DefaultItem(name,sellIn,quality);
                break;
        }

        return item;
    }

    /**
     * Méthode toString pour imprimer l'objet
     * @return
     */
    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
