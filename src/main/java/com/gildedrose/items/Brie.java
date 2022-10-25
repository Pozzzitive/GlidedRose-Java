package com.gildedrose.items;

public class Brie extends NormalItem {
    @Override
    protected void updateExpired(Item item) {
        incrementQuality(item);
    }

    @Override
    protected void updateQuality(Item item) {
        incrementQuality(item);
    }

}
