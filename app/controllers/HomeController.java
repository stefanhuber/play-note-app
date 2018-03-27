package controllers;

import models.Note;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;

public class HomeController extends Controller {

    public Result index() {
        List<Note> notes = new ArrayList<>();

        Note note1 = new Note();
        note1.setId(1);
        note1.setTitle("erste Notiz");
        note1.setLastEdited(1521364620);
        notes.add(note1);

        Note note2 = new Note();
        note2.setId(2);
        note2.setTitle("zweite Notiz");
        note2.setLastEdited(1523434663);
        notes.add(note2);

        return ok(views.html.index.render(notes));
    }

    public Result form(int id) {
        return ok(views.html.form.render());
    }

}
