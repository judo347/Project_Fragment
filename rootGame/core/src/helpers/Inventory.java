package helpers;

import Items.Item;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory {

    private ArrayList<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItems(Item ... items){

        //Destroy bodies of items picked up.
        for(Item itemObj : items)
            itemObj.destroyBody();
        
        //TODO maybe check if inventory is full or something?
        this.items.addAll(Arrays.asList(items));
    }

    public ArrayList<Item> getItems() {
        return new ArrayList<>(items);
    }
}
