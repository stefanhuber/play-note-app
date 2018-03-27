package controllers;

import models.Note;
import play.mvc.*;
import services.InMemoryNoteRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class HomeController extends Controller {

    @Inject
    protected InMemoryNoteRepository noteRepository;

    public Result index() {
        List<Note> notes = noteRepository.getNotes();
        return ok(views.html.index.render(notes));
    }

    public Result form(int id) {
        return ok(views.html.form.render());
    }

}
