package ua.com.alevel.dao;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public interface AuthorDao extends AbstractDao<Author, Integer> {
    void addBookToAuthor(Author author, Book book);
}
