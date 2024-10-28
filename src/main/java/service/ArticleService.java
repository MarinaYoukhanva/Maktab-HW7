package service;

import database.Database;
import model.Article;
import model.CustomList;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;

public class ArticleService {

    public void showPublishedArticlesMainInfo() {
        for (int i = 0; i < Database.getPublishedArticles().size(); i++) {
            System.out.println(Database.getPublishedArticles().get(i).toString());
        }
    }

    public void showPublishedArticleCompleteInfo(int articleId) {
        Article article = findArticleById(articleId, Database.getPublishedArticles());
        if (article != null)
            System.out.println(article.toString("full info"));
    }

    public void showPublishRequestedArticles() {
        for (int i = 0; i < Database.getPublishRequestedArticles().size(); i++) {
            System.out.println(((Article) Database.getPublishRequestedArticles().get(i)).toString("full info"));
        }
    }


    public static Article findArticleById(int articleId, CustomList articleList) {
        for (int i = 0; i < articleList.size(); i++) {
            Article article = (Article) articleList.get(i);
            if (article.getId() == articleId)
                return article;
        }
        return null;
    }

    public void addTagForArticle(Article article, int tagId) {
        article.getTags().add(Database.getAllTags().get(tagId - 1));
    }

    public void filterByCreateDate(Duration duration) {
        LocalDateTime filteredDate = LocalDateTime.now().minus(duration);
        for (int i = 0; i < Database.getPublishedArticles().size(); i++) {
            Article article = (Article) Database.getPublishedArticles().get(i);
            if (article.getCreateDate().isAfter(filteredDate)) {
                System.out.println(article.toString());
            }
        }
    }
    public void filterByLastUpdateDate(Duration duration) {
        LocalDateTime filteredDate = LocalDateTime.now().minus(duration);
        for (int i = 0; i < Database.getPublishedArticles().size(); i++) {
            Article article = (Article) Database.getPublishedArticles().get(i);
            if (article.getLastUpdateDate() != null) {
                if (article.getLastUpdateDate().isAfter(filteredDate)) {
                    System.out.println(article.toString());
                }
            }
        }
    }
    public void filterByPublishDate(Duration duration) {
        LocalDateTime filteredDate = LocalDateTime.now().minus(duration);
        for (int i = 0; i < Database.getPublishedArticles().size(); i++) {
            Article article = (Article) Database.getPublishedArticles().get(i);
                if (article.getPublishDate().isAfter(filteredDate)) {
                    System.out.println(article.toString());
            }
        }
    }

}
