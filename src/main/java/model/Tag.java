package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tag {

    private int id;
    private String title;

    public Tag(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
