package service;

import database.Database;
import model.Tag;

public class TagService {

    public void addTagToDatabase(String title) {
        Tag tag = new Tag(title);
        Database.getAllTags().add(tag);
        tag.setId(Database.getAllTags().size());
    }

    public void showTags (){
        Database.getAllTags().print();
    }
}
