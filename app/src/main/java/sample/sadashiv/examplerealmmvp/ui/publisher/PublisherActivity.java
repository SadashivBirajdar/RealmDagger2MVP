package sample.sadashiv.examplerealmmvp.ui.publisher;

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
import sample.sadashiv.examplerealmmvp.di.component.DaggerPublisherComponent;
import sample.sadashiv.examplerealmmvp.di.module.PublisherModule;
import sample.sadashiv.examplerealmmvp.model.Book;
import sample.sadashiv.examplerealmmvp.presenter.PublisherPresenter;
import sample.sadashiv.examplerealmmvp.ui.BaseActivity;
import sample.sadashiv.examplerealmmvp.ui.adapter.BookGridAdapter;

public class PublisherActivity extends BaseActivity implements PublisherView {

    private static final String EXTRA_PUBLISHER = "EXTRA_PUBLISHER";

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    @Inject
    PublisherPresenter mPublisherPresenter;
    private BookGridAdapter mAdapter;

    public static Intent getStartIntent(final Context context, final String publisher) {
        Intent intent = new Intent(context, PublisherActivity.class);
        intent.putExtra(EXTRA_PUBLISHER, publisher);
        return intent;
    }

    @Override
    protected void setupComponent(ApplicationComponent appComponent) {
        DaggerPublisherComponent.builder()
                .applicationComponent(appComponent)
                .publisherModule(getModule())
                .build()
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher);
        ButterKnife.bind(this);
        initList();
    }

    @Override
    public PublisherModule getModule() {
        return new PublisherModule(this, getIntent().getExtras().getString(EXTRA_PUBLISHER));
    }

    private void initList() {
        mAdapter = new BookGridAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPublisherPresenter.showBooks();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPublisherPresenter.clearView();
    }

    @Override
    protected void closeRealm() {
        mPublisherPresenter.closeRealm();
    }

    @Override
    public void showBooks(final RealmList<Book> books) {
        mAdapter.setBooks(books);
    }
}
