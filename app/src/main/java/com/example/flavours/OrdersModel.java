package com.example.flavours;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class OrdersModel {
    @ServerTimestamp
    private Date date;
    private String subtotal;
    private String tax;
    private String deliveryCharge;
    private String total;

    public OrdersModel() {
    }

    public OrdersModel(Date date, String subtotal, String tax, String deliveryCharge, String total) {
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

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}