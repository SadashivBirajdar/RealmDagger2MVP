package sample.sadashiv.examplerealmmvp.di.component;


import dagger.Component;
import sample.sadashiv.examplerealmmvp.di.module.DetailModule;
import sample.sadashiv.examplerealmmvp.ui.detail.DetailActivity;


/**
 * Created by emb-sadabir on 05/03/18.
 */

@Component(dependencies = ApplicationComponent.class, modules = DetailModule.class)
public interface DetailComponent {
    void inject(DetailActivity detailActivity);
}
