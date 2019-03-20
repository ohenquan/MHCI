package ugs.mobilehci.crashprevention.ui.settings;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ugs.mobilehci.crashprevention.ui.base.BasePresenter;

public class SettingsPresenter<V extends SettingsMvpView> extends BasePresenter<V> implements SettingsMvpPresenter<V> {


    @Inject
    public SettingsPresenter(CompositeDisposable compositeDisposable){
        super(compositeDisposable);

    }




}
