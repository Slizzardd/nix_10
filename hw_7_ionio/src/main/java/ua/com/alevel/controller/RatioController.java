package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.impl.RatioServiceImpl;
import ua.com.alevel.service.impl.AuthorServiceImpl;
import ua.com.alevel.service.impl.BookServiceImpl;

import java.util.Scanner;

import static ua.com.alevel.UtilityHelper.*;

public class RatioController {

    private final Scanner in = new Scanner(System.in);
    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final BookService bookService = new BookServiceImpl();
    private static final RatioServiceImpl ratioService = new RatioServiceImpl();

    public static void addBook() {
        print("Enter author id: ");
        Integer id = getInt();
        BookController.getInstance().findAllBooks();
        print("Enter books id`s(divide id with ','): ");
        String bookId = getString();
        Author author = authorService.find(id);
        if (author == null) {
            print("No author with current id!");
            return;
        }
        bookId = bookId.replaceAll("\\s", "");
        String[] ids = bookId.split(",");
        Book book;
        for (String s : ids) {
            book = bookService.find(Integer.parseInt(s));
            if (book == null) {
                print("No book with current id!");
                return;
            }
            ratioService.addBookToAuthor(author, book);
        }
        print("Books was added to author");
    }

    public static void addAuthors() {
        print("Enter book id: ");
        Integer id = getInt();
        AuthorController.getInstance().findAllAuthors();
        print("Enter authors id`s(divide id with ','): ");
        String authorsIds = getString();
        Book book = bookService.find(id);
        if (book == null) {
            print("No book with current id");
            return;
        }
        authorsIds = authorsIds.replaceAll("\\s", "");
        String[] ids = authorsIds.split(",");
        Author authors;
        for (String s : ids) {
            authors = authorService.find(Integer.parseInt(s));
            if (authors == null) {
                print("No author with current id!");
                return;
            }
            ratioService.addAuthorToBook(book, authors);
        }
        print("Authors was added to book");
    }
}
