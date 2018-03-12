package sample.sadashiv.examplerealmmvp.di.component;


import dagger.Component;
import sample.sadashiv.examplerealmmvp.di.module.BooksModule;
import sample.sadashiv.examplerealmmvp.ui.books.BooksActivity;


/**
 * Created by emb-sadabir on 05/03/18.
 */

@Component(dependencies = ApplicationComponent.class, modules = BooksModule.class)
public interface BooksComponent {
    void inject(BooksActivity booksActivity);
}
