package sample.sadashiv.examplerealmmvp.ui.publisher;

import io.realm.RealmList;
import sample.sadashiv.examplerealmmvp.model.Book;
import sample.sadashiv.examplerealmmvp.ui.BaseView;

public interface PublisherView extends BaseView{

    void showBooks(RealmList<Book> books);
}
