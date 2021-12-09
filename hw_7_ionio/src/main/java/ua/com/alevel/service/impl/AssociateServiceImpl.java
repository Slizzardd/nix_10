package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AssociateService;

import static ua.com.alevel.UtilityHelper.print;

public class AssociateServiceImpl implements AssociateService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    private final BookDao bookDao = new BookDaoImpl();
    private final AuthorDao authorDAO = new AuthorDaoImpl();

    @Override
    public void addAuthorToBook(Book book, Author author) {
        LOGGER_INFO.info("Start adding author to book.");
        if (book.getAuthors().contains(author.getId())) {
            print("Book already have this author!");
            LOGGER_WARN.warn("Book adding author warning: book already have this author");
            return;
        }
        bookDao.addAuthorToBook(book, author);
        LOGGER_INFO.info("Successful adding author to book.");
    }

    @Override
    public void addBookToAuthor(Author author, Book book) {
        LOGGER_INFO.info("Start adding book to author.");
        if (author == null) {
            print("No author with current id");
            return;
        }
        if (book == null) {
            print("No book with current id");
            return;
        }
        if (author.getBooks().contains(book.getId())) {
            LOGGER_WARN.warn("Author adding book warning: author already have this book");
            return;
        }
        authorDAO.addBookToAuthor(author, book);
        LOGGER_INFO.info("Successful adding book to author.");
    }
}
