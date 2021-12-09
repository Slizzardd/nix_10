package ua.com.alevel.db;

import ua.com.alevel.ReadFromCsv;
import ua.com.alevel.WriteToCsv;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.*;
import java.util.stream.Collectors;

public class DbLayer {

    private static DbLayer instance = null;

    private final String AUTHOR_PATH = "src/main/resources/csvFile/author.csv";
    private final String BOOK_PATH = "src/main/resources/csvFile/book.csv";
    private final String BOOK_REGISTRATION_PATH = "src/main/resources/csvFile/associations.csv";

    private final String[] AUTHOR_TABLE_HEADER = {"id", "name", "surname", "year_Of_Birth", "available"};
    private final String[] BOOK_TABLE_HEADER = {"id", "name", "pages", "available"};
    private final String[] BOOK_REGISTRATION_TABLE_HEADER = {"id", "author_id", "book_id"};

    private final List<String[]> AUTHOR_DATA_LIST = new ArrayList<>();
    private final List<String[]> BOOK_DATA_LIST = new ArrayList<>();
    private final List<String[]> BOOK_REGISTRATION_DATA_LIST = new ArrayList<>();

    private final Integer AUTHOR_TABLE_COLUMNS_AMOUNT = AUTHOR_TABLE_HEADER.length;
    private final Integer BOOK_TABLE_COLUMNS_AMOUNT = BOOK_TABLE_HEADER.length;
    private final Integer BOOK_REGISTRATION_TABLE_COLUMNS_AMOUNT = BOOK_REGISTRATION_TABLE_HEADER.length;

    private WriteToCsv authorWriter;
    private WriteToCsv bookWriter;
    private WriteToCsv bookRegistrationWriter;

    private ReadFromCsv authorReader;
    private ReadFromCsv bookReader;
    private ReadFromCsv bookRegistrationReader;

    private DbLayer() {
        authorWriter = new WriteToCsv(AUTHOR_PATH);
        authorReader = new ReadFromCsv(AUTHOR_PATH);

        bookWriter = new WriteToCsv(BOOK_PATH);
        bookReader = new ReadFromCsv(BOOK_PATH);

        bookRegistrationWriter = new WriteToCsv(BOOK_REGISTRATION_PATH);
        bookRegistrationReader = new ReadFromCsv(BOOK_REGISTRATION_PATH);

        boolean authorIsEmpty = authorReader.readAll().isEmpty();
        boolean bookIsEmpty = bookReader.readAll().isEmpty();
        boolean bookRegistrationIsEmpty = bookRegistrationReader.readAll().isEmpty();
        if (authorIsEmpty || bookIsEmpty || bookRegistrationIsEmpty) {
            initAuthorTable();
            initBookTable();
            initBookRegistrationTable();
        } else {
            authorReader = new ReadFromCsv(AUTHOR_PATH);
            AUTHOR_DATA_LIST.addAll(authorReader.readAll());

            bookReader = new ReadFromCsv(BOOK_PATH);
            BOOK_DATA_LIST.addAll(bookReader.readAll());

            bookRegistrationReader = new ReadFromCsv(BOOK_REGISTRATION_PATH);
            BOOK_REGISTRATION_DATA_LIST.addAll(bookRegistrationReader.readAll());
        }
    }

    public static DbLayer getInstance() {
        if (instance == null) {
            instance = new DbLayer();
        }
        return instance;
    }

    private void initAuthorTable() {
        authorWriter = new WriteToCsv(AUTHOR_PATH);
        authorReader = new ReadFromCsv(AUTHOR_PATH);

        AUTHOR_DATA_LIST.add(AUTHOR_TABLE_HEADER);
        authorWriter.writeAll(AUTHOR_DATA_LIST);
    }

    private void initBookTable() {
        bookWriter = new WriteToCsv(BOOK_PATH);
        bookReader = new ReadFromCsv(BOOK_PATH);

        BOOK_DATA_LIST.add(BOOK_TABLE_HEADER);
        bookWriter.writeAll(BOOK_DATA_LIST);
    }

