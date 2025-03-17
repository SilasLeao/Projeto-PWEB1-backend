package com.example.jponline_backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "imgurl")
    private String imgUrl;
    private String title;
    private String time;
    private String info;

    @Column(name = "hiddentext")
    private String hiddenText;

    private int likes;
    private int dislikes;

    private boolean expanded;

    // Construtores
    public Post() {}

    public Post(String imgUrl, String title, String time, String info, String hiddenText, int likes, int dislikes, boolean expanded) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.time = time;
        this.info = info;
        this.hiddenText = hiddenText;
        this.likes = likes;
        this.dislikes = dislikes;
        this.expanded = expanded;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHiddenText() {
        return hiddenText;
    }

    public void setHiddenText(String hiddenText) {
        this.hiddenText = hiddenText;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}