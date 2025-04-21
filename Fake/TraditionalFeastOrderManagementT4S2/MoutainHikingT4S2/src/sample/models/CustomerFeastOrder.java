/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.models;

import java.io.Serializable;

/**
 *
 * @author Wang
 */
public class CustomerFeastOrder implements Serializable {

    private Customer customer;
    private String tables;
    private float cost;

    @Override
    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
        CustomerFeastOrder order = (CustomerFeastOrder) obj;
        return this.customer.getId().equals(order.customer.getId());
    }

//    public boolean equalsByName(StudentMountain other) {
////        if (this == other) {
////            return true;
////        }
////        if (other == null || getClass() != other.getClass()) {
////            return false;
////        }
//        return this.student.getName().equalsIgnoreCase(other.student.getName());
//    }
    public CustomerFeastOrder() {
    }

    public String getTables() {
        return tables;
    }

    public void SetTables(String tables) {
        this.tables = tables;
    }

    public CustomerFeastOrder(Customer customer, String tables, float cost) {
        this.customer = customer;
        this.tables = tables;
        this.cost = cost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-12s | %-30s | MT%02d            | %,-10.0f",
                this.customer.getId(),
                this.customer.getName(),
                this.customer.getPhone(),
                this.customer.getEmail(),
                Integer.parseInt(this.tables),
                this.cost);
    }

    public String toStringByDelete() {
        return String.format("Student ID: %s\nName: %s\nPhone: %s\nEmail: %s\nMountain: MT%02d\nFee: %,.0f",
                this.customer.getId(),
                this.customer.getName(),
                this.customer.getPhone(),
                this.customer.getEmail(),
                Integer.parseInt(this.tables), // Chuyển đổi mountainCode thành số nguyên
                this.cost);
    }

}
