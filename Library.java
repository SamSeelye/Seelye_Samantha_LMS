package lms;
import java.util.ArrayList;
import java.util.List;

/**
 * Samantha Seelye, CEN3024C - 14835, 9/10/2023
 * Software Development I
 * Library.java
 * This class represents a Library that manages a collection of books.
 * It provides methods to add, remove, and list books.
 */
public class Library {
    private List<Book> books;
    private int lastAssignedId = 0;

    public Library() {
        books = new ArrayList<>();
    }

    /**
     * Adds a new book to the library.
     *
     * @param book The book to be added.
     */
    public void addBook(Book book) {
        book.setId(generateUniqueID());
        books.add(book);
    }

    /**
     * Removes a book from the library by its ID.
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
     * Lists all the books in the library.
     */
    public void listBooks() {
        for (Book book : books) {
            System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
        }
    }

    private int generateUniqueID() {
        return ++lastAssignedId;
    }
}
