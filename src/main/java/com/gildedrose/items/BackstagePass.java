package com.gildedrose.items;

public class BackstagePass extends NormalItem {

    @Override
    protected void updateQuality(Item item) {
        incrementQuality(item);

        if (item.sellIn <= 10) {
            incrementQuality(item);
        }

        if (item.sellIn <= 5) {
            incrementQuality(item);
        }
    }

    @Override
    protected void updateExpired(Item item) {
        item.quality = 0;
    }

}
