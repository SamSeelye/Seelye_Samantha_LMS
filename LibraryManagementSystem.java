package lms;

import java.io.*;
import java.util.*;

/**
 Samantha Seelye, CEN3024C - 14835, 10/8/2023
 * Software Development I
 *
 * This class represents the main library management system application.
 * It allows users to interact with the library database by adding, removing, checking out,
 * and checking in books, as well as listing books and saving data to a file.
 */
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library(); 
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the file name to import books
        System.out.print("Enter the file name to import books: ");
        String fileName = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            // Read books from the file and add them to the library
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    int barcode = Integer.parseInt(parts[3].trim());
                    Book book = new Book(id, title, author, barcode);
                    library.addBook(book);
                } else {
                    System.out.println("Invalid data format in line: " + line);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            scanner.close(); 
            return;
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
            scanner.close(); 
            return;
        }

        while (true) {
            // Display the menu
            System.out.println("\nMenu:");
            System.out.println("1. Add a new book");
            System.out.println("2. Remove a book by ID");
            System.out.println("3. Remove a book by title");
            System.out.println("4. Remove a book by barcode");
            System.out.println("5. Check out a book by title");
            System.out.println("6. Check in a book by title");
            System.out.println("7. List all books");
            System.out.println("8. Save data to file");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        // Add a new book to the library
                        System.out.print("Enter the title of the book: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter the author of the book: ");
                        String newAuthor = scanner.nextLine();
                        System.out.print("Enter the barcode of the book: ");
                        int newBarcode = scanner.nextInt();
                        scanner.nextLine();
                        Book newBook = new Book(0, newTitle, newAuthor, newBarcode); // ID will be auto-generated
                        library.addBook(newBook);
                        System.out.println("Book added successfully.");
                        break;
                    case 2:
                        // Remove a book from the library by ID
                        System.out.print("Enter the ID of the book to remove: ");
                        int removeId = scanner.nextInt();
                        scanner.nextLine();
                        library.removeBook(removeId);
                        break;
                    case 3:
                        // Remove a book from the library by title
                        System.out.print("Enter the title of the book to remove: ");
                        String removeTitle = scanner.nextLine();
                        library.removeBookByTitle(removeTitle);
                        break;
                    case 4:
                        // Remove a book from the library by barcode
                        System.out.print("Enter the barcode of the book to remove: ");
                        int removeBarcode = scanner.nextInt();
                        scanner.nextLine();
                        library.removeBookByBarcode(removeBarcode);
                        break;
                    case 5:
                        // Check out a book by title
                        System.out.print("Enter the title of the book to check out: ");
                        String checkoutTitle = scanner.nextLine();
                        library.checkOutBook(checkoutTitle);
                        break;
                    case 6:
                        // Check in a book by title
                        System.out.print("Enter the title of the book to check in: ");
                        String checkinTitle = scanner.nextLine();
                        library.checkInBook(checkinTitle);
                        break;
                    case 7:
                        // List all books in the library
                        library.listBooks();
                        break;
                    case 8:
                        // Save data to a file
                        System.out.print("Enter the file path to save data: ");
                        String filePath = scanner.nextLine();
                        library.saveDataToFile(filePath);
                        break;
                    case 9:
                        // Exit the program
                        System.out.println("Exiting the program.");
                        // Save the updated data to the text file before exiting
                        library.saveDataToFile(fileName);
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
}
