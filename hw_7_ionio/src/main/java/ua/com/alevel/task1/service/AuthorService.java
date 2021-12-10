package ua.com.alevel.task1.service;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.Set;

public interface AuthorService extends AbstractService<Author, Integer> {
    Set<Book> findBooks(Author author);
}