    private void initBookRegistrationTable() {
        bookRegistrationWriter = new WriteToCsv(BOOK_REGISTRATION_PATH);
        bookRegistrationReader = new ReadFromCsv(BOOK_REGISTRATION_PATH);

        BOOK_REGISTRATION_DATA_LIST.add(BOOK_REGISTRATION_TABLE_HEADER);
        bookRegistrationWriter.writeAll(BOOK_REGISTRATION_DATA_LIST);
    }

    public void createAuthor(Author author) {
        author.setId(AUTHOR_DATA_LIST.size());
        String[] authorData = new String[AUTHOR_TABLE_COLUMNS_AMOUNT];
        authorData[0] = String.valueOf(author.getId());
        authorData[1] = author.getName();
        authorData[2] = author.getSurname();
        authorData[3] = author.getDateOfBirth();
        authorData[4] = String.valueOf(author.getAvailable());

        this.AUTHOR_DATA_LIST.add(authorData);
        authorWriter = new WriteToCsv(AUTHOR_PATH);
        authorWriter.writeAll(this.AUTHOR_DATA_LIST);
    }

    public void createBook(Book book) {
        book.setId(BOOK_DATA_LIST.size());
        String[] bookData = new String[BOOK_TABLE_COLUMNS_AMOUNT];
        bookData[0] = String.valueOf(book.getId());
        bookData[1] = book.getName();
        bookData[2] = String.valueOf(book.getPages());
        bookData[3] = String.valueOf(book.getAvailable());

        this.BOOK_DATA_LIST.add(bookData);
        bookWriter = new WriteToCsv(BOOK_PATH);
        bookWriter.writeAll(this.BOOK_DATA_LIST);
    }

    public void createBookRegistration(Author author, Book book) {
        String[] bookRegistrationData = new String[BOOK_REGISTRATION_TABLE_COLUMNS_AMOUNT];
        bookRegistrationData[0] = String.valueOf(BOOK_REGISTRATION_DATA_LIST.size());
        bookRegistrationData[1] = String.valueOf(author.getId());
        bookRegistrationData[2] = String.valueOf(book.getId());

        BOOK_REGISTRATION_DATA_LIST.add(bookRegistrationData);
        bookRegistrationWriter = new WriteToCsv(BOOK_REGISTRATION_PATH);
        bookRegistrationWriter.writeAll(this.BOOK_REGISTRATION_DATA_LIST);
    }

    public Set<Author> findAllAuthors() {
        Set<Author> authors = new HashSet<>();
        for (int i = 1; i < AUTHOR_DATA_LIST.size(); i++) {
            int id = Integer.parseInt(AUTHOR_DATA_LIST.get(i)[0]);
            Author author = findAuthorById(id);
            authors.add(author);
        }

        return authors;
    }

    public Set<Book> findAllBooks() {
        Set<Book> books = new HashSet<>();
        for (int i = 1; i < BOOK_DATA_LIST.size(); i++) {
            int id = Integer.parseInt(BOOK_DATA_LIST.get(i)[0]);

            Book book = findBookById(id);
            books.add(book);
        }

        return books;
    }

    public Author findAuthorById(Integer id) {
        String[] authorRow = AUTHOR_DATA_LIST.stream()
                .skip(1)
                .filter(author -> Integer.parseInt(author[0]) == id)
                .findFirst().get();

        Author author = new Author();
        author.setId(Integer.parseInt(authorRow[0]));
        author.setName(authorRow[1]);
        author.setSurname(authorRow[2]);
        author.setDateOfBirth(authorRow[3]);
        author.setAvailable(Boolean.parseBoolean(authorRow[4]));
        author.setBooks(findAuthorBooks(id));

        return author;
    }

    public Book findBookById(Integer id) {
        String[] bookRow = BOOK_DATA_LIST.stream()
                .skip(1)
                .filter(book -> Integer.parseInt(book[0]) == id)
                .findFirst().get();

        Book book = new Book();
        book.setId(Integer.parseInt(bookRow[0]));
        book.setName(bookRow[1]);
        book.setPages(Integer.parseInt(bookRow[2]));
        book.setAvailable(Boolean.parseBoolean(bookRow[3]));
        book.setAuthors(findBookAuthors(id));

        return book;
    }

