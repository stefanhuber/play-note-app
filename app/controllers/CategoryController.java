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

        return ok(Json.toJson(category));
    }


}
