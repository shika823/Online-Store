/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 *
 * @author ksts
 */
public class Sale {

    private Integer saleID;

    private LocalDateTime date;

    private String status;

    private Customer customer;

    private Collection<SaleItem> items;

    public Integer getSaleID() {
        return saleID;
    }

    public void setSaleID(Integer saleID) {
        this.saleID = saleID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<SaleItem> getItems() {
        return items;
    }

    public void setItems(Collection<SaleItem> items) {
        this.items = items;
    }

    public void addItem(SaleItem saleitem) {
        items.add(saleitem);

    }

    public BigDecimal getTotal() {

        BigDecimal total = BigDecimal.ZERO;

        for (SaleItem item : items) {
            total = total.add(item.getItemTotal());
        }

        return total;
        //return items.stream().mapToBigDecimal(items -> items.getItemTotal()).sum();

    }

    @Override
    public String toString() {
        return "Sale{" + "saleID=" + saleID + ", date=" + date + ", status=" + status + ", customer=" + customer + ", items=" + items + '}';
    }

}
