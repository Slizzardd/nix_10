package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.db.DbLayer;
import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.persistence.entity.Book;

import java.util.Set;

public class AuthorDaoImpl implements AuthorDao {

    @Override
    public void create(Author author) {
        DbLayer.getInstance().createAuthor(author);
    }

    @Override
    public Author find(Integer id) {
        return DbLayer.getInstance().findAuthorById(id);
    }

    @Override
    public Set<Author> find() {
        return DbLayer.getInstance().findAllAuthors();
    }

    @Override
    public void update(Author author) {
        DbLayer.getInstance().updateAuthor(author);
    }

    @Override
    public void delete(Author author) {
        DbLayer.getInstance().deleteAuthor(author);
    }

    @Override
    public void addBookToAuthor(Author author, Book book) {
        DbLayer.getInstance().createBookRegistration(author, book);
    }
}