    public Set<Integer> findAuthorBooks(Integer authorId) {
        Set<Integer> booksIds = new HashSet<>();
        List<String[]> bookRegistrationRows = BOOK_REGISTRATION_DATA_LIST.stream()
                .skip(1)
                .filter(regData -> Integer.parseInt(regData[1]) == authorId)
                .collect(Collectors.toList());

        for (String[] data : bookRegistrationRows) {
            booksIds.add(Integer.parseInt(data[2]));
        }

        return booksIds;
    }

    public Set<Integer> findBookAuthors(Integer bookId) {
        Set<Integer> authorsIds = new HashSet<>();
        List<String[]> bookRegistrationRows = BOOK_REGISTRATION_DATA_LIST.stream()
                .skip(1)
                .filter(regData -> Integer.parseInt(regData[2]) == bookId)
                .collect(Collectors.toList());

        for (String[] data : bookRegistrationRows) {
            authorsIds.add(Integer.parseInt(data[1]));
        }

        return authorsIds;
    }

    public void updateAuthor(Author author) {
        int updatedAuthorRowIndex = 0;
        for (int i = 1; i < this.AUTHOR_DATA_LIST.size(); i++) {
            if (Integer.parseInt(AUTHOR_DATA_LIST.get(i)[0]) == author.getId()) {
                updatedAuthorRowIndex = i;
            }
        }

        String[] updatedRow = new String[AUTHOR_TABLE_COLUMNS_AMOUNT];
        updatedRow[0] = String.valueOf(author.getId());
        updatedRow[1] = author.getName();
        updatedRow[2] = author.getSurname();
        updatedRow[3] = author.getDateOfBirth();
        updatedRow[4] = String.valueOf(author.getAvailable());

        AUTHOR_DATA_LIST.set(updatedAuthorRowIndex, updatedRow);
        authorWriter = new WriteToCsv(AUTHOR_PATH);
        authorWriter.writeAll(AUTHOR_DATA_LIST);
    }

    public void updateBook(Book book) {
        int updatedBookRowIndex = 0;
        for (int i = 1; i < this.BOOK_DATA_LIST.size(); i++) {
            if (Integer.parseInt(BOOK_DATA_LIST.get(i)[0]) == book.getId()) {
                updatedBookRowIndex = i;
            }
        }

        String[] updatedRow = new String[BOOK_TABLE_COLUMNS_AMOUNT];
        updatedRow[0] = String.valueOf(book.getId());
        updatedRow[1] = book.getName();
        updatedRow[2] = String.valueOf(book.getPages());
        updatedRow[3] = String.valueOf(book.getAvailable());

        BOOK_DATA_LIST.set(updatedBookRowIndex, updatedRow);
        bookWriter = new WriteToCsv(BOOK_PATH);
        bookWriter.writeAll(BOOK_DATA_LIST);
    }

    public void deleteAuthor(Author author) {
        int deletedAuthorRowIndex = 0;
        for (int i = 1; i < this.AUTHOR_DATA_LIST.size(); i++) {
            if (Integer.parseInt(AUTHOR_DATA_LIST.get(i)[0]) == author.getId()) {
                deletedAuthorRowIndex = i;
                break;
            }
        }

        AUTHOR_DATA_LIST.get(deletedAuthorRowIndex)[4] = "false";
        authorWriter = new WriteToCsv(AUTHOR_PATH);
        authorWriter.writeAll(AUTHOR_DATA_LIST);
    }

    public void deleteBook(Book book) {
        int deletedBookRowIndex = 0;
        for (int i = 1; i < this.BOOK_DATA_LIST.size(); i++) {
            if (Integer.parseInt(BOOK_DATA_LIST.get(i)[0]) == book.getId()) {
                deletedBookRowIndex = i;
            }
        }

        BOOK_DATA_LIST.get(deletedBookRowIndex)[3] = "false";
        bookWriter = new WriteToCsv(BOOK_PATH);
        bookWriter.writeAll(BOOK_DATA_LIST);
    }
}