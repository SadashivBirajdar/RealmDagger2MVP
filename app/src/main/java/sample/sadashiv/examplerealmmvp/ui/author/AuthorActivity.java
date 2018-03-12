package sample.sadashiv.examplerealmmvp.ui.author;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import sample.sadashiv.examplerealmmvp.R;
import sample.sadashiv.examplerealmmvp.di.component.ApplicationComponent;
import sample.sadashiv.examplerealmmvp.di.component.DaggerAuthorComponent;
import sample.sadashiv.examplerealmmvp.di.module.AuthorModule;
import sample.sadashiv.examplerealmmvp.model.Book;
import sample.sadashiv.examplerealmmvp.presenter.AuthorPresenter;
import sample.sadashiv.examplerealmmvp.ui.BaseActivity;
import sample.sadashiv.examplerealmmvp.ui.adapter.BookGridAdapter;

public class AuthorActivity extends BaseActivity implements AuthorView {

    private static final String EXTRA_AUTHOR = "EXTRA_AUTHOR";

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @Inject
    AuthorPresenter mAuthorPresenter;
    private BookGridAdapter mAdapter;

    public static Intent getStartIntent(final Context context, final String author) {
        Intent intent = new Intent(context, AuthorActivity.class);
        intent.putExtra(EXTRA_AUTHOR, author);
        return intent;
    }

    @Override
    protected void setupComponent(ApplicationComponent appComponent) {
        DaggerAuthorComponent.builder()
                .applicationComponent(appComponent)
                .authorModule(getModule())
                .build()
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        ButterKnife.bind(this);
        initList();
    }

    @Override
    public AuthorModule getModule() {
        return new AuthorModule(getIntent().getExtras().getString(EXTRA_AUTHOR), this);
    }

    private void initList() {
        mAdapter = new BookGridAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuthorPresenter.showBooks();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuthorPresenter.clearView();
    }

    @Override
    protected void closeRealm() {
        mAuthorPresenter.closeRealm();
    }

    @Override
    public void showBooks(final RealmList<Book> books) {
        mAdapter.setBooks(books);
    }
}
