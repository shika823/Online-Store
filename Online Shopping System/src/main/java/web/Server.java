/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerCollectionsDAO;
import dao.CustomerDAO;
import dao.CustomerJdbcDAO;
import dao.ProductDAO;
import dao.ProductJdbcDAO;
import dao.SaleDAO;
import dao.SaleJdbcDAO;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.json.Gzon;

/**
 *
 * @author shika823
 */
public class Server extends Jooby {

    private ProductDAO productDao = new ProductJdbcDAO();
    private CustomerDAO CustomerDao = new CustomerJdbcDAO();
    private SaleDAO saleDao = new SaleJdbcDAO();

    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Server.");

        Server server = new Server();

        CompletableFuture.runAsync(() -> {
            server.start();
        });

        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop the server.");
        });

        // wait for user to hit the Enter key
        System.in.read();
        System.exit(0);
    }

    public Server() {
        port(8080);
        use(new Gzon());
        use(new ProductModule(productDao));
        use(new CustomerModule(CustomerDao));
        use(new SaleModule(saleDao));
        use(new AssetModule());

//get("/api/products", () -> productDao.getProducts());
        //get("/api/products/:id", (req) -> {
        //String id = req.param("id").value();
        //return productDao.searchById(id);
//});
    }
}
