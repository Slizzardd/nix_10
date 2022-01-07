package ua.com.alevel.dao;

import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.persistence.entity.Book;

public interface AuthorDao extends AbstractDao<Author, Integer> {
    void addBookToAuthor(Author author, Book book);
}
