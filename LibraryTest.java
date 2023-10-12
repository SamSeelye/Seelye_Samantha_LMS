
package lms;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Samantha Seelye, CEN3024C - 14835, 10/8/2023
 * Software Development I
 *
 * This test class is designed to test the functionality of the Library class in the Library Management System (LMS) project. 
 * It contains test cases for adding books, removing books by barcode, and removing books by title from the library.
 */

public class LibraryTest {

    @Test
    public void testAddBook() {
        // Test the addition of a book to the library
        Library library = new Library();
        Book book = new Book(1, "Sample Book", "Sample Author", 12345);

        library.addBook(book);

        // Verify that the book has been added to the library
        assertTrue(library.getBooks().contains(book));
    }

    @Test
    public void testRemoveBookByBarcode() {
        // Test the removal of a book from the library by barcode
        Library library = new Library();
        Book book = new Book(1, "Sample Book", "Sample Author", 12345);
        library.addBook(book);

        // Remove the book by barcode
        library.removeBook(12345);

        // Verify that the book has been removed from the library
        assertFalse(library.getBooks().contains(book));
    }

    @Test
    public void testRemoveBookByTitle() {
        // Test the removal of a book from the library by title
        Library library = new Library();
        Book book = new Book(1, "Sample Book", "Sample Author", 12345);
        library.addBook(book);

        // Remove the book by title
        library.removeBookByTitle("Sample Book");

        // Verify that the book has been removed from the library
        assertFalse(library.getBooks().contains(book));
    }
}
