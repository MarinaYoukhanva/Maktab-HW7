package service;

import database.Database;
import model.*;

import org.joda.time.LocalDateTime;

public class ModeratorService extends UserService{


    @Override
    public void addUserToDatabase(User user) {
        Database.getAllModerators().add(user);
        user.setId(Database.getAllModerators().size());
    }

    @Override
    public User userHasSignedUp(String username) {
        CustomList list = Database.getAllModerators();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof Moderator) {
                    if (username.equals( ((User) list.get(i)) .getUsername())) {
                        return ((User) list.get(i));
                    }
                }
            }
        }
        return null;
    }
    public boolean approvePublication (int articleId) {
        Article article = ArticleService.findArticleById(articleId, Database.getPublishRequestedArticles());
        if (article != null) {
            Database.getPublishedArticles().add(article);
            article.setStatus("Published ");
            article.setPublished(true);
            article.setPublishDate(LocalDateTime.now());
            Database.getPublishRequestedArticles().remove(article);
            return true;
        }
        return false;
    }

    public boolean rejectPublication (int articleId) {
        Article article = ArticleService.findArticleById(articleId, Database.getPublishRequestedArticles());
        if (article != null) {
            article.setStatus("Rejected ");
            Database.getPublishRequestedArticles().remove(article);
            return true;
        }
       return false;
    }
}
