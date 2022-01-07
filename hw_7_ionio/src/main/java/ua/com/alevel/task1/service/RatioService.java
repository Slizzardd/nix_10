package ua.com.alevel.task1.service;

import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.persistence.entity.Book;

public interface RatioService {
    void addAuthorToBook(Book book, Author author);

    void addBookToAuthor(Author author, Book book);
}
