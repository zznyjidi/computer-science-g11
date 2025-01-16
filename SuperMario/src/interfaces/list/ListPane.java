package interfaces.list;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

// Class with Type
// https://docs.oracle.com/javase/tutorial/java/generics/types.html
// https://stackoverflow.com/questions/897935/when-do-java-generics-require-extends-t-instead-of-t-and-is-there-any-down
public class ListPane<EntryType extends JPanel> extends JScrollPane implements MouseListener {

    // Components
    private JPanel listPanel;
    private List<EntryType> listEntries = new ArrayList<>();
    private int selectedEntry = -1;

    public ListPane(List<EntryType> entries) {
        this();
        updateList(entries);
    }

    public ListPane() {
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
        setViewportView(listPanel);
    }

    /**
     * Recreate List with new replay List
     * @param entries List of JSONObject of Replay Files
     */
    public void updateList(List<EntryType> entries) {
        selectedEntry = -1;
        listPanel.removeAll();
        listEntries.clear();

        // Create Entries form Json
        for (EntryType entry : entries) {
            newEntry(entry);
        }
        listPanel.revalidate();
        listPanel.repaint();
    }

    /**
     * Add New LeaderBoard Entry at the End
     * @param entry Panel to Add to the List
     * @return Index of the Entry in the List
     */
    public int newEntry(EntryType entry) {
        // Set Max Size for Entries
        // https://stackoverflow.com/questions/18405660/how-to-set-component-size-inside-container-with-boxlayout
        entry.setMaximumSize(new Dimension(500, 68));
        entry.setMinimumSize(new Dimension(300, 68));
        // Clickable Panel
        // https://stackoverflow.com/questions/9967006/how-to-call-a-function-when-i-click-on-a-jpanel-java
        entry.addMouseListener(this);
        // Add Entry to Panel
        listEntries.add(entry);
        listPanel.add(entry);

        listPanel.revalidate();
        listPanel.repaint();
        return listEntries.indexOf(entry);
    }

    /**
     * Select a Entry in the Pane
     * @param index index of the Entry to Select
     */
    public void selectEntry(int index) {
        // Restore old Panel
        try {
            // Default Panel Color
            // https://stackoverflow.com/questions/9991204/get-default-background-color-of-swing-component
            listEntries.get(selectedEntry).setBackground(UIManager.getColor("Panel.background"));
        } catch (IndexOutOfBoundsException e) {
        }
        // Set new Selected Panel
        selectedEntry = index;
        listEntries.get(index).setBackground(new Color(0x4ce5fc));
    }

    public int getSelectedEntry() {
        return selectedEntry;
    }

    public EntryType getSelectedEntryObject() {
        return listEntries.get(selectedEntry);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        selectEntry(listEntries.indexOf(event.getSource()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
