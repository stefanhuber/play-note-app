package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Category;
import play.libs.Json;
import play.mvc.BodyParser;
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

    @BodyParser.Of(BodyParser.Json.class)
    public Result save(int id) {
        JsonNode json = request().body().asJson();

        if (json.findPath("title").textValue() == null ||
                json.findPath("title").textValue().isEmpty()) {
            return badRequest("a title is required for categories");
        }

        if ( id > 0 && categoryRepository.getCategory(id) == null) {
            return badRequest("category does not exist");
        }

        Category category = Json.fromJson(json, Category.class);
        category.setId(id);
        categoryRepository.saveCategory(category);

        return ok(Json.toJson(category));

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
