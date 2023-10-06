package lms;

import java.util.Date;
import java.util.Calendar;

/**
 Samantha Seelye, CEN3024C - 14835, 10/8/2023
 * Software Development I
 *
 * This class represents a book in the library management system.
 * It stores information about the book such as its ID, title, author, barcode, status, and due date.
 */
public class Book {
    private int id;        // The unique identifier for the book
    private String title;  // The title of the book
    private String author; // The author of the book
    private int barcode;   // The barcode associated with the book
    private BookStatus status; // The status of the book (available or checked out)
    private Date dueDate;      // The due date for the book when it's checked out

    /**
     * Constructor for creating a new Book object.
     The unique identifier for the book.
     The title of the book.
     The author of the book.
     The barcode associated with the book.
     */
    public Book(int id, String title, String author, int barcode) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.barcode = barcode;
        this.status = BookStatus.AVAILABLE;
        this.dueDate = null;
    }

    /**
     * Get the unique identifier of the book.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the unique identifier of the book.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get the barcode associated with the book.
     */
    public int getBarcode() {
        return barcode;
    }

    /**
     * Get the status of the book (available or checked out).
     */
    public BookStatus getStatus() {
        return status;
    }

    /**
     * Set the status of the book (available or checked out).
        */
    public void setStatus(BookStatus status) {
        this.status = status;
    }

    /**
     * Get the due date for the book when it's checked out.
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Set the due date for the book when it's checked out.
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Check out the book, setting its status to "checked out" and calculating the due date.
     */
    public void checkOut() {
        if (status == BookStatus.AVAILABLE) {
            setStatus(BookStatus.CHECKED_OUT);
            setDueDate(calculateDueDate());
        }
    }

    /**
     * Check in the book, setting its status to "available" and clearing the due date.
     */
    public void checkIn() {
        if (status == BookStatus.CHECKED_OUT) {
            setStatus(BookStatus.AVAILABLE);
            setDueDate(null);
        }
    }

    /**
     * Calculate the due date for the book when it's checked out (4 weeks from the current date).
     */
    public Date calculateDueDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.WEEK_OF_YEAR, 4);
        return calendar.getTime();
    }
}
