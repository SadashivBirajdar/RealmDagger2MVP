package sample.sadashiv.examplerealmmvp.di.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import sample.sadashiv.examplerealmmvp.application.BooksApplication;

@Module
public class ApplicationModule {

    private final BooksApplication booksApplication;

    public ApplicationModule(BooksApplication booksApplication) {
        this.booksApplication = booksApplication;
    }

    @Provides
    public Application provideApplication() {
        return booksApplication;
    }
}
