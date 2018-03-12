package sample.sadashiv.examplerealmmvp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import sample.sadashiv.examplerealmmvp.application.BooksApplication;
import sample.sadashiv.examplerealmmvp.di.component.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(BooksApplication.get(this).component());
    }

    protected abstract void setupComponent(ApplicationComponent appComponent);

    @Override
    protected void onDestroy() {
        closeRealm();
        super.onDestroy();
    }

    protected abstract void closeRealm();
}
