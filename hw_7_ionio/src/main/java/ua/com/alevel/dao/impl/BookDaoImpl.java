package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.BookDao;
import ua.com.alevel.db.DbLayer;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.Set;

public class BookDaoImpl implements BookDao {

    @Override
    public void create(Book book) {
        DbLayer.getInstance().createBook(book);
    }

    @Override
    public Book find(Integer id) {
        return DbLayer.getInstance().findBookById(id);
    }

    @Override
    public Set<Book> find() {
        return DbLayer.getInstance().findAllBooks();
    }

    @Override
    public void update(Book book) {
        DbLayer.getInstance().updateBook(book);
    }

    @Override
    public void delete(Book book) {
        DbLayer.getInstance().deleteBook(book);
    }

    @Override
    public void addAuthorToBook(Book book, Author author) {
        DbLayer.getInstance().createBookRegistration(author, book);
    }
}
