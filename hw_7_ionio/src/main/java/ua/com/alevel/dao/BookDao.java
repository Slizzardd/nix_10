package ua.com.alevel.dao;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public interface BookDao extends AbstractDao<Book, Integer> {
    void addAuthorToBook(Book book, Author author);
}
