package database;

import model.CustomList;

public class Database {

    private static final CustomList allAuthors = new CustomList();
    private static final CustomList allModerators = new CustomList();
    private static final CustomList allCategories = new CustomList();
    private static final CustomList allTags = new CustomList();
    private static final CustomList publishedArticles = new CustomList();
    private static final CustomList publishRequestedArticles = new CustomList();
    private static int allArticlesCount = 1;


    public static CustomList getPublishedArticles() {
        return publishedArticles;
    }

    public static CustomList getAllAuthors() {
        return allAuthors;
    }

    public static CustomList getAllModerators() {
        return allModerators;
    }

    public static CustomList getAllCategories() {
        return allCategories;
    }

    public static CustomList getPublishRequestedArticles() {
        return publishRequestedArticles;
    }

    public static int getAllArticlesCount() {
        return allArticlesCount;
    }

    public static void setAllArticlesCount(int allArticlesCount) {
        Database.allArticlesCount = allArticlesCount;
    }

    public static CustomList getAllTags() {
        return allTags;
    }
}
