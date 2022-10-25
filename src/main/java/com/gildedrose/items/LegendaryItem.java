package com.gildedrose.items;

import com.gildedrose.exceptions.InvalidItemException;

public class LegendaryItem extends NormalItem {
    @Override
    protected void updateExpired(Item item) {
    }

    @Override
    protected void updateSellIn(Item item) {
    }

    @Override
    protected void updateQuality(Item item) {
    }

    @Override
    public void verifyItemIntegrity(Item item) throws InvalidItemException {
        if (item.quality != 80) {
            throw new InvalidItemException("Legendary items should have 80 quality");
        }
    }

}
