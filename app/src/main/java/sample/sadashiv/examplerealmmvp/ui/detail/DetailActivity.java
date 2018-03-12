package sample.sadashiv.examplerealmmvp.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.sadashiv.examplerealmmvp.R;
import sample.sadashiv.examplerealmmvp.di.component.ApplicationComponent;
import sample.sadashiv.examplerealmmvp.di.component.DaggerDetailComponent;
import sample.sadashiv.examplerealmmvp.di.module.DetailModule;
import sample.sadashiv.examplerealmmvp.model.Book;
import sample.sadashiv.examplerealmmvp.presenter.DetailPresenter;
import sample.sadashiv.examplerealmmvp.ui.BaseActivity;
import sample.sadashiv.examplerealmmvp.ui.author.AuthorActivity;
import sample.sadashiv.examplerealmmvp.ui.publisher.PublisherActivity;

public class DetailActivity extends BaseActivity implements DetailView {

    private static final String EXTRA_ID = "EXTRA_ID";

    @BindView(R.id.text_author) TextView mTextAuthor;
    @BindView(R.id.text_title) TextView mTextTitle;
    @BindView(R.id.text_publisher) TextView mTextPublisher;

    @Inject
    DetailPresenter mMyDetailPresenter;

    public static Intent getStartIntent(final Context context, final String id) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }

    @Override
    protected void setupComponent(ApplicationComponent appComponent) {
        DaggerDetailComponent.builder()
                .applicationComponent(appComponent)
                .detailModule(getModule())
                .build()
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);
        ButterKnife.bind(this);
    }

    @Override
    public DetailModule getModule() {
        return new DetailModule(this, getIntent().getExtras().getString(EXTRA_ID));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMyDetailPresenter.showBookDetails();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyDetailPresenter.clearView();
    }

    @Override
    protected void closeRealm() {
        mMyDetailPresenter.closeRealm();
    }

    @Override
    public void showBookDetails(final Book book) {
        mTextAuthor.setText(book.getAuthorFullName());
        mTextTitle.setText(book.getTitle());
        mTextPublisher.setText(book.getPublisher().getName());
    }

    @OnClick(R.id.text_publisher)
    public void onPublisherClick() {
        startActivity(PublisherActivity.getStartIntent(this, mTextPublisher.getText().toString()));
    }

    @OnClick(R.id.text_author)
    public void onAuthorClick() {
        startActivity(AuthorActivity.getStartIntent(this, mTextAuthor.getText().toString()));
    }
}
