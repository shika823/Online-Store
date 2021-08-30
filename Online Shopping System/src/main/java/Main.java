
import dao.ProductCollectionsDAO;
import dao.ProductJdbcDAO;
import domain.Product;
import gui.MainMenu;
import gui.ProductEditor;
import java.math.BigDecimal;


/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shika823
 */
public class Main {

    //@param args the command line arguments
    public static void main(String[] args) {
        ProductJdbcDAO dao = new ProductJdbcDAO();

        /*
        Product product1 = new Product();
        product1.setProductID("1111");
        product1.setDescription("Used record");
        product1.setName("John Lennon Record");
        product1.setListPrice(new BigDecimal("55.33"));
        product1.setCategory("rock");
        product1.setQuantityInStock(new BigDecimal((1)));
        
        Product product2 = new Product();
        product2.setProductID("1241");
        product2.setName("ACDC Record");
        product2.setDescription("New record");
        product2.setListPrice(new BigDecimal("45.00"));
        product2.setCategory("hip-hop");
        product2.setQuantityInStock(new BigDecimal((5)));
        
        Product product3 = new Product();
        product3.setProductID("9999");
        product3.setName("Abba Record");
        product3.setDescription("Used record");
        product3.setListPrice(new BigDecimal("75.00"));
        product3.setCategory("pop");
        product3.setQuantityInStock(new BigDecimal((3)));
        
        dao.saveProduct(product1);
        dao.saveProduct(product2);
		  dao.saveProduct(product3);
		  
         */
        MainMenu frame = new MainMenu(dao);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }
}
