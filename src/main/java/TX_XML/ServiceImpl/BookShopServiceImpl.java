package TX_XML.ServiceImpl;

import TX_XML.Dao.BookShopDao;
import TX_XML.Service.BookShopService;

public class BookShopServiceImpl implements BookShopService {

    private BookShopDao bookShopDao;

    public BookShopDao getBookShopDao() {
        return bookShopDao;
    }

    public void setBookShopDao(BookShopDao bookShopDao) {
        this.bookShopDao = bookShopDao;
    }

    @Override
    public void purchase(String username, String isbn) {
        int price = bookShopDao.fineBookPriceByIsbn(isbn);
        bookShopDao.updateBookStock(isbn);
        bookShopDao.updateUserAccount(username,price);
    }
}
