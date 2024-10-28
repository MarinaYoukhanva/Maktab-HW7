import model.*;
import service.*;
import view.View;

import org.joda.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        View view = new View();

        CategoryService categoryService = new CategoryService();
        categoryService.addCategoryToDatabase("Medicine", "about medicine");
        categoryService.addCategoryToDatabase("History", "about historical places");
        categoryService.addCategoryToDatabase("Sport", "about sport");
        categoryService.addCategoryToDatabase("Economy", "about economy ");
        categoryService.addCategoryToDatabase("Social", "about social issues");
        categoryService.addCategoryToDatabase("science", "about science");

        TagService tagService = new TagService();
        tagService.addTagToDatabase("health");
        tagService.addTagToDatabase("anatomy");
        tagService.addTagToDatabase("museum");
        tagService.addTagToDatabase("technology");
        tagService.addTagToDatabase("football");
        tagService.addTagToDatabase("workout");
        tagService.addTagToDatabase("finance");
        tagService.addTagToDatabase("money");

        ModeratorService moderatorService = new ModeratorService();
        Moderator defaultModerator = ((Moderator) moderatorService.signUp("admin(M)", "1", 2));
        AuthorService authorService = new AuthorService();
        Author defaultAuthor = ((Author) authorService.signUp("admin(A)", "1", 1));

        Article a = authorService.createNewArticle(defaultAuthor, "Vitamins", "about group B vitamins", "describes group B vitamins", 1);
        Article b = authorService.createNewArticle(defaultAuthor, "Sports Injuries", "about Sports Injuries", "describes Sports Injuries", 1);
        Article c = authorService.createNewArticle(defaultAuthor, "Artificial Intelligence", "about AI", "describes AI", 6);
        authorService.publishArticle(defaultAuthor,1);
        authorService.publishArticle(defaultAuthor,2);
        authorService.publishArticle(defaultAuthor,3);
        moderatorService.approvePublication(1);
        moderatorService.approvePublication(2);
        moderatorService.approvePublication(3);

        a.setCreateDate(new LocalDateTime(2023, 8, 20, 18, 30));
        b.setCreateDate(new LocalDateTime(2023, 12, 20, 18, 30));
        c.setCreateDate(new LocalDateTime(2024, 10, 25, 18, 30));

        a.setPublishDate(new LocalDateTime(2023, 9, 20, 18, 30));
        b.setPublishDate(new LocalDateTime(2024, 5, 20, 18, 30));
        c.setPublishDate(new LocalDateTime(2024, 10, 27, 18, 30));

        a.setLastUpdateDate(new LocalDateTime(2023, 11, 20, 18, 30));
        b.setLastUpdateDate(new LocalDateTime(2024, 10, 20, 18, 30));
        c.setLastUpdateDate(new LocalDateTime(2024, 10, 27, 22, 30));


        while(true) {
            view.mainMenu();
        }
    }
}