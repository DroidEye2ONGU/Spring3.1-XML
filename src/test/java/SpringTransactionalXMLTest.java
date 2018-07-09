import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import TX_XML.Service.BookShopService;
import TX_XML.Service.Cashier;

public class SpringTransactionalXMLTest {
    private ApplicationContext applicationContext;
    private BookShopService bookShopService;
    private Cashier cashier;

    {
        applicationContext = new ClassPathXmlApplicationContext(
                "applicationContext-TX-XML.xml");
        bookShopService = (BookShopService) applicationContext.getBean("bookShopService");
        cashier = (Cashier) applicationContext.getBean("cashier");
    }

    @Test
    public void testBookShopService() {
        bookShopService.purchase("AA","1001");
    }

    @Test
    public void testTransactionalPropagation() {
        cashier.chekout("AA",Arrays.asList("1001","1002"));
    }
}
