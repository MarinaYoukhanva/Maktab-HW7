package model;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDateTime;

@Getter
@Setter
public class Article {

    private int id;
    private String title;
    private String brief;
    private String content;
    private LocalDateTime createDate;
    private boolean isPublished;
    private LocalDateTime lastUpdateDate;
    private LocalDateTime publishDate;
    private String status;

    private boolean isUpdated;
    private Author author;
    private Category category;
    private CustomList tags;

    public Article(String title, String brief, String content) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.isPublished = false;
        this.lastUpdateDate = null;
        this.publishDate = null;
        this.status = "Not Published ";
        this.tags = new CustomList();
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", author id=" + author.getId() +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                '}';
    }

    public String toString(String description) {
        return "Article{" +
                "id=" + id +
                ", author id=" + author.getId() +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", isPublished=" + isPublished +
                ", lastUpdateDate=" + lastUpdateDate +
                ", publishDate=" + publishDate +
                ", status='" + status + '\'' +
                ", category=" + category +
                ", tags=" + tags.print() +
                '}';
    }


}
