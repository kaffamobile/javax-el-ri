package org.glassfish.el.test;

import java.util.Date;

public class Order {

    int orderID; 
    int customerID;
    Date orderDate;
    double total;

    public Order(int orderID, int customerID, Date orderDate, double total) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.total = total;
    }

    public String toString() {
        return "Order: " + orderID + ", " + customerID +
            ", " + orderDate.getYear() + ", " + total;
    }

    public int getOrderID() { return orderID; }
    public int getCustomerID() { return customerID; }
    public Date getOrderDate() { return orderDate; }
    public double getTotal() { return total; }
}
