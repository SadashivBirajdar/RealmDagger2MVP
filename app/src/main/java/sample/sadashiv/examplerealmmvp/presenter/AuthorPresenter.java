package sample.sadashiv.examplerealmmvp.presenter;

import io.realm.RealmList;
import sample.sadashiv.examplerealmmvp.model.Book;
import sample.sadashiv.examplerealmmvp.model.realm.RealmService;
import sample.sadashiv.examplerealmmvp.ui.author.AuthorView;

public class AuthorPresenter extends BasePresenter<AuthorView> {

    private final String mAuthor;

    public AuthorPresenter(final AuthorView view, final RealmService realmService, final String author) {
        super(view, realmService);
        mAuthor = author;
    }

    public void showBooks() {
        mView.showBooks(formatBooks(mRealmService.getAuthorBooks(mAuthor)));
    }

    private RealmList<Book> formatBooks(final RealmList<Book> publisherBooks) {
        return publisherBooks;
    }
}
