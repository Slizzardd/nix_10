package ua.com.alevel.task1.controller;

import static ua.com.alevel.UtilityHelper.*;

public class MainController {

    public void run() {
        print("Choose operation: ");
        String position;
        runNavigation();
        while ((position = getString()) != null) {
            crud(position);
            position = getString();
            if (position.equals("0")) {
                System.exit(0);
            }
            crud(position);
        }
    }

    private void crud(String position) {
        switch (position) {
            case "1.1" -> AuthorController.getInstance().createAuthor();
            case "1.2" -> {
                AuthorController.getInstance().findAllAuthors();
                AuthorController.getInstance().updateAuthor();
            }
            case "1.3" -> {
                AuthorController.getInstance().findAllAuthors();
                AuthorController.getInstance().deleteAuthor();
            }
            case "1.4" -> {
                AuthorController.getInstance().findAllAuthors();
                AuthorController.getInstance().findAuthorBooks();
            }
            case "1.5" -> AuthorController.getInstance().findAllAuthors();
            case "2.1" -> BookController.getInstance().createBook();
            case "2.2" -> {
                BookController.getInstance().findAllBooks();
                BookController.getInstance().updateBook();
            }
            case "2.3" -> {
                BookController.getInstance().findAllBooks();
                BookController.getInstance().deleteBook();
            }
            case "2.4" -> {
                BookController.getInstance().findAllBooks();
                BookController.getInstance().findBookAuthors();
            }
            case "2.5" -> BookController.getInstance().findAllBooks();
            case "3.1" -> {
                AuthorController.getInstance().findAllAuthors();
                RatioController.addBook();
            }
            case "3.2" -> {
                BookController.getInstance().findAllBooks();
                RatioController.addAuthors();
            }
            case "0" -> System.exit(0);
            default -> print("Incorrect input! Try again:(");
        }
        runNavigation();
    }

    private void runNavigation() {
        print("Create author, press 1.1");
        print("Update author, press 1.2");
        print("Delete author, press 1.3");
        print("Get authors books, press 1.4");
        print("Get all authors, press 1.5");
        consoleNextLine();
        print("Create book, press 2.1");
        print("Update book, press 2.2");
        print("Delete book, press 2.3");
        print("Get books author, press 2.4");
        print("Get all books, press 2.5");
        consoleNextLine();
        print("Add books to author, press 3.1");
        print("Add authors to book, press 3.2");
        consoleNextLine();
        print("Exit, press 0");
    }

}
