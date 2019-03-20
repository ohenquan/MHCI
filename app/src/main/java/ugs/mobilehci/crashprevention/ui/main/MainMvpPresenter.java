package ugs.mobilehci.crashprevention.ui.main;

import ugs.mobilehci.crashprevention.di.PerActivity;
import ugs.mobilehci.crashprevention.ui.base.MvpPresenter;


@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void onViewInitialized();

    void onNavMenuCreated();

}
