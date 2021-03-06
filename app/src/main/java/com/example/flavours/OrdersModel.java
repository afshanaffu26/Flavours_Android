package com.example.flavours;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class OrdersModel {
    private String date;
    private String subtotal;
    private String tax;
    private String deliveryCharge;
    private String total;
    private String address;

    public OrdersModel() {
    }

    public OrdersModel(String date, String subtotal, String tax, String deliveryCharge, String total, String address) {
        this.date = date;
        this.subtotal = subtotal;
        this.tax = tax;
        this.deliveryCharge = deliveryCharge;
        this.total = total;
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}