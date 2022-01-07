package ua.com.alevel.task1.service;

import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.persistence.entity.Book;

import java.util.Set;

public interface BookService extends AbstractService<Book, Integer> {
    Set<Author> findAuthors(Book book);
}
