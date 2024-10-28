package service;

import database.Database;
import model.Category;

public class CategoryService {

    public void addCategoryToDatabase(String title, String description) {
        Category category = new Category(title, description);
        Database.getAllCategories().add(category);
        category.setId(Database.getAllCategories().size());
    }

    public void showCategories() {
        Database.getAllCategories().print();
    }
}
