package sample.sadashiv.examplerealmmvp.presenter;

import sample.sadashiv.examplerealmmvp.model.realm.RealmService;
import sample.sadashiv.examplerealmmvp.ui.BaseView;

public class BasePresenter<T extends BaseView> {

    protected T mView;
    protected  RealmService mRealmService;

    public BasePresenter(T view, RealmService realmService) {
        mView = view;
        mRealmService = realmService;
    }

    public void clearView() {
        mView = null;
    }

    public void closeRealm() {
        mRealmService.closeRealm();
    }
}
