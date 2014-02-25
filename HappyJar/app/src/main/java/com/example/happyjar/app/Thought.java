package com.example.happyjar.app;

import java.util.Date;

/**
 * Created by Marty.Loewenthal on 2/25/14.
 */
public class Thought
{
    private String text;
    private String date;
    private int read;

    public Thought(String text)
    {
        this.text = text;
        this.date = new Date().toString();
        this.read = 0;
    }

    public Thought(String text, String date)
    {
        this.text = text;
        this.date = date;
        this.read = 0;
    }

    public String getText()
    {
        return this.text;
    }

    public String getDate()
    {
        return this.date;
    }

    public void incrementCount()
    {
        this.read++;
    }

    public int getReads()
    {
        return this.read;
    }
}