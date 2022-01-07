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
public class AuthorDaoTest {
    private final AuthorDao authorDao = new AuthorDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    @Test
    @Order(1)
    public void createAuthorTest(){
        for(int i = 1; i < 10; i++){
            Author author = new Author();
            author.setName("Name " + i);
            author.setSurname("Surname " + i);
            author.setDateOfBirth("Date of Birth 01/01/200" + i);
            authorDao.create(author);
        }
    }

    @Test
    @Order(2)
    public void findAuthorTest(){
        createAuthorTest();
        for(int i = 1; i < 10; i++){
            Author author = authorDao.find(i);
        }
    }

    @Test
    @Order(3)
    public void updateAuthorTest(){
        createAuthorTest();
        for(int i = 1; i < 10; i++){
            Author author = authorDao.find(i);
            author.setName("Name " + i);
            author.setSurname("Surname " + i);
            author.setDateOfBirth("Date of Birth 01/01/200" + i);
            authorDao.update(author);
        }
    }

    @Test
    @Order(4)
    public void deleteAuthorTest(){
        updateAuthorTest();
        for(int i = 1; i < 10; i++){
            Author author = authorDao.find(i);
            authorDao.delete(author);
        }
    }

    @Test
    @Order(5)
    public void addBookToAuthorTest(){
        updateAuthorTest();
        for(int i = 1; i < 10; i++){
            Book book = new Book();
            book.setName("Name " + i);
            book.setPages(10 + 1);
            bookDao.create(book);

            for(int j = 1; j < 10; j++){
                Author author = authorDao.find(j);
                authorDao.addBookToAuthor(author, bookDao.find(i));
            }
        }
    }
}
