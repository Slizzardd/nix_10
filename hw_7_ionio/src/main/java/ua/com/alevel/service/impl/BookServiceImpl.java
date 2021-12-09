package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final BookDao bookDao = new BookDaoImpl();
    private final AuthorDao authorDao = new AuthorDaoImpl();

    @Override
    public void create(Book book) {
        LOGGER_INFO.info("Start book creating.");
        bookDao.create(book);
        LOGGER_INFO.info("Successful book creating(id = " + book.getId() + ")");
    }

    @Override
    public Book find(Integer id) {
        LOGGER_INFO.info("Start book reading.");
        Book book;
        try {
            book = bookDao.find(id);
            if (isBookDeleted(book)) {
                LOGGER_ERROR.error("Reading book error: book with this id was deleted");
                return null;
            }
        } catch (NoSuchElementException e) {
            LOGGER_ERROR.error("Reading book error: no book with current id");
            return null;
        }
        LOGGER_INFO.info("Successful book reading (id = " + book.getId() + ")");
        return book;
    }

    @Override
    public Set<Book> findAll() {
        LOGGER_INFO.info("Start all books reading.");
        LOGGER_INFO.info("Successful all books reading.");
        return bookDao.find().stream().filter(book -> !isBookDeleted(book)).collect(Collectors.toSet());
    }

    @Override
    public void update(Book book) {
        LOGGER_INFO.info("Start book updating (id = " + book.getId() + ")");
        if (isBookDeleted(book)) {
            LOGGER_ERROR.error("Reading book error: book with this id was deleted");
            return;
        }
        bookDao.update(book);
        LOGGER_INFO.info("Successful book updating (id = " + book.getId() + ")");
    }

    @Override
    public void delete(Book book) {
        LOGGER_INFO.info("Start book deleting (id = " + book.getId() + ")");
        if (isBookDeleted(book)) {
            LOGGER_ERROR.error("Reading book error: book with this id was deleted");
            throw new NoSuchElementException("No book with current id");
        }
        bookDao.delete(book);
        LOGGER_INFO.info("Successful book deleting(id = " + book.getId() + ")");
    }

    @Override
    public Set<Author> findAuthors(Book book) {
        Set<Author> authors = new HashSet<>();
        book.getAuthors().forEach(authorId -> authors.add(authorDao.find(authorId)));
        return authors;
    }

    private boolean isBookDeleted(Book book) {
        return !book.getAvailable();
    }
}
