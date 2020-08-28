package com.example.flavours;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class OrdersModel {
    @ServerTimestamp
    private Date date;
    private double subtotal;
    private double tax;
    private double deliveryCharge;
    private double total;

    public OrdersModel() {
    }

    public OrdersModel(Date date, double subtotal, double tax, double deliveryCharge, double total) {
        this.date = date;
        this.subtotal = subtotal;
        this.tax = tax;
        this.deliveryCharge = deliveryCharge;
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
