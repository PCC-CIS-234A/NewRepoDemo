package logic;

import data.Database;

import java.util.ArrayList;

public class Show {
    private String m_ID;
    private String m_Title;

    public Show(String id, String title) {
        m_ID = id;
        m_Title = title;
    }

    public static ArrayList<Show> findShows() {
        return Database.findShows();
    }

    public String getID() {
        return m_ID;
    }

    public String getTitle() {
        return m_Title;
    }
}
