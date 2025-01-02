package com.example.pos.Model;

import javafx.beans.property.*;

public class Item {
    String ItemCode;
    String ItemName;
    int ItemNumber;
    double Cost;
    double CreditPrice;

    double InvoicePrice;
    double QuatationPrice;
    String Unit;
    double lcValue;
    String searchBar;
    double Discount;
    double profit;

    double quantity;

    double price;
    double Total;
    double Totallc;



    public Item(String itemCode, String itemName, int itemNumber ,double cost,double creditPrice, double invoicePrice, double quatationPrice, String unit, double lcValue) {
        this.ItemCode = itemCode;
        this.ItemName = itemName;
        this.ItemNumber = itemNumber;
        this.Cost = cost;
        this.CreditPrice = creditPrice;
        this.InvoicePrice = invoicePrice;
        this.QuatationPrice = quatationPrice;
        this.Unit = unit;
        this.lcValue = lcValue;

    }
    public Item(String itemCode,String itemName,double cost,double creditPrice, double invoicePrice, double quatationPrice, String unit, double lcValue) {
        this.ItemCode = itemCode;
        this.ItemName = itemName;
        this.Cost = cost;
        this.CreditPrice = creditPrice;
        this.InvoicePrice = invoicePrice;
        this.QuatationPrice = quatationPrice;
        this.Unit = unit;
        this.lcValue = lcValue;

    }
    public Item(String itemName,String itemCode){
        this.ItemName = itemName;
        this.ItemCode = itemCode;
    }
    public Item(String itemCode){
        this.ItemCode = itemCode;
    }

    public Item(int itemNumber){
        this.ItemNumber = itemNumber;
    }


    public String getItemCode() {
        return ItemCode;
    }
    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }
    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public double getCreditPrice() {
        return CreditPrice;
    }
    public void setCreditPrice(double creditPrice) {
        CreditPrice = creditPrice;
    }
    public Double getInvoicePrice() {
        return InvoicePrice;
    }
    public void setInvoicePrice(double invoicePrice) {
        InvoicePrice = invoicePrice;
    }
    public double getQuatationPrice() {
        return QuatationPrice;
    }
    public void setQuatationPrice(double quatationPrice) {
        QuatationPrice = quatationPrice;
    }
    public String getUnit() {
        return Unit;
    }
    public void setUnit(String unit) {
        this.Unit = unit;
    }
    public Double getLcValue() {
        return lcValue;
    }
    public void setLcValue(double lcValue) {
        this.lcValue = lcValue;
    }
    public double getCost() {
        return Cost;
    }
    public void setCost(double cost) {
        Cost = cost;
    }
    public int getItemNumber() {
        return ItemNumber;
    }
    public void setItemNumber(int itemNumber) {
        ItemNumber = itemNumber;
    }
    public String toString() {
        return ItemCode + " / " + ItemName ;
    }
    public String getSearchBar() {
        return searchBar;
    }
    public void setSearchBar(String searchBar) {
        this.searchBar = searchBar;
    }


    public Item(int itemNumber,String itemName,double price ,double quantity,String unit,double total,double lc,double discount,double prifit,double cost, double creditPrice) {
        this.ItemNumber = itemNumber;
        this.ItemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.Unit = unit;
        this.Total = total;
        this.lcValue = lc;
        this.Discount = discount;
        this.profit = prifit;
        this.Cost = cost;
        this.CreditPrice=creditPrice;

    }
    public int getitemNumber() {
        return ItemNumber;
    }
    public void setitemNumber(int itemNumber) {
        this.ItemNumber = itemNumber;
    }
    public String getItemname() {
        return ItemName;

    }
    public void setItemname(String itemname) {
        this.ItemName = itemname;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        this.Total = total;
    }

    public double getTotallc() {
        return Totallc;
    }
    public void setTotallc(double totallc) {
        this.Totallc = totallc;
    }

    public Item(String itemName,double quantity,double lcValue,double totallc) {
        this.ItemName = itemName;
        this.lcValue = lcValue;
        this.quantity = quantity;
        this.Totallc = totallc;

    }
    public double getDiscount(){
        return Discount;
    }

    public void setDiscount(double discount) {
        this.Discount =discount;
    }

    public double getProfit() {
        return profit;
    }
    public void setProfit(double profit){
        this.profit=profit;
    }
}

