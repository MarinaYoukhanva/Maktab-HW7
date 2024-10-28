import service.CategoryService;
import service.TagService;
import view.View;

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


        while(true) {
            view.mainMenu();
        }
    }
}