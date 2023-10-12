package lms;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Samantha Seelye, CEN3024C - 14835, 10/8/2023
 * Software Development I
 *
 * This test class is designed to test the functionality of the Book class in the Library Management System (LMS) project. 
 * It contains test cases for checking out and checking in books.
 */

public class BookTest {

    @Test
    public void testCheckOut() {
        // Test the checkout functionality of a book
        Book book = new Book(1, "Sample Book", "Sample Author", 12345);

        // Check out the book
        book.checkOut();

        // Verify that the status is "CHECKED_OUT" and the due date is not null
        assertEquals(BookStatus.CHECKED_OUT, book.getStatus());
        assertNotNull(book.getDueDate());
    }

    @Test
    public void testCheckIn() {
        // Test the check-in functionality of a book
        Book book = new Book(1, "Sample Book", "Sample Author", 12345);
        book.checkOut();

        // Check in the book
        book.checkIn();

        // Verify that the status is "AVAILABLE" and the due date is null
        assertEquals(BookStatus.AVAILABLE, book.getStatus());
        assertNull(book.getDueDate());
    }
}
