package ugs.mobilehci.crashprevention.ui.base;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {


    private final CompositeDisposable mCompositeDisposable;
    private V mMvpView;

    @Inject
    public BasePresenter(CompositeDisposable compositeDisposable){
        this.mCompositeDisposable=compositeDisposable;
    }

//    @Override
//    public void onDetach() {
//        mCompositeDisposable.dispose();
//        mMvpView = null;
//    }

}
