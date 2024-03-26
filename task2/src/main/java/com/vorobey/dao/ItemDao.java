package com.vorobey.dao;

public class ItemDao {
    private static final ItemDao INSTANCE = new ItemDao();
    public static ItemDao getInstance() {
        return INSTANCE;
    }
    private ItemDao() {}

}
