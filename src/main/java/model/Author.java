package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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

}
