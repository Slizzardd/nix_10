package ua.com.alevel.controller;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static ua.com.alevel.UtilityHelper.*;

//http://www.vandermeer.de/projects/skb/java/asciitable/apidocs/de/vandermeer/asciitable/AsciiTable.html
public class BookController {

    private static BookController instance = null;
    private final Scanner in = new Scanner(System.in);
    private final BookService bookService = new BookServiceImpl();

    private BookController() {
    }

    public static BookController getInstance() {
        if (instance == null) {
            instance = new BookController();
        }
        return instance;
    }

    public void createBook() {
        Book book = new Book();
        print("Enter name: ");
        book.setName(getString());
        print("Enter amount of pages: ");
        book.setPages(getInt());
        bookService.create(book);
        print("Book id: " + book.getId());
        print("Book created!");
    }

    public void findAllBooks() {
        List<Book> books = new ArrayList<>(bookService.findAll());
        books.sort(Comparator.comparing(BaseEntity::getId));
        AsciiTable bookTable = new AsciiTable();
        bookTable.addRule();
        bookTable.addRow(null, null, null, "BOOKS").setTextAlignment(TextAlignment.CENTER);
        bookTable.addRule();
        bookTable.addRow("ID", "NAME", "PAGES", "AUTHORS_ID");
        bookTable.addRule();
        for (Book book : books) {
            bookTable.addRow(book.getId(), book.getName(), book.getPages(), book.getAuthors().toString());
            bookTable.addRule();
        }
        print(bookTable.render());
    }

    public void findBookAuthors() {
        print("Enter book id: ");
        Integer id = getInt();
        Book book = bookService.find(id);
        if (isNull(book)) {
            print("No book with current id!");
            return;
        }
        List<Author> authors = new ArrayList<>(bookService.findAuthors(book));
        authors.sort(Comparator.comparing(BaseEntity::getId));
        AsciiTable authorTable = new AsciiTable();
        authorTable.addRule();
        authorTable.addRow(null, null, null, book.getName() + " authors").setTextAlignment(TextAlignment.CENTER);
        authorTable.addRule();
        authorTable.addRow("ID", "NAME", "SURNAME", "DATE_OF_BIRDS");
        authorTable.addRule();
        for (Author author : authors) {
            authorTable.addRow(author.getId(), author.getName(), author.getSurname(), author.getDateOfBirth());
            authorTable.addRule();
        }
        print(authorTable.render());
    }

    public void updateBook() {
        print("Enter book id: ");
        Integer id = getInt();
        Book book = bookService.find(id);
        if (isNull(book)) {
            print("No book with current id!");
            return;
        }
        print(book.toString());
        print("Enter new name: ");
        book.setName(in.nextLine());
        print("Enter new amount of pages: ");
        book.setPages(getInt());
        bookService.update(book);
        print("Book updated!");
    }

    public void deleteBook() {
        print("Enter book id: ");
        Integer id = getInt();
        Book book = bookService.find(id);
        if (isNull(book)) {
            print("No book with current id!");
            return;
        }
        bookService.delete(book);
        print("Book deleted!");
    }

    private boolean isNull(Object obj) {
        return obj == null;
    }
}