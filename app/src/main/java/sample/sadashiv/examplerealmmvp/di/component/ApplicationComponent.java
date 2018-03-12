package sample.sadashiv.examplerealmmvp.di.component;


import dagger.Component;
import sample.sadashiv.examplerealmmvp.application.BooksApplication;
import sample.sadashiv.examplerealmmvp.di.module.ApplicationModule;


/**
 * Created by janisharali on 08/12/16.
 */

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BooksApplication booksApplication);
}
