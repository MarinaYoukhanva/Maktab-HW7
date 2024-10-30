package database;

import lombok.Getter;
import model.CustomList;

public class Database {

    @Getter
    private static final CustomList allAuthors = new CustomList();
    @Getter
    private static final CustomList allModerators = new CustomList();
    @Getter
    private static final CustomList allCategories = new CustomList();
    @Getter
    private static final CustomList allTags = new CustomList();
    @Getter
    private static final CustomList publishedArticles = new CustomList();
    @Getter
    private static final CustomList publishRequestedArticles = new CustomList();
    @Getter
    private static int allArticlesCount = 1;


    public static void setAllArticlesCount(int allArticlesCount) {
        Database.allArticlesCount = allArticlesCount;
    }

}
