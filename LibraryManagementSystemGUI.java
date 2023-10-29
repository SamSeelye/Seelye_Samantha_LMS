package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

/**
Samantha Seelye, CEN3024C - 14835, 10/29/2023
* Software Development I
* Constructor for the GUI of the Library Management System.
* Initializes the library and creates a graphical user interface.
*/

public class LibraryManagementSystemGUI extends JFrame {
    private Library library;
    private JTextArea outputTextArea;

    public LibraryManagementSystemGUI() {
        library = new Library();
        outputTextArea = new JTextArea(20, 50);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        // Create buttons
        JButton importButton = new JButton("Import Books");
        JButton printButton = new JButton("Print Database");
        JButton removeBarcodeButton = new JButton("Remove by Barcode");
        JButton removeTitleButton = new JButton("Remove by Title");
        JButton checkoutButton = new JButton("Check Out");
        JButton checkinButton = new JButton("Check In");
        JButton exitButton = new JButton("Exit");

        // Create a panel to organize the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));
        buttonPanel.add(importButton);
        buttonPanel.add(printButton);
        buttonPanel.add(removeBarcodeButton);
        buttonPanel.add(removeTitleButton);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(checkinButton);
        buttonPanel.add(exitButton);

        // Add components to the frame
        Container container = getContentPane();
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.EAST);

        // Register event listeners for the buttons
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importBooks();
            }
        });

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printDatabase();
            }
        });

        removeBarcodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBookByBarcode();
            }
        });

        removeTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBookByTitle();
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutBook();
            }
        });

        checkinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkinBook();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        });

        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * Imports books from a user-selected file and adds them to the library.
     */
    private void importBooks() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        int id = Integer.parseInt(parts[0].trim());
                        String title = parts[1].trim();
                        String author = parts[2].trim();
                        int barcode = Integer.parseInt(parts[3].trim());
                        Book book = new Book(id, title, author, barcode);
                        library.addBook(book);
                    }
                }
                reader.close();
                printDatabase();
            } catch (FileNotFoundException e) {
                showError("File not found: " + e.getMessage());
            } catch (IOException e) {
                showError("Error reading books file: " + e.getMessage());
            }
        }
    }

    /**
     * Prints the current database of books to the text area.
     */
    private void printDatabase() {
        List<Book> books = library.getBooks();
        outputTextArea.setText("");
        for (Book book : books) {
            outputTextArea.append("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Status: " + book.getStatus() + "\n");
        }
    }

    /**
     * Prompts the user to enter a barcode, removes the book with the specified barcode from the library, and updates the database view.
     */
    private void removeBookByBarcode() {
        String barcodeStr = JOptionPane.showInputDialog("Enter the barcode to remove:");
        if (barcodeStr != null) {
            try {
                int barcode = Integer.parseInt(barcodeStr);
                library.removeBook(barcode);
                printDatabase();
            } catch (NumberFormatException e) {
                showError("Invalid barcode input.");
            }
        }
    }

    /**
     * Prompts the user to enter a title, removes the book with the specified title from the library, and updates the database view.
     */
    private void removeBookByTitle() {
        String title = JOptionPane.showInputDialog("Enter the title to remove:");
        if (title != null) {
            library.removeBookByTitle(title);
            printDatabase();
        }
    }

    /**
     * Prompts the user to enter a title, checks out the book with the specified title, and updates the database view.
     */
    private void checkoutBook() {
        String title = JOptionPane.showInputDialog("Enter the title to check out:");
        if (title != null) {
            library.checkOutBook(title);
            printDatabase();
        }
    }

    /**
     * Prompts the user to enter a title, checks in the book with the specified title, and updates the database view.
     */
    private void checkinBook() {
        String title = JOptionPane.showInputDialog("Enter the title to check in:");
        if (title != null) {
            library.checkInBook(title);
            printDatabase();
        }
    }

    /**
     * Exits the application. Asks the user if they want to save data before exiting.
     */
    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(this, "Save data before exiting?");
        if (confirm == JOptionPane.YES_OPTION) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                library.saveDataToFile(selectedFile.getAbsolutePath());
            }
        }

        System.exit(0);
    }
    
    /**
     * Displays an error message on the console.
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Main method to start the Library Management System GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryManagementSystemGUI().setVisible(true);
            }
        });
    }
}
