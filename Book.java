package lms;
/**
 * Samantha Seelye, CEN3024C - 14835, 9/10/2023
 * Software Development I
 * Book.java
 * This class represents a Book with an ID, title, and author.
 * It provides methods to access the book's attributes.
 */
public class Book {
    private int id;
    private String title;
    private String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
