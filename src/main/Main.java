package main;

import logic.Show;
import presentation.browser.ShowBrowser;

import javax.swing.*;
import java.util.ArrayList;

// This is in my AccountCreation branch, but not in main.

/**
 * Main entry point for the program.
 * <p>
 * Kick off the application by calling the start method of the Controller class.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
                /*
                ArrayList<Show> shows = Show.findShows();
                for (Show show: shows) {
                    System.out.println(show.getID() + ": " + show.getTitle());
                }
                 */
            }
        });
    }

    public static void createGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        // Create a JFrame to show our form in, and display the UsersTableGUI form.
        JFrame frame = new JFrame();
        // Makes the application close when the window goes away.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // showForm(new SearchForm(), frame);
        // showForm(new InfoGraphForm("tt5370118", "KonoSuba - God's Blessing on This Wonderful World!"), frame);
        // showForm(new InfoGraphForm("tt0112182", "Strange Luck"), frame);
        ShowBrowser app = new ShowBrowser();

        JPanel root = app.getRootPanel();

        frame.getContentPane().removeAll();
        frame.getContentPane().add(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

