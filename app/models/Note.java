package models;

public class Note {

    protected int id;
    protected String title;
    protected String description;
    protected int lastEdited;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(int lastEdited) {
        this.lastEdited = lastEdited;
    }
}
