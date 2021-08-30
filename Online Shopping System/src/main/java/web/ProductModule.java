/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.ProductDAO;
import dao.ProductJdbcDAO;
import org.jooby.Jooby;

/**
 *
 * @author shika823
 */
public class ProductModule extends Jooby {

    private ProductDAO productDao = new ProductJdbcDAO();

    public ProductModule(ProductDAO daoIn) {

        get("/api/products", () -> productDao.getProducts());

        get("/api/categories", () -> productDao.getCategories());

        get("/api/categories/:category", (req) -> {
            String category = req.param("category").value();
            return productDao.filterByCategory(category);
        });

        get("/api/products/:id", (req) -> {
            String id = req.param("id").value();
            return productDao.searchById(id);
        });

    }
}
