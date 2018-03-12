package sample.sadashiv.examplerealmmvp.di.module;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import sample.sadashiv.examplerealmmvp.model.realm.RealmService;
import sample.sadashiv.examplerealmmvp.presenter.AddBookPresenter;
import sample.sadashiv.examplerealmmvp.ui.add.AddBookView;

@Module
public class AddBooksModule {

    private final AddBookView mAddBookView;
    public AddBooksModule(AddBookView addBookView) {
        mAddBookView = addBookView;
    }

    @Provides
    AddBookPresenter provideAddBookPresenter() {
        RealmService realmService = new RealmService(Realm.getDefaultInstance());
        return new AddBookPresenter(mAddBookView, realmService);
    }
}
