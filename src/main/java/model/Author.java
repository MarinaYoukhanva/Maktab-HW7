package model;

import java.time.LocalDate;

public class Author extends User{

    private final String nationalCode;
    private LocalDate birthday;

    private CustomList articles;

    public Author(int id, String username, String password,
                  String nationalCode, LocalDate birthday, CustomList articles) {
        super(id, username, password);
        this.nationalCode = nationalCode;
        this.birthday = birthday;
        this.articles = articles;
    }
    public Author(String username, String password, String nationalCode) {
        super(username, password);
        this.nationalCode = nationalCode;
        this.articles = new CustomList();
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public CustomList getArticles() {
        return articles;
    }

    public void setArticles(CustomList articles) {
        this.articles = articles;
    }
}
