package view;

import java.time.LocalDate;
import java.util.Scanner;

import database.Database;
import model.*;
import org.joda.time.Duration;
import service.*;

public class View {

    Scanner sc = new Scanner(System.in);
    UserService userService;
    AuthorService authorService = new AuthorService();
    ModeratorService moderatorService = new ModeratorService();
    ArticleService articleService = new ArticleService();
    CategoryService categoryService = new CategoryService();
    TagService tagService = new TagService();
    User loggedInUser;
    User signedUpUser;
    boolean isSignupSuccess;
    boolean isLoginSuccess;
    LocalDate localDate;
    int choiceForUser;
    Duration duration;

    public void mainMenu() {
        System.out.println("1.Author Menu ");
        System.out.println("2.Moderator Menu ");
        choiceForUser = sc.nextInt();
        switch (choiceForUser) {
            case 1:
                authorMenu();
                break;
            case 2:
                moderatorMenu();
                break;
        }
    }

    public void authorMenu() {
        System.out.println("1.Signup ");
        System.out.println("2.Login ");
        System.out.println("3.View published articles ");
        System.out.println("4.Back to Main Menu ");
        int choiceForAuthor = sc.nextInt();
        switch (choiceForAuthor) {
            case 1:
                isSignupSuccess = false;
                while (!isSignupSuccess) {
                    System.out.println("Enter your username : ");
                    String username = sc.next();
                    if (authorService.userHasSignedUp(username) != null)
                        System.out.println("Username already in use! choose another one: ");
                    else {
                        System.out.println("Enter your national code : ");
                        String password = sc.next();
                        signedUpUser = authorService.signUp(username, password, choiceForUser);
                        System.out.println("Enter your birthday (year, month, day) : ");
                        localDate = LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());
                        ((Author) signedUpUser).setBirthday(localDate);
                        System.out.println("Signup successful ");
                        System.out.println("Your default password is your national code. You can change it after login. ");
                        isSignupSuccess = true;
                    }
                }
                break;
            case 2:
                userService = new AuthorService();
                loggedInUser = loginMenu();
                System.out.println("Welcome dear " + loggedInUser.getUsername());
                while (Authentication.getLoggedInUser() != null) {
                    loggedInAuthorMenu();
                }
                break;
            case 3:
                System.out.println("1.view all published articles");
                System.out.println("2.filter articles by date");
                System.out.println("3.back to previous menu");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        articleService.showPublishedArticlesMainInfo();
                        articleFullInfoMenu();
                        authorMenu();
                        break;
                    case 2:
                        System.out.println("1.filter by creation date");
                        System.out.println("2.filter by last update date");
                        System.out.println("3.filter by publication date");
                        int whichDateChoice = sc.nextInt();
                        System.out.println("1.last 24 hours");
                        System.out.println("2.last week");
                        System.out.println("3.last month");
                        System.out.println("4.last six months");
                        System.out.println("5.last year");
                        int durationChoice = sc.nextInt();
                        switch (durationChoice) {
                            case 1:
                                duration = Duration.standardHours(24);
                                break;
                            case 2:
                                duration = Duration.standardDays(7);
                                break;
                            case 3:
                                duration = Duration.standardDays(30);
                                break;
                            case 4:
                                duration = Duration.standardDays(182);
                                break;
                            case 5:
                                duration = Duration.standardDays(365);
                                break;
                        }
                        switch (whichDateChoice) {
                            case 1:
                                articleService.filterByCreateDate(duration);
                                articleFullInfoMenu();
                                break;
                            case 2:
                                articleService.filterByLastUpdateDate(duration);
                                articleFullInfoMenu();
                                break;
                            case 3:
                                articleService.filterByPublishDate(duration);
                                articleFullInfoMenu();
                                break;
                        }
                    case 3:
                        authorMenu();
                        break;
                }

