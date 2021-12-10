package ua.com.alevel.task1.service;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public interface RatioService {
    void addAuthorToBook(Book book, Author author);

    void addBookToAuthor(Author author, Book book);
}
