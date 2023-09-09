package lms;
import java.io.*;

/**
 * Samantha Seelye, CEN3024C - 14835, 9/10/2023
 * Software Development I
 * LibraryManagementSystem.java
 * This class contains the main method for the Library Management System program.
 * It allows users to interact with the library by adding, removing, and listing books.
 */
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Reading books from a text file and adding them to the library
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\sammi\\Desktop\\Sam's School [Valencia]\\23 Spring\\COP2800C - JavaOne\\Eclipse Workspace\\LMS\\src\\lms\\books.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    Book book = new Book(id, title, author);
                    library.addBook(book);
                } else {
                    System.out.println("Invalid data format in line: " + line);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }

        // Menu for user interaction
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a new book");
            System.out.println("2. Remove a book by ID");
            System.out.println("3. List all books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int choice = Integer.parseInt(br.readLine());

                switch (choice) {
                    case 1:
                        // Add a new book
                        System.out.print("Enter the title of the book: ");
                        String newTitle = br.readLine();
                        System.out.print("Enter the author of the book: ");
                        String newAuthor = br.readLine();
                        Book newBook = new Book(0, newTitle, newAuthor); // ID will be auto-generated
                        library.addBook(newBook);
                        System.out.println("Book added successfully.");
                        break;

                    case 2:
                        // Remove a book by ID
                        System.out.print("Enter the ID of the book to remove: ");
                        int removeId = Integer.parseInt(br.readLine());
                        library.removeBook(removeId);
                        break;

                    case 3:
                        // List all books
                        library.listBooks();
                        break;

                    case 4:
                        // Exit the program
                        System.out.println("Exiting the program.");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                        break;
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

