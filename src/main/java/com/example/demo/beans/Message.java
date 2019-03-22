package com.example.demo;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 3)
    private String title;

    @NotNull
    @Size(min = 10)
    private String content;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-dd-YY  hh:mm")
    private Date postedDate;

    @NotNull
    @Size(min = 2)
    private String postedBy;

    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }



    public Message(){

    }

    public Message(
            @NotNull @Size(min = 3)
                    String title,
            @NotNull @Size(min = 10) String content,
            @NotNull @Size(min = 2) String postedBy)
    {
        this.title = title;
        this.content = content;
        this.postedBy = postedBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
}

