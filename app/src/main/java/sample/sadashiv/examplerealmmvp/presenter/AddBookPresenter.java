package sample.sadashiv.examplerealmmvp.presenter;

import sample.sadashiv.examplerealmmvp.model.realm.RealmService;
import sample.sadashiv.examplerealmmvp.ui.add.AddBookView;

public class AddBookPresenter extends BasePresenter<AddBookView> {

    public AddBookPresenter(final AddBookView addBookView, final RealmService realmService) {
        super(addBookView, realmService);
    }

    public void onAddClick(final String title, final String author, final String isbn, final String publisher) {
        mRealmService.addBookAsync(title, author, isbn, publisher, mView);
    }
}
