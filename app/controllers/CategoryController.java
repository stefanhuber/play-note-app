package controllers;

import models.Category;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.EbeanCategoryRepository;

import javax.inject.Inject;

public class CategoryController extends Controller {

    @Inject
    protected EbeanCategoryRepository categoryRepository;

    public Result get(int id) {
        Category category = categoryRepository.getCategory(id);

        if(category == null) {
            return badRequest("Category not found.");
        } else {
            return ok(Json.toJson(category));
        }
    }

    public Result delete(int id){
        try {
            categoryRepository.delete(id);
            return ok();

        } catch (Exception ex) {

            return badRequest("Cannot delete Category, because it is linked to a note!");
        }

    }


    public Result list() {
        return ok(Json.toJson(categoryRepository.getCategories()));
    }


}
