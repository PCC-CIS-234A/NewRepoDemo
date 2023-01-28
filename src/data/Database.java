package data;

import java.sql.*;
import java.util.ArrayList;

import logic.Show;

public class Database {
    // Connection string for connecting to SQL Server at CISDBSS, using the IMDB database.
    // Requires jtds.XXX.jar to be included in the project with the correct dependency set.

    private static final String CONNECTION_STRING = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/IMDB";
    private static final String USERNAME = "275student";
    private static final String PASSWORD = "275student";

    // Some SQL queries.
    private static final String FIND_SHOWS_QUERY =
            "SELECT TOP 50 tconst, primaryTitle FROM title_basics;";
    private static final String GET_TYPES = "" +
            "SELECT DISTINCT titleType\n" +
            "FROM title_basics;";

    // The one and only connection object
    private static Connection m_Connection = null;

    /**
     * Create a new connection object if there isn't one already.
     */
    private static void connect() {
        if (m_Connection != null)
            return;
        try {
            // Create a database connection with the given username and password.
            // System.out.println("Opening database connection.");
            m_Connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error! Couldn't connect to the database!");
        }
    }

    /**
     * Fetch a list of shows that match the given text in their primaryTitle.
     *
     * @param text The text to search for
     * @param maxShows  Maximum number of shows to return
     * @return The list of shows with that text in their primaryTitle.
     */
    public static ArrayList<Show> findShows() {
        ResultSet rs = null;
        ArrayList<Show> shows = new ArrayList<>();
        PreparedStatement stmt;

        try {
            // Create a connection if there isn't one already
            connect();

            // Prepare a SQL statement
            stmt = m_Connection.prepareStatement(FIND_SHOWS_QUERY);

            // Execute the query returning a result set
            rs = stmt.executeQuery();

            // For each row in the result set, create a new Show object with the specified values
            // and add it to the list of results.
            while (rs.next()) {
                shows.add(new Show(
                        rs.getString("tconst"),
                        rs.getString("primaryTitle")
                ));
            }
        } catch (Exception e) {
            System.err.println("Error: Interrupted or couldn't connect to database.");
            e.printStackTrace();
            return null;
        }
        // Return the list of results. Will be an empty list if there was an error.
        return shows;
    }


    /**
     * Fetch a list of all the types.
     *
     * @return The list of show types.
     */
    public static ArrayList<String> getTypes() {
        ResultSet rs = null;
        ArrayList<String> types = new ArrayList<>();
        PreparedStatement stmt;

        try {
            // Create a connection if there isn't one already
            connect();

            // Prepare a SQL statement
            stmt = m_Connection.prepareStatement(GET_TYPES);

            // Execute the query returning a result set
            rs = stmt.executeQuery();

            // For each row in the result set, create a new Show object with the specified values
            // and add it to the list of results.
            while (rs.next()) {
                types.add(rs.getString("titleType"));
            }
        } catch (Exception e) {
            System.err.println("Error: Interrupted or couldn't connect to database.");
            e.printStackTrace();
            return null;
        }
        // Return the list of results. Will be an empty list if there was an error.
        return types;
    }
}