                break;
            case 4:
                mainMenu();
                break;
            default:
                System.out.println("not valid choice");
                break;
        }
    }

    public void moderatorMenu() {
        System.out.println("1.Signup ");
        System.out.println("2.Login ");
        System.out.println("3.Back to Main Menu: ");
        int choiceForModerator = sc.nextInt();
        switch (choiceForModerator) {
            case 1:
                isSignupSuccess = false;
                while (!isSignupSuccess) {
                    System.out.println("Enter your username : ");
                    String username = sc.next();
                    if (moderatorService.userHasSignedUp(username) != null)
                        System.out.println("Username already in use! choose another one: ");
                    else {
                        System.out.println("Enter your password : ");
                        String password = sc.next();
                        moderatorService.signUp(username, password, choiceForUser);
                        System.out.println("Signup successful ");
                        isSignupSuccess = true;
                    }
                }
                break;
            case 2:
                userService = new ModeratorService();
                loggedInUser = loginMenu();
                System.out.println("Welcome dear " + loggedInUser.getUsername());
                while (Authentication.getLoggedInUser() != null) {
                    loggedInModeratorMenu();
                }
                break;
            case 3:
                mainMenu();
            default:
                System.out.println("not valid choice");
                break;
        }
    }

    public User loginMenu() {
        isLoginSuccess = false;
        while (!isLoginSuccess) {
            System.out.println("Enter your username : ");
            String username = sc.next();
            System.out.println("Enter your password : ");
            String password = sc.next();
            if (userService.userHasSignedUp(username) != null) {
                if (userService.isPasswordCorrectForUsername(username, password) != null) {
                    loggedInUser = userService.userLogin(username, password);
                    isLoginSuccess = true;
                    break;
                }
            }
            System.out.println("invalid username or password ");
        }
        return loggedInUser;
    }

    public void loggedInAuthorMenu() {
        System.out.println("1.View My Articles ");
        System.out.println("2.Edit Articles and Publication Status ");
        System.out.println("3.Create New Article ");
        System.out.println("4.Change Password ");
        System.out.println("5.Logout ");
        int choiceForLoggedInAuthor = sc.nextInt();
        switch (choiceForLoggedInAuthor) {
            case 1:
                authorService.viewMyArticles(((Author) loggedInUser));
                break;
            case 2:
                editingArticleMenu();
                break;
            case 3:
                int choiceForCategory = choosingCategoryMenu();
                Article article = creatingArticleMenu(choiceForCategory);
                addingTagMenu(article);
                break;
            case 4:
                System.out.println("old password: ");
                String oldPassword = sc.next();
                System.out.println("new password: ");
                String newPassword = sc.next();
                if (authorService.changePassword(((Author) loggedInUser),
                        oldPassword, newPassword))
                    System.out.println("Password changed successfully ");
                else
                    System.out.println("Password change failed");
                break;
            case 5:
                Authentication.logout();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public int choosingCategoryMenu() {
        System.out.println("Choose category(id): ");
        categoryService.showCategories();
        int newCategoryChoice = Database.getAllCategories().size() + 1;
        System.out.println("press <" + newCategoryChoice + "> to Create a new category");
        int choiceForCategory = sc.nextInt();
        if (choiceForCategory == newCategoryChoice) {
            System.out.println("Title: ");
            String title = sc.next();
            System.out.println("Description: ");
            String description = sc.next();
            categoryService.addCategoryToDatabase(title, description);
            System.out.println("Category added successfully ");
        }
        return choiceForCategory;
    }

    public Article creatingArticleMenu(int choiceForCategory) {
        System.out.println("Creating new article... ");
        System.out.println("title: ");
        String title = sc.next();
        System.out.println("brief: ");
        String brief = sc.next();
        System.out.println("content: ");
        String content = sc.next();
        return authorService.createNewArticle(((Author) loggedInUser),
                title, brief, content, choiceForCategory);
    }

    public void addingTagMenu(Article article) {
        String answer;
        do {
            System.out.println("Choose tag(id): ");
            tagService.showTags();
            int newTagChoice = Database.getAllTags().size() + 1;
            System.out.println("press <" + newTagChoice + "> to creat a new tag");
            int choiceForTag = sc.nextInt();
            if (choiceForTag == newTagChoice) {
                System.out.println("Title: ");
                String tagTitle = sc.next();
                tagService.addTagToDatabase(tagTitle);
            }
            articleService.addTagForArticle(article, choiceForTag);
            System.out.println("tag added successfully. need more tags?(y/n) ");
            answer = sc.next();
        } while (answer.equalsIgnoreCase("y"));

        System.out.println("Article created successfully ");
    }

    public void editingArticleMenu() {
        int articleChoice;
        System.out.println("1.Edit article ");
        System.out.println("2.Publish article ");
        System.out.println("3.Unpublish article ");
        int choiceForEditArticle = sc.nextInt();
        switch (choiceForEditArticle) {
            case 1:
                authorService.viewMyArticles(((Author) loggedInUser));
                System.out.println("which article you want to edit? ");
                articleChoice = sc.nextInt();
                System.out.println("1.write new Title, new Brief and new Content ");
                System.out.println("2.edit a specific part ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        if (authorService.updateArticle((Author) loggedInUser, articleChoice,
                                sc.next(), sc.next(), sc.next()))
                            System.out.println("Update successful ");
                        else
                            System.out.println("Article not found! ");
                        break;
                    case 2:
                        System.out.println("1.title ");
                        System.out.println("2.brief ");
                        System.out.println("3.content ");
                        System.out.println("4.add tag ");
                        int choiceForPart = sc.nextInt();
                        switch (choiceForPart) {
                            case 1:
                                System.out.println("new title: ");
                                String newTitle = sc.next();
                                authorService.findArticleByIdForAuthor(((Author) loggedInUser), articleChoice).setTitle(newTitle);
                                break;
                            case 2:
                                System.out.println("new brief: ");
                                String newBrief = sc.next();
                                authorService.findArticleByIdForAuthor(((Author) loggedInUser), articleChoice).setBrief(newBrief);
                                break;
                            case 3:
                                System.out.println("new content: ");
                                String newContent = sc.next();
                                authorService.findArticleByIdForAuthor(((Author) loggedInUser), articleChoice).setContent(newContent);
                                break;
                            case 4:
                                addingTagMenu(authorService.findArticleByIdForAuthor(((Author) loggedInUser), articleChoice));
                                break;
                        }
                        break;
                }
                break;
            case 2:
                authorService.viewMyArticles(((Author) loggedInUser));
                System.out.println("which article you want to publish? ");
                articleChoice = sc.nextInt();
                authorService.publishArticle((Author) loggedInUser, articleChoice);
                System.out.println("Your request will be checked. You can see the publication status on your profile");
                break;
            case 3:
                authorService.viewMyArticles(((Author) loggedInUser));
                System.out.println("which article you want to unpublish? ");
                articleChoice = sc.nextInt();
                if (authorService.unpublishArticle((Author) loggedInUser, articleChoice))
                    System.out.println("Unpublishing successful. ");
                else
                    System.out.println("Unpublishing failed. ");
                break;
        }
    }

    public void loggedInModeratorMenu() {
        System.out.println("1.view articles awaiting publication: ");
        System.out.println("2.logout ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                articleService.showPublishRequestedArticles();
                System.out.println("choose article for publication approval or rejection(id) ");
                int logout = Database.getPublishRequestedArticles().size() + 1;
                System.out.println(logout + ".logout ");
                int articleId = sc.nextInt();
                if (articleId == logout) {
                    Authentication.logout();
                    break;
                }
                System.out.println("1.Approve publication");
                System.out.println("2.Reject publication");
                int choiceForApproval = sc.nextInt();
                switch (choiceForApproval) {
                    case 1:
                        if (moderatorService.approvePublication(articleId))
                            System.out.println("Publication approved successfully ");
                        else
                            System.out.println("Article not found! ");
                        break;
                    case 2:
                        if (moderatorService.rejectPublication(articleId))
                            System.out.println("Publication rejected successfully ");
                        else
                            System.out.println("Article not found! ");
                        break;
                }
                break;
            case 2:
                Authentication.logout();
                break;
        }
    }

    public void articleFullInfoMenu() {
        System.out.println("1.complete information of the articles");
        System.out.println("2.back to previous menu");
        int articleChoice = sc.nextInt();
        switch (articleChoice) {
            case 1:
                System.out.println("choose article for complete information");
                int articleId = sc.nextInt();
                articleService.showPublishedArticleCompleteInfo(articleId);
                break;
            case 2:
                authorMenu();
                break;
        }
    }
}

