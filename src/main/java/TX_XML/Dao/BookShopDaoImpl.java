package TX_XML.Dao;

import org.springframework.jdbc.core.JdbcTemplate;

import TX_XML.MyException.BookStockException;
import TX_XML.MyException.UserAccountException;

public class BookShopDaoImpl implements BookShopDao {
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int fineBookPriceByIsbn(String isbn) {
        String sql = "SELECT price FROM book WHERE isbn=?";

        Integer price = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        return price;
    }

    @Override
    public void updateBookStock(String isbn) {
        String sql2 = "SELECT stock FROM book_stock WHERE isbn=?";
        Integer stock = jdbcTemplate.queryForObject(sql2, Integer.class, isbn);
        if (stock < 1) {
            throw new BookStockException("库存不足!");
        }

        String sql = "UPDATE book_stock SET stock=stock-1 WHERE isbn=?";
        jdbcTemplate.update(sql, isbn);
    }

    @Override
    public void updateUserAccount(String username, int price) {
        String sql2 = "SELECT balance FROM account WHERE username=?";
        Integer balance = jdbcTemplate.queryForObject(sql2, Integer.class, username);
        if (balance < price) {
            throw new UserAccountException("余额不足!");
        }

        String sql = "UPDATE account SET balance=balance-? WHERE username=?";
        jdbcTemplate.update(sql, price, username);
    }
}
