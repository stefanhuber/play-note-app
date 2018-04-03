package services;

import io.ebean.Ebean;
import models.Category;

import java.util.List;

public class EbeanCategoryRepository {

    public EbeanCategoryRepository() {
        if (Ebean.find(Category.class).findCount() <= 0) {
            Category category1 = new Category();
            category1.setTitle("Important");
            Ebean.save(category1);

            Category category2 = new Category();
            category2.setTitle("Shopping");
            Ebean.save(category2);

            Category category3 = new Category();
            category3.setTitle("Holiday");
            Ebean.save(category3);
        }
    }

    public List<Category> getCategories() {
        return Ebean.find(Category.class).findList();
    }

}
