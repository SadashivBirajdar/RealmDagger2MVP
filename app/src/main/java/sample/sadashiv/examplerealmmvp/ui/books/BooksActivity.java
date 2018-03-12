package sample.sadashiv.examplerealmmvp.ui.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;
import sample.sadashiv.examplerealmmvp.R;
import sample.sadashiv.examplerealmmvp.di.component.ApplicationComponent;
import sample.sadashiv.examplerealmmvp.di.component.DaggerBooksComponent;
import sample.sadashiv.examplerealmmvp.di.module.BooksModule;
import sample.sadashiv.examplerealmmvp.model.Book;
import sample.sadashiv.examplerealmmvp.presenter.BooksPresenter;
import sample.sadashiv.examplerealmmvp.ui.BaseActivity;
import sample.sadashiv.examplerealmmvp.ui.adapter.BookListAdapter;
import sample.sadashiv.examplerealmmvp.ui.add.AddBookActivity;
import sample.sadashiv.examplerealmmvp.ui.detail.DetailActivity;

public class BooksActivity extends BaseActivity implements BooksView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    BooksPresenter mBooksPresenter;
    private BookListAdapter mAdapter;

    @Override
    protected void setupComponent(ApplicationComponent appComponent) {
        DaggerBooksComponent.builder()
                .applicationComponent(appComponent)
                .booksModule(getModule())
                .build()
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBooksPresenter.showBooks();
    }

    @Override
    public BooksModule getModule() {
        return new BooksModule(this);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void initList() {
        mAdapter = new BookListAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBooksPresenter.clearView();
    }

    @Override
    protected void closeRealm() {
        mBooksPresenter.closeRealm();
    }

    @Override
    public void showBooks(final RealmResults<Book> books) {
        mAdapter.setBooks(books);
    }

    @Override
    public void onBookClick(final String id) {
        startActivity(DetailActivity.getStartIntent(this, id));
    }

    @OnClick(R.id.fab)
    public void onAddNewBookClick() {
        startActivity(new Intent(this, AddBookActivity.class));
    }
}
