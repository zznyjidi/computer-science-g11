import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryBookSorter extends JFrame implements ActionListener{

    // GUI Components
    private JLabel titleLabel;
    private JTextField bookInputField;
    private JButton addButton;
    private JButton sortButton;
    private JTextArea bookListArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem aboutMenuItem;
    private JLabel imageLabel;

    // Book Storage
    private List<String> bookList;

    public LibraryBookSorter() {
        // Init Storage
        bookList = new ArrayList<String>();

        // GUI
        // Title
        titleLabel = new JLabel("Library Book Sorter");

        // Input Field
        bookInputField = new JTextField(20); // Hold 20 chars

        // Buttons
        addButton = new JButton("Add Book");
        sortButton = new JButton("Sort");

        // Book List
        bookListArea = new JTextArea(10, 20); // 10 rows, 20 cols
        bookListArea.setEditable(false);

        // Menu Bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        // Menu Items
        exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);
        aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);

        // Image
        imageLabel = new JLabel();
        ImageIcon saitama = new ImageIcon("assets/onepunchman.png");
        imageLabel.setIcon(saitama);

        // Layout
        setLayout(new BorderLayout());

        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        add(imagePanel, BorderLayout.WEST);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Book Title: "));
        inputPanel.add(bookInputField);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sortButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(inputPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(bookListArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Actions
        addButton.addActionListener(this);
        sortButton.addActionListener(this);

        exitMenuItem.addActionListener(this);
        aboutMenuItem.addActionListener(this);

        // JFrame Properties
        setTitle("Library Book Sorter");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack(); // Auto Size
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String bookTitle = bookInputField.getText().trim(); // Remove Extra Spaces

            if (!bookTitle.isEmpty()) {
                bookList.add(bookTitle);
                bookInputField.setText(""); // Clear Input Field
                updateBookList();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Book name can't be Empty! ", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            } 
        }
        else if (e.getSource() == sortButton) {
            sortBooks();
            updateBookList();
        }
        else if (e.getSource() == exitMenuItem) {
            System.exit(0);
        }
        else if (e.getSource() == aboutMenuItem) {
            JOptionPane.showMessageDialog(this,
                "Library Book Sorter v0.1\nCreated By ZZNYJIDI", 
                "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void sortBooks() {
        bookList.sort(null);
    }

    private void updateBookList() {
        bookListArea.setText("");
        for (String book : bookList) {
            bookListArea.append(book + "\n");
        }
    }

    // Debug
    public static void main(String[] args) {
        new LibraryBookSorter();
    }
}
