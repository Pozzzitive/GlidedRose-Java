package com.gildedrose;

import com.gildedrose.exceptions.InvalidItemException;
import com.gildedrose.items.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GildedRoseTest {

    @Test
    public void updateQualityDecrementsItemsQualityAndSellInBy1() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Elixir of the Mongoose", 1, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void onceTheSellByDateHasPassedQualityDegradesTwiceAsFast() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Elixir of the Mongoose", -1, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    public void qualityOfAnItemCanNeverBecomeNegative() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Elixir of the Mongoose", 2, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    public void theQualityOfAnItemCanNeverBecomeMoreThan50() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Aged Brie", -1, 50), new Item("Backstage passes to a TAFKAL80ETC concert", 3, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(50, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);

        assertEquals(50, app.items[1].quality);
        assertEquals(2, app.items[1].sellIn);
    }

    @Test
    public void briesQualityIncrementsByOneForEachDayBeforeItsSellByDate() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Aged Brie", 4, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }

    @Test
    public void briesQualityIncrementsByTwoForEachDayPastItsSellByDate() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Aged Brie", -1, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    public void SulfurasBeingALegendaryItemNeverHasToBeSoldOrDecreasesInQuality() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    public void backstagePassesQualityIncrementsByOneWithEachDayPassing() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 11, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(31, app.items[0].quality);
        assertEquals(10, app.items[0].sellIn);
    }

    @Test
    public void backstagePassesIncreaseInQualityBy2WhenThereAre10DaysOrLessRemaining() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(32, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    public void backstagePassesIncreaseInQualityBy3WhenThereAre5DaysOrLessRemaining() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 33)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(36, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }

    @Test
    public void backstagePassesQualityDropsTo0AfterTheConcert() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", -1, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    public void conjuredItemsDegradeInQualityTwiceAsFastAsNormalItems() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Conjured", 2, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(28, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    public void conjuredItemsDegradeInQualityTwiceAsFastAsNormalItemsWhenPastItsSellByDate() throws InvalidItemException {
        Item[] items = new Item[]{new Item("Conjured", -1, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(26, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    void testItemIntegrityIfItemStartsAbove50QualityAndIsNotLegendary() {
        InvalidItemException exception = null;
        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 3, 51)};
        GildedRose app = new GildedRose(items);
        try {
            app.updateQuality();
        } catch (InvalidItemException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(exception.getMessage(), "Item quality can not start above 50");
    }

    @Test
    void testItemIntegrityIfItemStartsBelow0Quality() {
        InvalidItemException exception = null;
        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 3, -1)};
        GildedRose app = new GildedRose(items);
        try {
            app.updateQuality();
        } catch (InvalidItemException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(exception.getMessage(), "Item quality can not start below 0");
    }

    @Test
    void testItemIntegrityIfLegendaryItemStartsWithQualityDifferentThan80() {
        InvalidItemException exception = null;
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 50)};
        GildedRose app = new GildedRose(items);
        try {
            app.updateQuality();
        } catch (InvalidItemException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(exception.getMessage(), "Legendary items should have 80 quality");
    }

}
