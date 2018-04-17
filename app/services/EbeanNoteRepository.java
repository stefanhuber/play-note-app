package services;

import io.ebean.Ebean;
import models.Note;
import models.User;

import javax.inject.Inject;
import java.util.List;

public class EbeanNoteRepository {

    @Inject
    protected EbeanUserRepository userRepository;

    public List<Note> getNotesByUser() {
        User user = userRepository.getCurrentUser();

        if (user.isAdmin()) {
            return getNotes();
        }

        return Ebean.find(Note.class)
                .where()
                .eq("user_id", user.getId())
                .findList();
    }

    public List<Note> getNotes() {
        return Ebean.find(Note.class).findList();
    }

    public Note getNote(int id) {
        return Ebean.find(Note.class)
                    .where()
                    .eq("id", id)
                    .findOne();
    }

    public void saveNote(Note note) {
        note.setLastEdited( (int) (System.currentTimeMillis() / 1000L));

        if (note.getId() > 0) {
            Ebean.update(note);
        } else {
            User user = userRepository.getCurrentUser();
            note.setUser(user);
            Ebean.save(note);
        }
    }

    public void deleteNote(int id) {
        Ebean.delete(Note.class, id);
    }

}
