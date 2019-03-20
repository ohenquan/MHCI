package ugs.mobilehci.crashprevention.ui.main;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ugs.mobilehci.crashprevention.ui.base.BasePresenter;
import ugs.mobilehci.crashprevention.ui.base.MvpView;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    private static  final  String TAG = "MainPresenter";

    @Inject
    public MainPresenter(CompositeDisposable compositeDisposable){
        super(compositeDisposable);
    }


    @Override
    public void onViewInitialized(){

    }

    @Override
    public void onNavMenuCreated(){

    }




}
