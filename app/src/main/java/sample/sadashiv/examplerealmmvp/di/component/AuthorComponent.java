package sample.sadashiv.examplerealmmvp.di.component;


import dagger.Component;
import sample.sadashiv.examplerealmmvp.di.module.AuthorModule;
import sample.sadashiv.examplerealmmvp.ui.author.AuthorActivity;


/**
 * Created by emb-sadabir on 05/03/18.
 */

@Component(dependencies = ApplicationComponent.class, modules = AuthorModule.class)
public interface AuthorComponent {
    void inject(AuthorActivity authorActivity);
}
