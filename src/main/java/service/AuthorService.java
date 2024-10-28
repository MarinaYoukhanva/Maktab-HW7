package service;

import database.Database;
import model.*;

import org.joda.time.LocalDateTime;

public class AuthorService extends UserService{

    @Override
    public void addUserToDatabase(User user) {
        Database.getAllAuthors().add(user);
        user.setId(Database.getAllAuthors().size());
    }

    @Override
    public User userHasSignedUp(String username) {
        CustomList list = Database.getAllAuthors();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof Author) {
                    if (username.equals( ((User) list.get(i)) .getUsername())) {
                        return ((User) list.get(i));
                    }
                }
            }
        }
        return null;
    }

    public boolean changePassword (Author author, String oldPassword, String newPassword) {
        if (author.getPassword().equals(oldPassword)) {
            author.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public Article createNewArticle (Author author, String title, String brief, String content, int categoryId) {
        Article article = new Article(title, brief, content);
        article.setCategory(((Category) Database.getAllCategories().get(categoryId - 1)));
        author.getArticles().add(article);
        article.setAuthor(author);
        article.setId(Database.getAllArticlesCount());
        Database.setAllArticlesCount(Database.getAllArticlesCount() + 1);
        return article;
    }

    public void viewMyArticles (Author author) {
        for (int i = 0; i < author.getArticles().size(); i++) {
            System.out.println(((Article) author.getArticles().get(i)).toString("full info"));
        }
    }
    public void publishArticle (Author author, int articleId) {
        Article article = ArticleService.findArticleById(articleId, author.getArticles());
        Database.getPublishRequestedArticles().add(article);
        if (article != null)
            article.setStatus("Awaiting publication approval by moderator ");

    }

    public boolean unpublishArticle (Author author, int articleId) {
        Article article = ArticleService.findArticleById(articleId, author.getArticles());
        if (article != null) {
            Database.getPublishedArticles().remove(article);
            article.setStatus("Unpublished ");
            article.setPublished(false);
            article.setPublishDate(null);
            return true;
        }
        return false;
    }

    public boolean updateArticle (Author author, int articleId, String newTitle, String newBrief, String newContent){
        Article article = ArticleService.findArticleById(articleId, author.getArticles());
        if (article != null) {
            article.setTitle(newTitle);
            article.setBrief(newBrief);
            article.setContent(newContent);
            article.setLastUpdateDate(LocalDateTime.now());
            return true;
        }
        return false;
    }

}
