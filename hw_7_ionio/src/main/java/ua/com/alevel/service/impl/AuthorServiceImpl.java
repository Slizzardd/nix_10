package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import static ua.com.alevel.UtilityHelper.print;

public class AuthorServiceImpl implements AuthorService {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final AuthorDao authorDAO = new AuthorDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public void create(Author author) {
        LOGGER_INFO.info("Start author creating.");
        authorDAO.create(author);
        LOGGER_INFO.info("Successful author creating (id = " + author.getId() + ")" );
    }

    @Override
    public Author find(Integer id) {
        LOGGER_INFO.info("Start author reading.");
        try {
            Author author = authorDAO.find(id);
            if (isAuthorDeleted(author)) {
                LOGGER_ERROR.error("Reading author error: author with this id was deleted");
                return null;
            }
            LOGGER_INFO.info("Successful author reading (id =" + author.getId() + ")");
            return author;
        } catch (NoSuchElementException e) {
            LOGGER_ERROR.error("Reading author error: no author with current id");
            return null;
        }
    }

    @Override
    public Set<Author> findAll() {
        LOGGER_INFO.info("Start all author reading.");
        LOGGER_INFO.info("Successful all author reading.");
        return authorDAO.find().stream().filter(author -> !isAuthorDeleted(author)).collect(Collectors.toSet());
    }

    @Override
    public void update(Author author) {
        LOGGER_INFO.info("Start author updating (id = " + author.getId() + ")");
        if (isAuthorDeleted(author)) {
            LOGGER_ERROR.error("Reading author error: author with this id was deleted");
            return;
        }
        authorDAO.update(author);
        LOGGER_INFO.info("Successful author updating (id = " + author.getId() + ")");
    }

    @Override
    public void delete(Author author) {
        LOGGER_INFO.info("Start author deleting(id = " + author.getId() + ")");
        if (isAuthorDeleted(author)) {
            LOGGER_ERROR.error("Reading author error: author with this id was deleted");
            return;
        }
        authorDAO.delete(author);
        LOGGER_INFO.info("Successful author deleting (id = " + author.getId() + ")");
    }


    @Override
    public Set<Book> findBooks(Author author) {
        LOGGER_INFO.info("Start reading author books.");
        if (author == null) {
            print("No author with current id");
            return null;
        }
        Set<Book> books = new HashSet<>();
        author.getBooks().forEach(bookId -> books.add(bookDao.find(bookId)));
        LOGGER_INFO.info("Successful reading author books.");
        return books;
    }

    private boolean isAuthorDeleted(Author author) {
        return !author.getAvailable();
    }
}
