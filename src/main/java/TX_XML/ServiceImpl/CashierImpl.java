package TX_XML.ServiceImpl;

import java.util.List;

import TX_XML.Service.BookShopService;
import TX_XML.Service.Cashier;

public class CashierImpl implements Cashier {
    BookShopService bookShopService;

    public BookShopService getBookShopService() {
        return bookShopService;
    }

    public void setBookShopService(BookShopService bookShopService) {
        this.bookShopService = bookShopService;
    }

    @Override
    public void chekout(String username, List<String> isbns) {
        for (String isbn :
                isbns) {
            bookShopService.purchase(username,isbn);
        }
    }
}
