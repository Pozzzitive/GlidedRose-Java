package com.gildedrose.items;

public class ConjuredItem extends NormalItem {
    @Override
    protected void updateQuality(Item item) {
        decrementQuality(item);
        decrementQuality(item);
    }

    @Override
    protected void updateExpired(Item item) {
        decrementQuality(item);
        decrementQuality(item);
    }

}
