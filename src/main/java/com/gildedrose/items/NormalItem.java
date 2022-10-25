package com.gildedrose.items;

import com.gildedrose.exceptions.InvalidItemException;

public class NormalItem {

    protected void incrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    protected void decrementQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }

    protected void updateExpired(Item item) {
        decrementQuality(item);
    }

    protected void updateSellIn(Item item) {
        item.sellIn--;
    }

    protected void updateQuality(Item item) {
        decrementQuality(item);
    }

    public void verifyItemIntegrity(Item item) throws InvalidItemException {
        if (item.quality < 0) {
            throw new InvalidItemException("Item quality can not start below 0");
        } else if (item.quality > 50) {
            throw new InvalidItemException("Item quality can not start above 50");
        }
    }

    public void updateItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
        if (item.sellIn < 0) {
            updateExpired(item);
        }
    }

}
