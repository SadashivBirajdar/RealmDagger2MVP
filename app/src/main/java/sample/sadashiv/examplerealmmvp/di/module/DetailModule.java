package sample.sadashiv.examplerealmmvp.di.module;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import sample.sadashiv.examplerealmmvp.model.realm.RealmService;
import sample.sadashiv.examplerealmmvp.presenter.DetailPresenter;
import sample.sadashiv.examplerealmmvp.ui.detail.DetailView;

@Module
public class DetailModule {

    private final String mBookId;
    private final DetailView mView;

    public DetailModule(DetailView view, final String bookId) {
        mBookId = bookId;
        mView = view;
    }

    @Provides
    DetailPresenter provideMyDetailPresenter() {
        RealmService realmService = new RealmService(Realm.getDefaultInstance());
        return new DetailPresenter(mView, realmService, mBookId);
    }
}
