package com.example.myapplication;

public class Stocks {
    private String SecurityCode,SecurityID ,Price,IssuerName;
   public Stocks(){

   }
    public Stocks(String securityID, String price, String issuerName) {
        SecurityID = securityID;
        Price = price;
        IssuerName = issuerName;
    }

    public String getSecurityCode() {
        return SecurityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.SecurityCode = securityCode;
    }

    public String getSecurityID() {
        return SecurityID;
    }

    public void setSecurityID(String securityID) {
        this.SecurityID = securityID;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getIssuerName() {
        return IssuerName;
    }

    public void setIssuerName(String issuerName) {
        IssuerName = issuerName;
    }
}
