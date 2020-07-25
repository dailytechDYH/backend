package me.eltacshikhsaidov.dailytech.entities;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
//import javax.validation.constraints.NotNull;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String date;

    @Size(max = 80)
    @NotEmpty
    private String title;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "USER_EMAIL")
    private User user;

    public Blog() {
    }

    public Blog(String date, String title, String description) {
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public Blog(String date, String title, String description, User user) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    // ----------------- Getting a few words from description ---------------

    public String getSentence() {
        return this.description.substring(0, 20) + "...";
    }

    public String getThePartOfTitle() {
        if(this.title.length() > 10)
            return this.title.substring(0, 9) + "...";
        else return this.title;
    }

    //-----------------------------------------------------------------------

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}