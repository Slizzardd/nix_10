package ua.com.alevel;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.dao.CategoryDao;
import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.dao.UserDao;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.type.CardType;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.CategoryService;
import ua.com.alevel.service.TransactionService;
import ua.com.alevel.service.UserService;

import static ua.com.alevel.util.GetCreditBalanceByUserStatistic.getCreditBalanceByHistoryUsers;

@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
public class ModuleApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountDao accountDao;

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    TransactionService transactionService;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryService categoryService;

    @Test
    public void aCreateUser() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setFirstName("dru" + i);
            user.setLastName("dru" + i);
            user.setAge(20 + i);
            user.setEmail("artem.dubenko@nure.ua " + i);
            user.setPhoneNumber("+38000000000" + i);
            user.setPassport_details("000000000" + i);
            userService.create(user);
        }
        Assertions.assertEquals(10, userDao.count());
    }

    @Test
    public void bAddAccountsInUser() {
        for (int i = 0; i < 5; i++) {
            for (int l = 1; l <= 2; l++) {
                Account account = new Account();
                User user = userService.findById((long) i + 1);
                CardType cardType = CardType.values()[i];
                account.setCardType(cardType);
                accountService.create(account, "000000000" + i);
                user.addAccount(account);
                userService.update(user);
            }
        }
        Assertions.assertEquals(10, userDao.count());
        Assertions.assertEquals(10, accountDao.count());
    }

    @Test
    public void cUpdateUser() {
        for (int i = 1; i <= 5; i++) {
            User user = userService.findById((long) i);
            user.setFirstName("dru" + i + "updated");
            user.setLastName("dru" + i + "updated");
            user.setAge(30 + i);
            user.setEmail("artem.dubenko@nure.ua " + i + "updated");
            user.setPhoneNumber("+38000000000" + i);
            user.setPassport_details("000000000" + i);
            userService.update(user);
        }
        Assertions.assertEquals(10, userDao.count());
        Assertions.assertEquals(10, accountDao.count());
    }

    @Test
    public void dUpdateAccount() {
        for (int i = 1; i <= 3; i++) {
            Account account = accountService.findById((long) i);
            CardType cardType = CardType.values()[i + 1];
            account.setCardType(cardType);
            account.setBalance(getCreditBalanceByHistoryUsers(account.getUser(), account));
            accountService.update(account);
        }
        Assertions.assertEquals(10, userDao.count());
        Assertions.assertEquals(10, accountDao.count());
    }

    @Test
    public void eCreateTransaction(){
        Transaction transaction = new Transaction();
        String cardNumber = accountService.findById(3L).getCardNumber();
        Category category = new Category();
        category.setIncome(true);
        transaction.setAmount(199.0);
        transaction.setCategory(category);
        transactionService.create(transaction, cardNumber);

        Assertions.assertEquals(199.0, accountService.findById(3L).getBalance());
    }

    @Test
    public void fDeleteAccount() {
        for (int i = 1; i <= 5; i++) {
            accountService.delete((long) i);
        }
        Assertions.assertEquals(10, userDao.count());
        Assertions.assertEquals(5, accountDao.count());
    }

    @Test
    @Sql(value = "/delete_date_table.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void gDeleteUsers() {
        for (int i = 1; i <= 10; i++) {
            userService.delete((long) i);
        }
        Assertions.assertEquals(0, userDao.count());
        Assertions.assertEquals(0, accountDao.count());
    }
}
