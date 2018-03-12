package sample.sadashiv.examplerealmmvp.di.component;


import dagger.Component;
import sample.sadashiv.examplerealmmvp.di.module.PublisherModule;
import sample.sadashiv.examplerealmmvp.ui.publisher.PublisherActivity;


/**
 * Created by emb-sadabir on 05/03/18.
 */

@Component(dependencies = ApplicationComponent.class, modules = PublisherModule.class)
public interface PublisherComponent {
    void inject(PublisherActivity publisherActivity);
}
