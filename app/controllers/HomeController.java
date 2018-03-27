package controllers;

import models.Note;
import play.mvc.*;
import services.InMemoryNoteRepository;

import javax.inject.Inject;

public class HomeController extends Controller {

    @Inject
    protected InMemoryNoteRepository noteRepository;

    public Result index() {
        return ok(views.html.index.render(noteRepository.getNotes()));
    }

}
