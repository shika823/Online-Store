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
import domain.Customer;
import org.jooby.Jooby;
import org.jooby.Result;
import org.jooby.Status;

/**
 *
 * @author shika823
 */
public class CustomerModule extends Jooby {

    private CustomerJdbcDAO CustomerDao = new CustomerJdbcDAO();

    public CustomerModule(CustomerDAO CustomerDao) {

        get("/api/customers/:username", (req)
                -> {
            String username = req.param("username").value();
            Customer c = CustomerDao.getCustomer(username);
            if (c == null) {
                return new Result().status(Status.NOT_FOUND);
            } else {

                return c;
            }

        });

        post("/api/register", (req, rsp) -> {
            Customer customer = req.body().to(Customer.class);
            CustomerDao.saveCustomer(customer);
            rsp.status(Status.CREATED);
        });

    }
}
