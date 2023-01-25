package presentation.browser;

import logic.Show;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ShowBrowser {
    private JPanel rootPanel;
    private JScrollPane scrollPane;
    private JTable showTable;

    private DefaultTableModel m_ShowTableModel;

    public ShowBrowser() {
        // setupTable();
        fetchData();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void setupTable() {
        // Create a default table model with three columns named Email, Password and Role, and no table data.
        m_ShowTableModel = new DefaultTableModel(
                // Initial data (empty)
                new Object[][]{},
                // Initial columns
                new Object[] { "Show ID", "Series Title" }
        ) {
            // Do not let the user edit values in the table.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Make the userTable use that model
        showTable.setModel(m_ShowTableModel);

        // Center values in the Start Year, End Year, and Runtime columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        showTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        showTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );

        // Center column headers
        ((DefaultTableCellRenderer)showTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Adjust column widths
        showTable.getColumnModel().getColumn(0).setMinWidth(80);
        showTable.getColumnModel().getColumn(1).setMinWidth(400);
    }

    private void fetchData() {
        setupTable();
        ArrayList<Show> shows = Show.findShows();
        for (Show show: shows) {
            m_ShowTableModel.addRow(new Object[]{
                    show.getID(),
                    show.getTitle()
            });
        }
    }
}
