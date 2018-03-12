package sample.sadashiv.examplerealmmvp.ui.books;

import io.realm.RealmResults;
import sample.sadashiv.examplerealmmvp.model.Book;
import sample.sadashiv.examplerealmmvp.ui.BaseView;

public interface BooksView extends BaseView{

    void showBooks(RealmResults<Book> books);

    void onBookClick(String id);
}
