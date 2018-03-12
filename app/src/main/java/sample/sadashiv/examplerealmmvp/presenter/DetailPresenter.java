package sample.sadashiv.examplerealmmvp.presenter;

import sample.sadashiv.examplerealmmvp.model.realm.RealmService;
import sample.sadashiv.examplerealmmvp.ui.detail.DetailView;

public class DetailPresenter extends BasePresenter<DetailView> {

    private final String mBookId;

    public DetailPresenter(final DetailView view, final RealmService realmService, final String bookId) {
        super(view, realmService);
        mBookId = bookId;
    }

    public void showBookDetails() {
        mView.showBookDetails(mRealmService.getBook(mBookId));
    }
}
