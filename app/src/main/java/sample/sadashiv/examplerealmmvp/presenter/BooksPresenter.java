package sample.sadashiv.examplerealmmvp.presenter;

import sample.sadashiv.examplerealmmvp.model.realm.RealmService;
import sample.sadashiv.examplerealmmvp.ui.books.BooksView;

public class BooksPresenter extends BasePresenter<BooksView> {

    private boolean booksWereShown = false;

    public BooksPresenter(final BooksView booksView, final RealmService realmService) {
        super(booksView, realmService);
    }

    public void showBooks() {
        if(!booksWereShown) {
            mView.showBooks(mRealmService.getAllBooks());
            booksWereShown = true;
        }
    }
}
