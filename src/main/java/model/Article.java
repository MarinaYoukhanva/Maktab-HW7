package model;

import java.time.*;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setPublished(boolean published) {
        isPublished = published;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CustomList getTags() {
        return tags;
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
