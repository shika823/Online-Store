/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import static org.assertj.swing.core.matcher.DialogMatcher.withTitle;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.jooby.funzy.When.when;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author shika823
 */
public class ProductEditorTest {

    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;

    private Product p1 = new Product("12345", "ACDC Record", "old", "Rock", new BigDecimal("55.00"), new BigDecimal("1"));
    private Product p2 = new Product("12346", "Micheal Jackson CD", "old", "Pop", new BigDecimal("25.00"), new BigDecimal("2"));
    private Product p3 = new Product("12335", "Bob Dylan CD", "old", "Rock", new BigDecimal("45.00"), new BigDecimal("1"));

    ;

    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(100);

        Collection<String> categories = new ArrayList<>();
        categories.add("Pop");
        categories.add("Rock");

        dao = mock(ProductDAO.class);
        when(dao.getCategories()).thenReturn(categories);

        Collection<Product> products = new HashSet<>();

        products.add(p1);
        products.add(p2);

        // stub the getProducts method
        when(dao.getProducts()).thenReturn(products);

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                // remove the product from the collection that getProducts() uses
                products.remove(p2);
                return null;
            }
        }).when(dao).removeProduct(p2);
    }

    @AfterEach
    public void tearDown() {
        fixture.cleanUp();

    }

    @Test
    public void testSaveProduct() {

        ProductEditor dialog = new ProductEditor(null, true, dao);

        fixture = new DialogFixture(robot, dialog);

        fixture.show().requireVisible();

        fixture.click();

        fixture.textBox("TxtId").enterText("12345");
        fixture.textBox("TxtName").enterText("ACDC Record");
        fixture.textBox("TxtDescription").enterText("old");
        fixture.comboBox("categoryComboBox").selectItem("Rock");
        fixture.textBox("TxtPrice").enterText("55");
        fixture.textBox("TxtQuantityInStock").enterText("1");

        fixture.button("jButtonSave").click();

        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        verify(dao).saveProduct(argument.capture());

        Product savedProduct = argument.getValue();

        assertThat("Ensure the ID was saved", savedProduct.getProductID(), is("12345"));
        assertThat("Ensure the name was saved", savedProduct.getName(), is("ACDC Record"));
        assertThat("Ensure the description was saved", savedProduct.getDescription(), is("old"));
        assertThat("Ensure the category was saved", savedProduct.getCategory(), is("Rock"));
        assertThat("Ensure the price was saved", savedProduct.getListPrice().toString(), is("55"));
        assertThat("Ensure the quantity was saved", savedProduct.getQuantityInStock().toString(), is("1"));

    }

    @Test
    public void testGetProducts() {
        ProductViewer viewer = new ProductViewer(null, true, dao);

        fixture = new DialogFixture(robot, viewer);

        fixture.show().requireVisible();

        fixture.click();

        SimpleListModel model = (SimpleListModel) fixture.list("itemList").target().getModel();
        //fixture.textBox("itemList").requireList(viewer.productsModel);

        assertTrue("list contains the expected product", model.contains(p1));
        assertTrue("list contains the expected product", model.contains(p2));
        assertFalse("list does not contain product", model.contains(p3));
        assertEquals("list contains the correct number of products", 2, model.getSize());
    }

    @Test
    public void testDeleteProduct() {
        ProductViewer viewer = new ProductViewer(null, true, dao);

        fixture = new DialogFixture(robot, viewer);

        fixture.show().requireVisible();

        fixture.click();

        // select item to delete in the list
        fixture.list("itemList").selectItem(p2.toString());

// click the delete button
        fixture.button("deleteButton").click();

// ensure a confirmation dialog is displayed
        DialogFixture confirmDialog = fixture.dialog(withTitle("Select an Option").andShowing()).requireVisible();

// click the Yes button on the confirmation dialog
        confirmDialog.button(withText("Yes")).click();

        SimpleListModel model = (SimpleListModel) fixture.list("itemList").target().getModel();

        assertFalse("list no longer contains product", model.contains(p2));
        assertTrue("list still contains product", model.contains(p1));
        assertEquals("list contains correct number of products (1)", 1, model.getSize());

    }
}
