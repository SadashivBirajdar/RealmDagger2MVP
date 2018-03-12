package sample.sadashiv.examplerealmmvp.ui.author;

import io.realm.RealmList;
import sample.sadashiv.examplerealmmvp.model.Book;
import sample.sadashiv.examplerealmmvp.ui.BaseView;

public interface AuthorView extends BaseView{

    void showBooks(RealmList<Book> books);
}
