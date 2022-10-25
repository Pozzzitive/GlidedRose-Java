package com.gildedrose;

import com.gildedrose.exceptions.InvalidItemException;
import com.gildedrose.items.Item;

public class Main {
    public static void main(String[] args) {

        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 40),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 40),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 40),
            new Item("Conjured Mana Cake", 3, 6)};

        GildedRose app = new GildedRose(items);

        int days = 4;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println();
            try {
                app.updateQuality();
            } catch (InvalidItemException e) {
                e.printStackTrace();
            }
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

}
