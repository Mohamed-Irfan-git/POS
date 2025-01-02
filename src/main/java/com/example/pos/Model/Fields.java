package com.example.pos.Model;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Fields {
    TextField ItemName;
    TextField ItemCode;
    TextField CostPrice;
    TextField CreditPrice;
    TextField InvoicePrice;
    TextField quotationPrice;
    TextField unit;
    TextField lc;
    Label ItemNo;

    public Fields(  TextField itemCode, TextField itemName , TextField costPrice, TextField creditPrice, TextField invoicePrice, TextField quotationPrice, TextField unit, TextField lc) {

        ItemName = itemName;
        ItemCode = itemCode;
        CostPrice = costPrice;
        CreditPrice = creditPrice;
        InvoicePrice = invoicePrice;
        this.quotationPrice = quotationPrice;
        this.unit = unit;
        this.lc = lc;
    }
    public Fields(Label itemNo) {
        ItemNo = itemNo;
    }


    public TextField getItemName() {
        return ItemName;
    }

    public TextField getItemCode() {
        return ItemCode;
    }

    public TextField getCostPrice() {
        return CostPrice;
    }

    public TextField getCreditPrice() {
        return CreditPrice;
    }

    public TextField getInvoicePrice() {
        return InvoicePrice;
    }

    public TextField getQuotationPrice() {
        return quotationPrice;
    }

    public TextField getUnit() {
        return unit;
    }

    public TextField getLc() {
        return lc;
    }
    public Label getItemNo() {
        return ItemNo;
    }
}
