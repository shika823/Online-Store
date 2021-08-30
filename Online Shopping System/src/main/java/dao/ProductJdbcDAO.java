/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale.Category;

/**
 *
 * @author shika823
 */
public class ProductJdbcDAO implements ProductDAO {

    String uri = DbConnection.getDefaultConnectionUri();

    public ProductJdbcDAO() {
    }

    public ProductJdbcDAO(String uriIn) {
        uri = uriIn;

    }

    @Override
    public Collection<Product> filterByCategory(String category) {

        String sql = "select * from product where category = ?";
        try (
                 Connection connection = DbConnection.getConnection(uri);  PreparedStatement stmt = connection.prepareStatement(sql);) {

            stmt.setString(1, category);

            ResultSet rs = stmt.executeQuery();

            Collection<Product> filterCategory = new HashSet<>();

            while (rs.next()) {
                String productId = rs.getString("product_Id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String categ = rs.getString("category");
                BigDecimal listPrice = rs.getBigDecimal("list_Price");
                BigDecimal quantityInStock = rs.getBigDecimal("quantity_In_Stock");

                Product product = new Product(productId, name, description, category, listPrice, quantityInStock);

                filterCategory.add(product);

                //return filterCategory;
            }
            return filterCategory;

        } catch (SQLException ex) {

            throw new DAOException(ex.getMessage(), ex);
        }
        //return null;

    }

    @Override
    public Collection<String> getCategories() {
        String sql = "select category from product ";

        try (
                 Connection dbCon = DbConnection.getConnection(uri);  PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            ResultSet rs = stmt.executeQuery();

            Collection<String> categoriesList = new HashSet<>();

            while (rs.next()) {

                String category = rs.getString("category");
                categoriesList.add(category);
            }

            return categoriesList;

        } catch (SQLException ex) {

            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public Collection<Product> getProducts() {
        String sql = "select * from product order by product_id";

        try (
                 Connection dbCon = DbConnection.getConnection(uri);  PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            ResultSet rs = stmt.executeQuery();
            Collection<Product> productsList = new HashSet<>();


            while (rs.next()) {

                String productId = rs.getString("product_Id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal listPrice = rs.getBigDecimal("list_Price");
                BigDecimal quantityInStock = rs.getBigDecimal("quantity_In_Stock");
                Product product = new Product(productId, name, description, category, listPrice, quantityInStock);

                productsList.add(product);
            }

            return productsList;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);

        }
    }

    @Override
    public void removeProduct(Product productToAdd) {
        String sql = "Delete from product where product_id = ?";
        try (
                 Connection dbCon = DbConnection.getConnection(uri);  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, productToAdd.getProductID());

            stmt.executeUpdate();
        } catch (SQLException ex) {

            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void saveProduct(Product productToAdd) {
        String sql = "merge into product (Product_Id, name, description, category, List_price, quantity_In_Stock) values (?,?,?,?,?,?)";
        try (
                 Connection dbCon = DbConnection.getConnection(uri);  PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            stmt.setString(1, productToAdd.getProductID());
            stmt.setString(2, productToAdd.getName());
            stmt.setString(3, productToAdd.getDescription());
            stmt.setString(4, productToAdd.getCategory());
            stmt.setBigDecimal(5, productToAdd.getListPrice());
            stmt.setBigDecimal(6, productToAdd.getQuantityInStock());
            stmt.executeUpdate();
        } catch (SQLException ex) {

            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Product searchById(String productId) {
        String sql = "select * from product where product_id = ?";

        try (
                 Connection connection = DbConnection.getConnection(uri);  PreparedStatement stmt = connection.prepareStatement(sql);) {

            stmt.setString(1, productId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String id = rs.getString("product_Id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal listPrice = rs.getBigDecimal("list_Price");
                BigDecimal quantityInStock = rs.getBigDecimal("quantity_In_Stock");

                return new Product(productId, name, description, category, listPrice, quantityInStock);

            } else {

                return null;
            }

        } catch (SQLException ex) {

            throw new DAOException(ex.getMessage(), ex);
        }
    }

}

/**
 * @Override public Product searchById(String productId) { throw new
 * UnsupportedOperationException("Not supported yet."); //To change body of
 * generated methods, choose Tools | Templates. }
 */
