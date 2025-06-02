package com.company.oop.engine;

import java.util.ArrayList;

public class Board {
    private final ArrayList<BoardItem> items;

    public Board () {
        this.items = new ArrayList<>();
    }

    public int totalItems() {
        return items.size();
    }

    public void addItem (BoardItem item) {
        if (items.contains(item)) {
            throw new IllegalArgumentException("Item already in the list");
        }
        items.add(item);
    }

}