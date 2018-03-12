package sample.sadashiv.examplerealmmvp.di.module;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import sample.sadashiv.examplerealmmvp.model.realm.RealmService;
import sample.sadashiv.examplerealmmvp.presenter.BooksPresenter;
import sample.sadashiv.examplerealmmvp.ui.books.BooksView;

@Module
public class BooksModule {

    private final BooksView booksView;

    public BooksModule(BooksView booksView) {
        this.booksView = booksView;
    }

    @Provides
    BooksPresenter provideMyListPresenter() {
        RealmService realmService = new RealmService(Realm.getDefaultInstance());
        return new BooksPresenter(booksView, realmService);
    }
}
