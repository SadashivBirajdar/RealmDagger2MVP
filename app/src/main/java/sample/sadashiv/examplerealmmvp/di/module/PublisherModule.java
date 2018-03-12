package sample.sadashiv.examplerealmmvp.di.module;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import sample.sadashiv.examplerealmmvp.model.realm.RealmService;
import sample.sadashiv.examplerealmmvp.presenter.PublisherPresenter;
import sample.sadashiv.examplerealmmvp.ui.publisher.PublisherView;

@Module
public class PublisherModule {

    private final String mPublisher;
    private final PublisherView mView;

    public PublisherModule(PublisherView view, final String publisher) {
        mPublisher = publisher;
        mView = view;
    }

    @Provides
    PublisherPresenter providePublisherPresenter() {
        RealmService realmService = new RealmService(Realm.getDefaultInstance());
        return new PublisherPresenter(mView, realmService, mPublisher);
    }
}
