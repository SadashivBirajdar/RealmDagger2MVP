package sample.sadashiv.examplerealmmvp.di.module;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import sample.sadashiv.examplerealmmvp.model.realm.RealmService;
import sample.sadashiv.examplerealmmvp.presenter.AuthorPresenter;
import sample.sadashiv.examplerealmmvp.ui.author.AuthorView;

@Module
public class AuthorModule {

    private final String mAuthor;
    private final AuthorView mAuthorView;

    public AuthorModule(final String author, AuthorView view) {
        mAuthor = author;
        mAuthorView = view;
    }

    @Provides
    AuthorPresenter provideAuthorPresenter() {
        RealmService realmService = new RealmService(Realm.getDefaultInstance());
        return new AuthorPresenter(mAuthorView, realmService, mAuthor);
    }
}
