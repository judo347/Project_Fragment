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
        this.items.addAll(Arrays.asList(items));
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
