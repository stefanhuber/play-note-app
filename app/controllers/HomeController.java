package controllers;

import models.Note;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import services.InMemoryNoteRepository;

import javax.inject.Inject;
import java.util.List;

public class HomeController extends Controller {

    protected InMemoryNoteRepository noteRepository;

    protected Form<Note> noteForm;



    @Inject
    public HomeController(InMemoryNoteRepository noteRepository, FormFactory formFactory) {
        this.noteRepository = noteRepository;
        this.noteForm = formFactory.form(Note.class);
    }

    public Result index() {
        List<Note> notes = noteRepository.getNotes();
        return ok(views.html.index.render(notes));
    }

    public Result form(int id) {
        return ok(views.html.form.render());
    }

    public Result save() {
        Note newNote = noteForm.bindFromRequest().get();
        noteRepository.saveNote(newNote);

        return redirect( "/");
    }

}
