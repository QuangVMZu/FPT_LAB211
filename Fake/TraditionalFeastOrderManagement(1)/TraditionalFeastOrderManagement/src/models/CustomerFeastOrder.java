/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class CustomerFeastOrder implements Serializable {

    private Customer customer;
    private String codeOfSetMenu;
    private int numOfTable;
    private Date date;

    public CustomerFeastOrder() {
    }

    public CustomerFeastOrder(Customer customer) {
        this.customer = customer;
    }

    public CustomerFeastOrder(Customer customer, String codeOfSetMenu, int numOfTable) {
        this.customer = customer;
        this.codeOfSetMenu = codeOfSetMenu;
        this.numOfTable = numOfTable;
    }

    @Override
    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
        CustomerFeastOrder that = (CustomerFeastOrder) obj;

        // So sánh mã khách hàng
        if (this.customer != null && that.customer != null) {
            if (!this.customer.getCode().equalsIgnoreCase(that.customer.getCode())) {
                return false;
            }
        } else {
            return false;
        }

        // So sánh mã thực đơn (có thể bị null)
        if (this.codeOfSetMenu != null && that.codeOfSetMenu != null) {
            if (!this.codeOfSetMenu.equalsIgnoreCase(that.codeOfSetMenu)) {
                return false;
            }
        }

        // So sánh ngày tổ chức tiệc (có thể bị null)
        if (this.date != null && that.date != null) {
            if (!this.date.equals(that.date)) {
                return false;
            }
        }

        return true;
    }

    public CustomerFeastOrder(Customer customer, String codeOfSetMenu, int numOfTable, Date date) {
        this.customer = customer;
        this.codeOfSetMenu = codeOfSetMenu;
        this.numOfTable = numOfTable;
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCodeOfSetMenu() {
        return codeOfSetMenu;
    }

    public void setCodeOfSetMenu(String codeOfSetMenu) {
        this.codeOfSetMenu = codeOfSetMenu;
    }

    public int getNumOfTable() {
        return numOfTable;
    }

    public void setNumOfTable(int numOfTable) {
        this.numOfTable = numOfTable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%-13s | %-20s | %-12s | %-30s",
                this.customer.getCode(),
                this.customer.getName(),
                this.customer.getPhone(),
                this.customer.getEmail()
        );
    }
}
