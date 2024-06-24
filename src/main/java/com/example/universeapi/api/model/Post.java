package com.example.universeapi.api.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postID;

    @Column(name = "posting_date")
    private Date postingDate;

    @Column(name = "content")
    private String content;

    @Column(name = "userID")
    private int userID;

    public Post(){}


    public Post(int userID, String content){
        this.userID = userID;
        this.content = content;
        this.postingDate = getCurrentDate();
    }


    public int getPostID() {
        return postID;
    }

    public int getUserID() {
        return userID;
    }

    public String getContent() {
        return content;
    }

    public Date getPostingDate() {
        return postingDate;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    private static Date getCurrentDate() {
        long currentTimeMillis = System.currentTimeMillis();

        return new Date(currentTimeMillis);
    }
}
