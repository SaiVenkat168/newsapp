package com.example.android.newsapp;

import java.util.ArrayList;

public class NewsModal
{
    private int totalresults;
    private String status;
    private ArrayList<Articles> articles;
    public NewsModal(int totalresults, String status, ArrayList<Articles> articles) {
        this.totalresults = totalresults;
        this.status = status;
        this.articles = articles;
    }

    public int getTotalresults() {
        return totalresults;
    }

    public void setTotalresults(int totalresults) {
        this.totalresults = totalresults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }
}
