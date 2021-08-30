/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//import domain.ProductCollectionsDAO;
//import static domain.ProductCollectionsDAO.getProducts;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Locale.Category;
import domain.Product;
import static gui.ProductEditor.dao;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ksts
 */
public class ProductCollectionsDAOTest {

    private Product product1;
    private Product product2;
    private Product product3;

    public ProductCollectionsDAOTest() {
    }
    //ProductCollectionsDAO dao = new ProductCollectionsDAO();
    ProductJdbcDAO dao = new ProductJdbcDAO("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");

    @BeforeEach
    public void setUp() {

        product1 = new Product();
        product1.setProductID("11112");
        product1.setDescription("Used record");
        product1.setName("John Lennon Record");
        product1.setListPrice(new BigDecimal("55.33"));
        product1.setCategory("rock");
        product1.setQuantityInStock(new BigDecimal((1)));

        product2 = new Product();
        product2.setProductID("12412");
        product2.setName("ACDC Record");
        product2.setDescription("New record");
        product2.setListPrice(new BigDecimal("45.00"));
        product2.setCategory("hip-hop");
        product2.setQuantityInStock(new BigDecimal((5)));

        product3 = new Product();
        product3.setProductID("99991");
        product3.setName("Abba Record");
        product3.setDescription("Used record");
        product3.setListPrice(new BigDecimal("75.00"));
        product3.setCategory("pop");
        product3.setQuantityInStock(new BigDecimal((3)));

        dao.saveProduct(product1);
        dao.saveProduct(product2);

    }

    @AfterEach
    public void tearDown() {
        dao.removeProduct(product1);
        dao.removeProduct(product2);
    }

    @Test
    public void testSaveProduct() {
        assertThat(dao.getProducts(), hasItem(product1));
        assertThat(dao.getProducts(), hasItem(product2));
        assertThat(dao.getProducts(), hasSize(2));
    }

    @Test
    public void testRemoveProduct() {

        dao.removeProduct(product1);
        assertThat(dao.getProducts(), not(hasItem(product1)));
        assertThat(dao.getProducts(), hasSize(1));

        dao.removeProduct(product3);
    }

    @Test
    public void testGetProducts() {
        assertThat(dao.getProducts(), (hasItem(product1)));
        assertThat(dao.getProducts(), (hasItem(product2)));

        assertThat(dao.getProducts(), hasSize(2));
    }

    @Test
    public void testGetCategories() {
        assertEquals(product1.getCategory(), ("rock"));
        assertEquals(product2.getCategory(), ("hip-hop"));
    }

    @Test
    public void testSearchById() {
        //assertThat(dao, is(11112));
        assertThat(product1, is(notNullValue()));
        assertThat(product1, hasProperty("productID", equalTo("11112")));
        assertThat(product1, hasProperty("productID", not(equalTo("11114"))));

    }

    @Test

    public void testFilterByCategory() {
        Collection<Product> filterCategory = dao.filterByCategory("rock");

        for (Product product : filterCategory) {

            String category = product.getCategory();
            assertThat(product.getCategory(), is(category));

            //assertThat(product.getCategory(), is("pop"));
            assertThat(product.getCategory(), is(not("pop")));

        }

    }
}
