package com.example.myapplication;

public class recycler_search {
    private String Issuer;
    private String Security_Name;

    public Double getPrize() {
        return prize;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }

    private Double prize;

    public recycler_search() {
    }

    public recycler_search(String issuer, String security_Name,Double Prize) {
        Issuer = issuer;
        Security_Name = security_Name;
        prize = Prize;

    }

    public String getIssuer() {
        return Issuer;
    }

    public void setIssuer(String issuer) {
        Issuer = issuer;
    }

    public String getSecurity_Name() {
        return Security_Name;
    }

    public void setSecurity_Name(String security_Name) {
        Security_Name = security_Name;
    }
}
