package sample.sadashiv.examplerealmmvp.application;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import sample.sadashiv.examplerealmmvp.di.component.ApplicationComponent;
import sample.sadashiv.examplerealmmvp.di.component.DaggerApplicationComponent;
import sample.sadashiv.examplerealmmvp.di.module.ApplicationModule;

public class BooksApplication extends Application {

    private static BooksApplication sInstance;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initRealmConfiguration();
        initDagger();
    }

    public static BooksApplication get(Context context) {
        return (BooksApplication) context.getApplicationContext();
    }

    public static BooksApplication getInstance() {
        return sInstance;
    }

    private void initRealmConfiguration() {

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name(
                "sample.realm").schemaVersion(0).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initDagger() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(sInstance))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent component() {
        return mApplicationComponent;
    }
}
