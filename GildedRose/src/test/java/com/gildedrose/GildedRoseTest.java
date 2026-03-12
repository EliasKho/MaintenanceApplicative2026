package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de test initiale pour vérifier le fonctionnement du programme
 */
class GildedRoseTest {

    //Raccourci des noms des items

    String brie = "Aged Brie";
    String sulfuras = "Sulfuras, Hand of Ragnaros";
    String passes = "Backstage passes to a TAFKAL80ETC concert";

    //Tests

    @Test
    void test_brie_sell0plus_qual50moins() {
        Item[] items = new Item[] { Item.create(brie, 10, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(16, app.items[0].quality);
    }

    @Test
    void test_brie_sell0plus_qual50plus() {
        Item[] items = new Item[] { Item.create(brie, 10, 60) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(60, app.items[0].quality);
    }

    @Test
    void test_aged_brie_sell0moins_qual50plus() {
        Item[] items = new Item[] { Item.create(brie, -5, 60) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-6, app.items[0].sellIn);
        assertEquals(60, app.items[0].quality);
    }

    @Test
    void test_aged_brie_sell0moins_qual50moins() {
        Item[] items = new Item[] { Item.create(brie, -5, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-6, app.items[0].sellIn);
        assertEquals(17, app.items[0].quality);
    }


    @Test
    void test_sulfuras_sellin0plus() {
        Item[] items = new Item[] { Item.create(sulfuras, 10, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, app.items[0].sellIn);
        assertEquals(15, app.items[0].quality);
    }

    @Test
    void test_sulfuras_sellin0moins() {
        Item[] items = new Item[] { Item.create(sulfuras, -10, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-10, app.items[0].sellIn);
        assertEquals(15, app.items[0].quality);
    }

    @Test
    void test_passes_sell0plus_qual50moins(){
        Item[] items = new Item[] { Item.create(passes, 10, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(17, app.items[0].quality);
    }

    @Test
    void test_passes_sell11plus_qual50plus(){
        Item[] items = new Item[] { Item.create(passes, 15, 60) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(14, app.items[0].sellIn);
        assertEquals(60, app.items[0].quality);
    }

    @Test
    void test_passes_sell11plus_qual0moins(){
        Item[] items = new Item[] { Item.create(passes, 15, -15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(14, app.items[0].sellIn);
        assertEquals(-14, app.items[0].quality);
    }

    @Test
    void test_passes_sell11moins_qual50plus(){
        Item[] items = new Item[] { Item.create(passes, 8, 60) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(7, app.items[0].sellIn);
        assertEquals(60, app.items[0].quality);
    }

    @Test
    void test_passes_sell11moins_qual0moins(){
        Item[] items = new Item[] { Item.create(passes, 10, -15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(-13, app.items[0].quality);
    }

    @Test
    void test_passes_sell6moins_qual50plus(){
        Item[] items = new Item[] { Item.create(passes, 5, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(18, app.items[0].quality);
    }

    @Test
    void test_passes_sell6moins_qual0moins(){
        Item[] items = new Item[] { Item.create(passes, 5, -15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(-12, app.items[0].quality);
    }

    @Test
    void test_passes_sell0moins(){
        Item[] items = new Item[] { Item.create(passes, -10, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-11, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_passes_sell10_qual49() {
        Item[] items = new Item[] { Item.create(passes, 10, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_autre_sell0plus_qual50plus(){
        Item[] items = new Item[] { Item.create("autre", 10, 60) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(59, app.items[0].quality);
    }

    @Test
    void test_autre_sell0plus_qual50moins(){
        Item[] items = new Item[] { Item.create("autre", 10, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(14, app.items[0].quality);
    }

    @Test
    void test_autre_sell0plus_qual0moins(){
        Item[] items = new Item[] { Item.create("autre", 10, -15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(-15, app.items[0].quality);
    }

    @Test
    void test_autre_sell0moins_qual50moins(){
        Item[] items = new Item[] { Item.create("autre", -10, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-11, app.items[0].sellIn);
        assertEquals(13, app.items[0].quality);
    }

    @Test
    void test_autre_sell0moins_qual0moins(){
        Item[] items = new Item[] { Item.create("autre", -10, -15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-11, app.items[0].sellIn);
        assertEquals(-15, app.items[0].quality);
    }

    @Test
    void test_autre_sell0moins_qual50plus(){
        Item[] items = new Item[] { Item.create("autre", -10, 60) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-11, app.items[0].sellIn);
        assertEquals(58, app.items[0].quality);
    }

    @Test
    void test_nom_item(){
        Item[] items = new Item[] { Item.create("foo", 0, 1) };
        GildedRose app = new GildedRose(items);
        assertEquals("foo", app.items[0].name);
        assertEquals("foo, 0, 1", app.items[0].toString());
    }

    @Test
    void test_passes_qualite_50_avant_palier6() {
        Item[] items = new Item[] { Item.create(passes, 5, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

}
