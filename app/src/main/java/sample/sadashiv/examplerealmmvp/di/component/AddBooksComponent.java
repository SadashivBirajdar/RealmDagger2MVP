package sample.sadashiv.examplerealmmvp.di.component;


import dagger.Component;
import sample.sadashiv.examplerealmmvp.di.module.AddBooksModule;
import sample.sadashiv.examplerealmmvp.ui.add.AddBookActivity;


/**
 * Created by janisharali on 08/12/16.
 */

@Component(dependencies = ApplicationComponent.class, modules = AddBooksModule.class)
public interface AddBooksComponent {
    void inject(AddBookActivity addBookActivity);
}
