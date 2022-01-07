import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.persistence.entity.Book;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDaoTest {
    private final AuthorDao authorDao = new AuthorDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    @Test
    @Order(1)
    public void createBookTest(){
        for(int i = 1; i < 10; i++){
            Book book = new Book();
            book.setName("Name " + i);
            book.setPages(10 + 1);
            bookDao.create(book);
        }
    }

    @Test
    @Order(2)
    public void findBookTest(){
        createBookTest();
        for(int i = 1; i < 10; i++){
            Book book = bookDao.find(i);
        }
    }

    @Test
    @Order(3)
    public void updateBookTest(){
        createBookTest();
        for(int i = 1; i < 10; i++){
            Book book = bookDao.find(i);
            book.setName("Update name " + i);
            book.setPages(20 + i);
            bookDao.update(book);
        }
    }

    @Test
    @Order(4)
    public void deleteBookTest(){
        updateBookTest();
        for(int i = 1; i < 10; i++){
            Book book = bookDao.find(i);
            bookDao.delete(book);
        }
    }

    @Test
    @Order(5)
    public void addAuthorToBookTest(){
        updateBookTest();
        for(int i = 1; i < 10; i++){
            Author author = new Author();
            author.setName("Name " + i);
            author.setSurname("Surname " + i);
            author.setDateOfBirth("Date of Birth 01/01/200" + i);
            authorDao.create(author);

            for(int j = 1; j < 10; j++){
                Book book = bookDao.find(j);
                bookDao.addAuthorToBook(book, authorDao.find(i));
            }
        }
    }
}
