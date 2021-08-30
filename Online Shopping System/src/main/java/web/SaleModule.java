/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerJdbcDAO;
import org.jooby.Jooby;

import dao.SaleDAO;
import dao.SaleJdbcDAO;
import domain.Customer;
import domain.Sale;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author ksts
 */
public class SaleModule extends Jooby {

    private SaleJdbcDAO SaleDao = new SaleJdbcDAO();

    public SaleModule(SaleDAO SaleDao) {
        post("/api/sales", (req, rsp) -> {
            Sale sale = req.body().to(Sale.class);
            SaleDao.save(sale);
            rsp.status(Status.CREATED);
        });

    }

}
