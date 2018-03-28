package controllers;

import models.Note;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import services.InMemoryNoteRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class HomeController extends Controller {

    @Inject
    protected InMemoryNoteRepository noteRepository;

    protected Form<Note> noteForm;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.noteForm = formFactory.form(Note.class);
    }

    public Result index() {
        List<Note> notes = noteRepository.getNotes();
        return ok(views.html.index.render(notes));
    }

    public Result form(int id) {
        Note note = new Note();

        if (id > 0) {
            note = noteRepository.getNote(id);
        }

        return ok(views.html.form.render(noteForm.fill(note)));
    }

    public Result save() {
        Form<Note> form = noteForm.bindFromRequest();


        if(form.hasErrors()){
            return badRequest(views.html.form.render(form));
        }else{
            noteRepository.saveNote(form.get());
            return redirect("/");
        }


    }

    public Result delete(int id) {
        noteRepository.delete(id);
        return ok();
    }

}
