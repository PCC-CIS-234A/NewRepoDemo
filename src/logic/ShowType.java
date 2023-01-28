package logic;

import data.Database;

import java.util.ArrayList;

public class ShowType {
    public static final ArrayList<String> getTypes() {
        return Database.getTypes();
    }
}
