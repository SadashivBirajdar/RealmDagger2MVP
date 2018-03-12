package sample.sadashiv.examplerealmmvp.ui.add;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.EditText;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.sadashiv.examplerealmmvp.R;
import sample.sadashiv.examplerealmmvp.di.component.ApplicationComponent;
import sample.sadashiv.examplerealmmvp.di.component.DaggerAddBooksComponent;
import sample.sadashiv.examplerealmmvp.di.module.AddBooksModule;
import sample.sadashiv.examplerealmmvp.presenter.AddBookPresenter;
import sample.sadashiv.examplerealmmvp.ui.BaseActivity;

public class AddBookActivity extends BaseActivity implements AddBookView {

    @BindView(R.id.layout_container) LinearLayout mLayoutContainer;
    @BindView(R.id.edit_title) EditText mEditTitle;
    @BindView(R.id.edit_author) EditText mEditAuthor;
    @BindView(R.id.edit_isbn) EditText mEditIsbn;
    @BindView(R.id.edit_publisher) EditText mEditPublisher;

    @Inject
    AddBookPresenter mAddBookPresenter;

    @Override
    protected void setupComponent(ApplicationComponent appComponent) {
        DaggerAddBooksComponent.builder()
                .applicationComponent(appComponent)
                .addBooksModule(getModule())
                .build()
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);
    }

    @Override
    public AddBooksModule getModule() {
        return new AddBooksModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAddBookPresenter.clearView();
    }

    @Override
    protected void closeRealm() {
        mAddBookPresenter.closeRealm();
    }

    @OnClick(R.id.button_add)
    public void onAddClick() {
        mAddBookPresenter.onAddClick(
                mEditTitle.getText().toString(),
                mEditAuthor.getText().toString(),
                mEditIsbn.getText().toString(),
                mEditPublisher.getText().toString());
    }

    @Override
    public void showAddBookError() {
        Snackbar.make(mLayoutContainer, R.string.add_new_book_error, Snackbar.LENGTH_SHORT).show();
    }
}
