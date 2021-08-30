/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author shika823
 */
public class CustomerJdbcDAO implements CustomerDAO {

    String uri = DbConnection.getDefaultConnectionUri();

    public CustomerJdbcDAO() {
    }

    public CustomerJdbcDAO(String uriIn) {
        uri = uriIn;

    }

    @Override
    public void saveCustomer(Customer customer) {

        String sql = "insert into customer (username, firstname, surname, password, Email_Address, Shipping_Address ) values (?,?,?,?,?,?)";
        try (
                 Connection dbCon = DbConnection.getConnection(uri);  
                PreparedStatement stmt = dbCon.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);) {

            //stmt.setInt(1, customer.getCustomerID());
            stmt.setString(1, customer.getUserName());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getSurname());
            stmt.setString(4, customer.getPassword());
            stmt.setString(5, customer.getEmailAddress());
            stmt.setString(6, customer.getShippingAddress());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {

                Integer generatedId = rs.getInt(1);

            } else {
                throw new RuntimeException("Problem getting generated ID");
            }

        } catch (SQLException ex) {

            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public Customer getCustomer(String username) {
        String sql = "select * from customer where username = ?";

        try (
                 Connection connection = DbConnection.getConnection(uri);  PreparedStatement stmt = connection.prepareStatement(sql);) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                //Integer customerID = rs.getInt("customerID");
                Integer id = rs.getInt("customer_id");
                String userName = rs.getString("username");
                String firstName = rs.getString("firstname");
                String surname = rs.getString("surname");
                String password = rs.getString("password");
                String emailAddress = rs.getString("email_address");
                String shippingAddress = rs.getString("shipping_address");

                return new Customer(id, userName, firstName, surname, password, emailAddress, shippingAddress);

            } else {

                return null;
            }

        } catch (SQLException ex) {

            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Boolean validateCredentials(String username, String password) {
        String sql = "select * from customer where username = ?";

        try (
                 Connection connection = DbConnection.getConnection(uri);  PreparedStatement stmt = connection.prepareStatement(sql);) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;

            }

        } catch (SQLException ex) {

            throw new DAOException(ex.getMessage(), ex);
        }

    }
}


