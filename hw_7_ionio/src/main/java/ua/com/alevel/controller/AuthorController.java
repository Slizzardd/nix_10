package ua.com.alevel.controller;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.impl.AuthorServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ua.com.alevel.UtilityHelper.*;

//http://www.vandermeer.de/projects/skb/java/asciitable/apidocs/de/vandermeer/asciitable/AsciiTable.html
public class AuthorController {

    private static AuthorController instance = null;
    private final AuthorService authorService = new AuthorServiceImpl();

    private AuthorController() {
    }

    public static AuthorController getInstance() {
        if (instance == null) {
            instance = new AuthorController();
        }
        return instance;
    }

    public void createAuthor() {
        Author author = new Author();

        print("Enter name: ");
        author.setName(getString());
        print("Enter surname: ");
        author.setSurname(getString());
        print("Enter date of Birds(Not use ','): ");
        author.setDateOfBirth(getString());
        authorService.create(author);

        print("Author id: " + author.getId().toString());
        print("Author created!");
    }

    public void findAllAuthors() {
        List<Author> authors = new ArrayList<>(authorService.findAll());
        authors.sort(Comparator.comparing(BaseEntity::getId));
        AsciiTable authorTable = new AsciiTable();
        authorTable.addRule();
        authorTable.addRow(null, null, null, null, "AUTHORS").setTextAlignment(TextAlignment.CENTER);
        authorTable.addRule();
        authorTable.addRow("ID", "NAME", "SURNAME", "DATE_OF_BIRDS", "BOOKS_ID");
        authorTable.addRule();
        for (Author author : authors) {
            authorTable.addRow(author.getId(), author.getName(), author.getSurname(), author.getDateOfBirth(), author.getBooks().toString());
            authorTable.addRule();
        }
        print(authorTable.render());
    }

    public void findAuthorBooks() {
        print("Enter author id: ");
        Integer id = getInt();
        Author author = authorService.find(id);
        if (isNull(author)) {
            print("No author with current id!");
            return;
        }
        List<Book> books = new ArrayList<>(authorService.findBooks(author));
        books.sort(Comparator.comparing(BaseEntity::getId));
        AsciiTable bookTable = new AsciiTable();
        bookTable.addRule();
        bookTable.addRow(null, null, author.getName() + " " + author.getSurname() + " books").setTextAlignment(TextAlignment.CENTER);
        bookTable.addRule();
        bookTable.addRow("ID", "NAME", "PAGES");
        bookTable.addRule();
        for (Book book : books) {
            bookTable.addRow(book.getId(), book.getName(), book.getPages());
            bookTable.addRule();
        }
        print(bookTable.render());
    }

    public void updateAuthor() {
        print("Enter author id: ");
        Integer id = getInt();
        Author author = authorService.find(id);
        if (isNull(author)) {
            print("No author with current id!");
            return;
        }
        print(author.toString());
        print("Enter new name: ");
        author.setName(getString());
        print("Enter new surname: ");
        author.setSurname(getString());
        print("Enter new date of Birds(Not use ','): ");
        author.setDateOfBirth(getString());
        authorService.update(author);
        print("Author updated!");
    }

    public void deleteAuthor() {
        print("Enter author id: ");
        Integer id = getInt();
        Author author = authorService.find(id);
        if (isNull(author)) {
            print("No author with current id!");
            return;
        }
        authorService.delete(author);
        print("Author deleted!");
    }

    private boolean isNull(Object obj) {
        return obj == null;
    }
}