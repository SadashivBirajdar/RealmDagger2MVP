package sample.sadashiv.examplerealmmvp.presenter;

import io.realm.RealmList;
import sample.sadashiv.examplerealmmvp.model.Book;
import sample.sadashiv.examplerealmmvp.model.realm.RealmService;
import sample.sadashiv.examplerealmmvp.ui.publisher.PublisherView;

public class PublisherPresenter extends BasePresenter<PublisherView> {

    private final String mPublisher;

    public PublisherPresenter(PublisherView view, final RealmService realmService, final String publisher) {
        super(view, realmService);
        mPublisher = publisher;
    }

    public void showBooks() {
        mView.showBooks(formatBooks(mRealmService.getPublisherBooks(mPublisher)));
    }

    private RealmList<Book> formatBooks(final RealmList<Book> publisherBooks) {
        return publisherBooks;
    }
}
