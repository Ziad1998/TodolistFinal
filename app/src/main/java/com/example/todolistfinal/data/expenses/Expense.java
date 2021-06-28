

package com.example.todolistfinal.data.expenses;

import java.io.Serializable;

public class Expense implements Serializable
{
    private String item;
    private String price;
    private String date;
    private String uid;
    private String note;

    public Expense()
    {
        item = "";
        price = "";
        note = "";
    }

    public Expense( String i, String d, String u )
    {
        this.item = i;
        this.date = d;
        this.uid = u;
    }

    public Expense( String i, String d, String u, String p, String q )
    {
        this.item = i;
        this.date = d;
        this.uid = u;
        this.price = p;
        this.note = q;
    }



    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String category) {
        this.date = category;
    }

    public String getUid() {
        return uid;
    }
}
