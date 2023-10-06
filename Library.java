package lms;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 Samantha Seelye, CEN3024C - 14835, 10/8/2023
 * Software Development I
 *
 * This class represents a library that stores and manages a collection of books.
 * It provides methods for adding, removing, listing, and saving books, as well as checking books in and out.
 */
public class Library {
    private List<Book> books;     // List to store books in the library
    private int lastAssignedId = 0; // Last assigned ID for a book

    /**
     * Constructor for creating a new Library object.
     * Initializes the list of books as an empty ArrayList.
     */
    public Library() {
        books = new ArrayList<>();
    }

    /**
     * Get the list of books in the library.
     *
     * @return The list of books.
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Add a new book to the library.
     *
     * @param book The book to be added.
     */
    public void addBook(Book book) {
        book.setId(generateUniqueID());
        books.add(book);
    }

    /**
     * Remove a book from the library by its ID.
     *
     * @param bookId The ID of the book to be removed.
     */
    public void removeBook(int bookId) {
        Book foundBook = null;
        for (Book book : books) {
            if (book.getId() == bookId) {
                foundBook = book;
                break;
            }
        }
        if (foundBook != null) {
            books.remove(foundBook);
            System.out.println("Book removed: " + foundBook.getTitle());
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }
    }

    /**
     * Remove a book from the library by its title.
     *
     * @param title The title of the book to be removed.
     */
    public void removeBookByTitle(String title) {
        Book foundBook = null;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBook = book;
                break;
            }
        }
        if (foundBook != null) {
            books.remove(foundBook);
            System.out.println("Book removed: " + foundBook.getTitle());
        } else {
            System.out.println("Book not found with title: " + title);
        }
    }

    /**
     * Remove a book from the library by its barcode.
     *
     * @param barcode The barcode of the book to be removed.
     */
    public void removeBookByBarcode(int barcode) {
        Book foundBook = null;
        for (Book book : books) {
            if (book.getId() == barcode) {
                foundBook = book;
                break;
            }
        }
        if (foundBook != null) {
            books.remove(foundBook);
            System.out.println("Book removed: " + foundBook.getTitle());
        } else {
            System.out.println("Book not found with barcode: " + barcode);
        }
    }

    /**
     * List all books in the library.
     */
    public void listBooks() {
        System.out.println("Printing the database:");
        for (Book book : books) {
            System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
        }
    }

    /**
     * Generate a unique ID for a new book.
     *
     * @return The generated unique ID.
     */
    private int generateUniqueID() {
        return ++lastAssignedId;
    }

    /**
     * Check out a book from the library by its title.
     *
     * @param title The title of the book to be checked out.
     */
    public void checkOutBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.getStatus() == BookStatus.AVAILABLE) {
                    book.setStatus(BookStatus.CHECKED_OUT);
                    book.setDueDate(book.calculateDueDate());
                    System.out.println("Book checked out: " + book.getTitle());
                } else {
                    System.out.println("Book is already checked out: " + book.getTitle());
                }
                return;
            }
        }
        System.out.println("Book not found with title: " + title);
    }

    /**
     * Check in a book to the library by its title.
     *
     * @param title The title of the book to be checked in.
     */
    public void checkInBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.getStatus() == BookStatus.CHECKED_OUT) {
                    book.setStatus(BookStatus.AVAILABLE);
                    book.setDueDate(null);
                    System.out.println("Book checked in: " + book.getTitle());
                } else {
                    System.out.println("Book is not checked out: " + book.getTitle());
                }
                return;
            }
        }
        System.out.println("Book not found with title: " + title);
    }

    /**
     * Save the data of books to a file.
     *
     * @param filePath The file path to save the data.
     */
    public void saveDataToFile(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.println(book.getId() + "," + book.getTitle() + "," + book.getAuthor());
            }
            System.out.println("Data saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }
}
