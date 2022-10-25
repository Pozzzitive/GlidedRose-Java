package com.gildedrose;

import com.gildedrose.exceptions.InvalidItemException;
import com.gildedrose.items.*;


class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() throws InvalidItemException {
        for (Item item : items) {
            NormalItem itemPouch = categorize(item);
            itemPouch.verifyItemIntegrity(item);
            itemPouch.updateItem(item);
        }
    }

    private NormalItem categorize(Item item) {
        if ("Sulfuras, Hand of Ragnaros".equals(item.name)) {
            return new LegendaryItem();
        }
        if ("Aged Brie".equals(item.name)) {
            return new Brie();
        }
        if ("Backstage passes to a TAFKAL80ETC concert".equals(item.name)) {
            return new BackstagePass();
        }
        if (item.name.startsWith("Conjured"))
            return new ConjuredItem();
        return new NormalItem();
    }
}
