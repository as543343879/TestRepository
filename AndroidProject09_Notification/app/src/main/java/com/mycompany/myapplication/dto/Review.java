package com.mycompany.myapplication.dto;


import java.io.Serializable;
//인텐트로 해당 클래스를 넘겨 주기 위해 serializable을 구현함
public class Review implements Serializable{
    private int photo; // res에 drawable의 이미지는 번호로 관리함
    private String title;
    private int star;
    private String content;

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
