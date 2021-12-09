package ua.com.alevel.service;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.Set;

public interface BookService extends AbstractService<Book, Integer> {
    Set<Author> findAuthors(Book book);
}
