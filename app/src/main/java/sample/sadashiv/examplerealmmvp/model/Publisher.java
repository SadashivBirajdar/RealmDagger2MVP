package sample.sadashiv.examplerealmmvp.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Publisher extends RealmObject {

    @PrimaryKey
    private String id;

    private String name;
    private RealmList<Book> books;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public RealmList<Book> getBooks() {
        return books;
    }

    public void setBooks(final RealmList<Book> books) {
        this.books = books;
    }
}
