package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

    private int id;
    private String title;
    private String description;

    public Category(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id= " + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
