package sample.sadashiv.examplerealmmvp.ui.detail;

import sample.sadashiv.examplerealmmvp.model.Book;
import sample.sadashiv.examplerealmmvp.ui.BaseView;

public interface DetailView extends BaseView {

    void showBookDetails(Book book);
}
